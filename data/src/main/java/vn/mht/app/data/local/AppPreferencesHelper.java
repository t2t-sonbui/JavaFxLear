package vn.mht.app.data.local;

import io.reactivex.Single;
import org.yaml.snakeyaml.Yaml;


import javax.inject.Singleton;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final Class mMainClass;
    private Yaml mYaml;
    private Map<String, Object> yamlContents;
    private static final String HOME_PATH = System.getProperty("user.home");
    private final org.apache.log4j.Logger mLogger;

    public AppPreferencesHelper(final Yaml yaml, final Class mainClazz, final org.apache.log4j.Logger logger) {

        this.mMainClass = mainClazz;
        this.mYaml = yaml;
        this.mLogger = logger;
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
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


    @Override
    public Single<Map<String, Object>> getYamlConfig(String yamlFile) {
        return Single.fromCallable(() -> {
            InputStream inputStream = null;
            String configDir = getCurrentDir();
            mLogger.debug("CurrentDir:" + configDir);
            if (getCurrentDir() != null) {// Load ext config
                try {
                    inputStream = new FileInputStream(configDir + File.separator + yamlFile);
                    mLogger.debug("Use External config");
                } catch (FileNotFoundException e) {
                    inputStream = getClass().getClassLoader().getResourceAsStream(yamlFile);
                    mLogger.debug("Use Internal config");
                }

            } else {
                inputStream = getClass().getClassLoader().getResourceAsStream(yamlFile);
                mLogger.debug("Use Internal config");
            }

            if (inputStream == null) {
                mLogger.debug("Sorry, unable to find config file");
                throw new Exception("Unable to find config file " + yamlFile);
            }
            yamlContents = mYaml.load(inputStream);
            return yamlContents;
        });

    }


    private String getDestForderPath() {
        return HOME_PATH + File.separator + "Music";//+ File.separator + uid;
    }

    @Override
    public Single<Boolean> deleteSourceDirPath() {
        return Single.fromCallable(() -> {
            String destFolderPath = getDestForderPath();

//            Path deleteFilePath = Paths.get(destFolderPath);
//            Files.delete(deleteFilePath);

            File directory = new File(destFolderPath);
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (null != files) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isDirectory()) {
                            deleteDirectory(files[i]);
                        } else {
                            files[i].delete();
                        }
                    }
                }
            }
            return (directory.delete());
        });
    }

    public boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return (directory.delete());
    }

    @Override
    public Single<String> getSourceDirPath() {//Config dir
        return Single.fromCallable(() -> {
            // start compile
            String destFolderPath = getDestForderPath();
            File destFolder = new File(destFolderPath);
            // make sure source exists
            if (!destFolder.exists()) {
                // Fix when can not create mutil dir path
                String pathFile = getDestForderPath() + File.separator + ".temp";
                Path path = Paths.get(pathFile);
                Files.createDirectories(path.getParent());
                // if directory not exists, create it
                if (!destFolder.exists()) {
                    destFolder.mkdir();
                }
            }
            return destFolderPath;
        });
    }

    private Single<File> getSourceDirFile() {
        return Single.fromCallable(() -> {
            // start compile
            String destFolderPath = getDestForderPath();
            File destFolder = new File(destFolderPath);
            // make sure source exists
            if (!destFolder.exists()) {
                // Fix when can not create mutil dir path
                String pathFile = getDestForderPath() + File.separator + ".temp";
                Path path = Paths.get(pathFile);
                Files.createDirectories(path.getParent());
                // if directory not exists, create it
                if (!destFolder.exists()) {
                    destFolder.mkdir();
                }
            }
            return destFolder;
        });

    }

    @Override
    public Single<String> getCurrentJarPath() {
        return Single.defer(() -> Single.just(getCurrentDir()));
    }

    @Override
    public Single<String> writeTextFile(String content, String fileNameWithoutPath) {
        return getSourceDirPath().flatMap(configFolderPath ->
                Single.fromCallable(() -> {
                    BufferedWriter bw = null;
                    FileWriter fw = null;
                    final String fileNameFullPath = configFolderPath + File.separator + fileNameWithoutPath;
                    try {
                        fw = new FileWriter(fileNameFullPath);
                        bw = new BufferedWriter(fw);
                        bw.write(content);
                    } catch (IOException e) {
                        throw e;
                    } finally {
                        try {
                            if (bw != null)
                                bw.close();
                            if (fw != null)
                                fw.close();
                        } catch (IOException ex) {
                            System.err.format("IOException: %s%n", ex);
                            throw ex;
                        }
                    }
                    return fileNameFullPath;
                }));
    }

    @Override
    public Single<String> readTextFile(String fileNameWithoutPath) {
        return getSourceDirPath().flatMap(configFolderPath ->
                Single.fromCallable(() -> {
                    BufferedReader br = null;
                    FileReader fr = null;
                    String content;
                    final String fileNameFullPath = configFolderPath + File.separator + fileNameWithoutPath;
                    try {
                        fr = new FileReader(fileNameFullPath);
                        br = new BufferedReader(fr);
                        content = readAllLinesWithStream(br);
                    } catch (IOException e) {
                        throw e;
                    } finally {
                        try {
                            if (br != null)
                                br.close();
                            if (fr != null)
                                fr.close();
                        } catch (IOException ex) {
                            System.err.format("IOException: %s%n", ex);
                            throw ex;
                        }
                    }
                    return content;
                }));
    }


    public String readAllLinesWithStream(BufferedReader reader) {
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }

    @Override
    public Single<String> getDeviceIdHardcode() {
        return Single.fromCallable(() -> {
                    if (yamlContents.get("fixed") instanceof Map<?, ?>) {
                        Map<String, Object> yamlApi = (Map<String, Object>) yamlContents.get("fixed");
                        String deviceIdFixed = (String) yamlApi.get("deviceId");
                        if ((deviceIdFixed != null) && !deviceIdFixed.isEmpty()) {
                            return deviceIdFixed;
                        } else
                            throw new Error("Not found device ID config");
                    } else throw new Exception("No options config or in the wrong format");
                }
        );
    }

}

