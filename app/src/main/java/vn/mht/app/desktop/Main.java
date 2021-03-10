package vn.mht.app.desktop;

import com.jfoenix.svg.SVGGlyphLoader;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import javafx.application.Application;
import javafx.stage.Stage;
import vn.mht.app.desktop.app.FxAppComponent;
import vn.mht.app.desktop.dagger.AppComponent;

import vn.mht.app.desktop.dagger.DaggerAppComponent;
import vn.mht.app.domain.interactors.use.data.impl.PrepareLoadConfigInteractorImpl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String FILE_LOCK = "ZSupport_Lock.dat";
    private static final String FILE_LOCK_PATH = TMP_DIR + File.separator + FILE_LOCK;
    private FileLock fileLock;
    private FileChannel fileChannel;

    private MainBusiness mMainBusiness;

    private CompositeDisposable disposables = new CompositeDisposable();

    private org.apache.log4j.Logger logger;
    private ExecutorService appExecutorService;
    private FxAppComponent fxAppComponent;

    //Stage nhu la Activity, Scene nhu la Fragment

    @Override
    public void init() throws Exception {
        super.init();
        //ppComponent component =DaggerAppComponent.create();/Khi ko có moudle được khởi tạo sau khong có trong component thi dùng create
        AppComponent component = DaggerAppComponent.builder()//Khi co moudle được khởi tạo sau khong có trong component thi dùng builder
                .mainClass(Main.class)
                .build();

        fxAppComponent = component.fxAppComponent()
                .application(this)
                .build();
        AppCommon appCommon = component.appCommon();
        logger = component.logger();

        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {// Global handle eror config for RXjava
                e = e.getCause();
            }
//            if ((e instanceof IOException) || (e instanceof SocketException)) {
//                // fine, irrelevant network problem or API that throws on cancellation
//                return;
//            }
//            if (e instanceof InterruptedException) {
//                // fine, some blocking code was interrupted by a dispose call
//                return;
//            }
//            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
//                // that's likely a bug in the application
//                Thread.currentThread().getUncaughtExceptionHandler()
//                        .handleException(Thread.currentThread(), e);
//                return;
//            }
//            if (e instanceof IllegalStateException) {
//                // that's a bug in RxJava or in a custom operator
//                Thread.currentThread().getUncaughtExceptionHandler()
//                        .handleException(Thread.currentThread(), e);
//                return;
//            }
            logger.error("Undeliverable exception received, not sure what to do", e);
        });
        try {
            Path newFilePath = Paths.get(FILE_LOCK_PATH);
            if (newFilePath.toFile().exists()) {
                Files.delete(newFilePath);
            }
            Files.createFile(newFilePath);
            File file = newFilePath.toFile();//new File(TMP_DIR, FILE_LOCK);
            logger.debug(file.getPath());
            // Creates a random access file stream to read from, and optionally to write to
            fileChannel = new RandomAccessFile(file, "rw").getChannel();
            // Acquire an exclusive lock on this channel's file (blocks until lock can be retrieved)
            fileLock = fileChannel.lock();
            // Attempts to acquire an exclusive lock on this channel's file (returns null or throws
            // an exception if the file is already locked.
            try {
                fileLock = fileChannel.tryLock();
            } catch (OverlappingFileLockException e) {
                // thrown when an attempt is made to acquire a lock on a a file that overlaps
                // a region already locked by the same JVM or when another thread is already
                // waiting to lock an overlapping region of the same file
                logger.debug("Overlapping File Lock Error: " + e.getMessage());
            }

        } catch (IOException e) {
            logger.error("I/O Error: " + e.getMessage());
        }

        PrepareLoadConfigInteractorImpl mPrepareLoadConfigInteractor = component.getPrepareLoadConfigInteractor();

        disposables.add(
                mPrepareLoadConfigInteractor.execute(PrepareLoadConfigInteractorImpl.Params.forConfigFile("Config.yaml"))
//                        .doOnSuccess(objectMap -> {
//                            logger.info("Config:" + Arrays.toString(objectMap.values().toArray()));
//                        })

                        .subscribeOn(appCommon.getBackgroundThread())
                        .observeOn(appCommon.getMainThread())
                        .subscribe(
                                (objectMap) -> {
                                    logger.info("Config:" + Arrays.toString(objectMap.values().toArray()));
//Run some thing when load done
                                },
                                throwable -> {
                                    logger.error("Get Config.yaml Error", throwable);
                                }
                        )
        );

        appExecutorService = appCommon.getAppExecutorService();
        mMainBusiness = component.mainBusiness();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        fxAppComponent.getSettingActivity().terminate();
        fxAppComponent.getRunActivity().terminate();
        logger.info("Shutting down....");
        if (mMainBusiness != null) mMainBusiness.dispose();
        disposables.clear();
        logger.info("Shutting down Executor Service");
        appExecutorService.shutdown();
        try {
            // release the lock
            fileLock.release();
            logger.info("Release the lock");
            // close the channel
            fileChannel.close();
            logger.info("Close the channel");
            Path deleteFilePath = Paths.get(FILE_LOCK_PATH);
            Files.delete(deleteFilePath);
            logger.info("Delete File Path:" + deleteFilePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("System shutdown clear file error", e);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(Main.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        mMainBusiness.run();
        primaryStage.hide();
        fxAppComponent.getRunActivity().showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

