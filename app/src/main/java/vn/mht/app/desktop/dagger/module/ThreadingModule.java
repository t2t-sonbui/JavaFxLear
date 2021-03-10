package vn.mht.app.desktop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;


import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Module
public final class ThreadingModule {

    @Singleton
    @Provides
    public ExecutorService provideThreadPoolExecutor() {
        int threadCount = Runtime.getRuntime().availableProcessors();
//        int threadCoreCount = 5;
//        if (threadCount <= 5) {
//            threadCoreCount = threadCount - 2;
//        }
//        if (threadCount < 2) threadCount = 2;
//        if (threadCoreCount < 2) threadCoreCount = 2;
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCoreCount, threadCount, 60,
//                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCount, threadCount, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        return executor;

//        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);//Test running in single thread
//        return threadPoolExecutor;
    }

    @Provides
    @Singleton
    @ThreadMainUi
    public Scheduler provideMainScheduler() {
//        return Schedulers.single();
        return Schedulers.io();
    }


    @Provides
    @Singleton
    @ThreadBackground
    public Scheduler provideBackgroundScheduler(ExecutorService executor) {
        return Schedulers.from(executor);
//        return Schedulers.trampoline();//Use for https://medium.com/@I_Love_Coding/rxjava-schedulers-trampoline-use-cases-283f6649cbf Firebase limit one
//        return Schedulers.computation();
//        return Schedulers.io();
    }

}
