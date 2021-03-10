package vn.mht.app.desktop.app.run.node.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import vn.mht.app.desktop.FragmentNode;
import vn.mht.app.desktop.FragmentScreen;
import vn.mht.app.desktop.Main;
import vn.mht.app.domain.OsUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemConfigFragment extends FragmentNode<SystemConfigViewModel> implements FragmentScreen {

    //https://quantrimang.com/lenh-reg-add-trong-windows-159659
//    private final String REG_ADD_CMD = "cmd /c reg add \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run\" /v \"{0}\" /d \"{1}\" /t REG_EXPAND_SZ";//Dung ko can login
    private final String REG_ADD_CMD = "reg add \"HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run\" /v \"{0}\" /d \"{1}\" /t REG_SZ /f";
    //https://quantrimang.com/lenh-reg-delete-trong-windows-159776
    private final String REG_DEL_CMD = "reg delete \"HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run\" /v \"{0}\" /f";
    private final String REG_QUERY_CMD = "reg query \"HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run\" /v \"{0}\"";

    final Class mMainClass = Main.class;

    private JFXButton btnCheckOta;
    private JFXButton btnUpdate;
    private JFXToggleButton cbStartup;


    private final SystemConfigViewModel viewModel;
    private final org.apache.log4j.Logger mLogger;
    private final FXMLLoader loader;
    private Node parentNode;

    public SystemConfigFragment(SystemConfigViewModel viewModel, FXMLLoader fxmlLoader, org.apache.log4j.Logger logger) {
        this.viewModel = viewModel;
        loader = fxmlLoader;
        mLogger = logger;
        initViewComponent();
    }


    public Node getParentNode() {
        return parentNode;
    }

    public void initViewComponent() {
        try {
            parentNode = loader.load();//Parent root = loader.load();
            btnCheckOta = (JFXButton) loader.getNamespace().get("btnCheckOta");
            btnUpdate = (JFXButton) loader.getNamespace().get("btnUpdate");
            cbStartup = (JFXToggleButton) loader.getNamespace().get("cbStartup");
            initStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStage() {
        viewModel.getTimeUpdate(s -> showDateTime(s));

        btnCheckOta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
                viewModel.updateDateTime();
            }
        });
        String executionPath = getExecutionPath();
        String regKey = "ZigApp";
        String regAppPath = executionPath + File.separator + "app.exe";

        if (executionPath != null) {

        }
        mLogger.info(" getExecutionPath():" + executionPath);

        String cmdQuery = MessageFormat.format(REG_QUERY_CMD, new Object[]{regKey});
        try {
            List<String> regResult = getRegeditCommandResult(null, cmdQuery);

            if (regResult.size() > 2) {
                String line = regResult.get(2);
                if (!line.startsWith("ERROR")) {
                    mLogger.info(" Parser path for line : " + line);
                    Pattern patternAddress = Pattern.compile("(\\s+\\w+)*\\s+(.+)");
                    Matcher matcher = patternAddress.matcher(line);
                    mLogger.info("Start finding:" + line);
                    if (matcher.matches()) {
                        String path = matcher.group(2);
                        mLogger.info(" Got Path startup: " + path);
                        if (path.equalsIgnoreCase(regAppPath))
                            cbStartup.setSelected(true);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        cbStartup.setOnAction(event -> {
            if (cbStartup.isSelected()) {
                System.out.println(event);
                String cmdLine = MessageFormat.format(REG_ADD_CMD, new Object[]{regKey, regAppPath});
                try {
                    List<String> regResult = getRegeditCommandResult(null, cmdLine);
                    if (!regResult.get(0).startsWith("ERROR")) {
                        System.out.println(regResult.get(0));
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                String cmdLine = MessageFormat.format(REG_DEL_CMD, new Object[]{regKey});
                try {
                    List<String> regResult = getRegeditCommandResult(null, cmdLine);
                    if (!regResult.get(0).startsWith("ERROR")) {
                        System.out.println(regResult.get(0));
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void showDateTime(String datetime) {
        System.out.println(datetime);
    }

    @Override
    public void terminate() {
        System.out.println("Fragment terminate()");
        viewModel.dispose();
    }

    private String getCurrentDir() {
        String resultPath = null;
        CodeSource codeSource = mMainClass.getProtectionDomain().getCodeSource();
        File jarFile;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
            resultPath = jarFile.getParentFile().getPath();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if ((resultPath != null) && !resultPath.isEmpty()) {
            return resultPath;
        } else
            return null;

    }

    private String getParentDir() {
        String resultPath = null;
        CodeSource codeSource = mMainClass.getProtectionDomain().getCodeSource();
        File jarFile;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
            resultPath = jarFile.getParentFile().getParent();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if ((resultPath != null) && !resultPath.isEmpty()) {
            return resultPath;
        } else
            return null;

    }

    public String getExecutionPath() {
        try {
            String executionPath = System.getProperty("user.dir");
            return executionPath;

//            String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
//            absolute = absolute.substring(0, absolute.length() - 1);
//            absolute = absolute.substring(0, absolute.lastIndexOf("/") + 1);
//            String configPath = absolute + "config/file.properties";
//            String os = System.getProperty("os.name");
//            if (os.indexOf("Windows") != -1) {
//                configPath = configPath.replace("/", "\\\\");
//                if (configPath.indexOf("file:\\\\") != -1) {
//                    configPath = configPath.replace("file:\\\\", "");
//                }
//            } else if (configPath.indexOf("file:") != -1) {
//                configPath = configPath.replace("file:", "");
//            }

        } catch (Exception e) {
            System.out.println("Exception caught =" + e.getMessage());
            return null;
        }
    }

    public List<String> getRegeditCommandResult(String sourcePath, final String cmdExecute) throws IOException, InterruptedException {
        Process process = null;
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true); // redirecting error stream
        mLogger.debug(" -->: " + cmdExecute);
        List<String> lines = new ArrayList<>();
        if (OsUtils.isWindows()) {
            processBuilder.command("cmd.exe", "/c", cmdExecute);
        } else if (OsUtils.isLinux()) {
//            processBuilder.command("sh", "-c", cmdExecute);
        }
        if (!(sourcePath == null || sourcePath.isEmpty()))
            processBuilder.directory(new File(sourcePath));
        try {
            process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//                mLogger.info("<--:" + line.trim());
                if (OsUtils.isWindows()) {
                    lines.add(line);
                } else
                    break;


            }
            int exitCode = process.waitFor();
            mLogger.debug("<--ExitCode: " + exitCode);
            return lines;
        } catch (IOException e) {
            throw e;
        } catch (InterruptedException e) {
            throw e;
        } finally {
            if (process != null) {
                process.getInputStream().close();
                process.waitFor(3, TimeUnit.SECONDS);
                process.destroy();
                if (process.isAlive()) process.destroyForcibly();
            }
        }

    }
}
