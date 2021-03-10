package vn.mht.app.desktop;

import io.reactivex.Scheduler;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class AppCommon {
    private final Scheduler mainThread;
    private final Scheduler backgroundThread;
    private AtomicBoolean shutdown = new AtomicBoolean(false);
    private ExecutorService appExecutorService;


    @Inject
    public AppCommon(final ExecutorService executor, @ThreadMainUi final Scheduler main, @ThreadBackground final Scheduler background) {
//    public AppCommon(final ExecutorService executor,final Scheduler main, final Scheduler background, BaseUrlHolder urlHolder, HostSelectionInterceptor interceptor) {
        mainThread = main;
        backgroundThread = background;
        appExecutorService = executor;

    }

    public Scheduler getMainThread() {
        return mainThread;
    }

    public Scheduler getBackgroundThread() {
        return backgroundThread;
    }

    public boolean getShutdown() {
        return shutdown.get();
    }

    public void setShutdown(boolean off) {
        this.shutdown.set(off);
    }

    public ExecutorService getAppExecutorService() {
        return appExecutorService;
    }
}
