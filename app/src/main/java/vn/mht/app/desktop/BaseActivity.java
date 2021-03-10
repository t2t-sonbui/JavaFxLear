package vn.mht.app.desktop;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity implements CommonTask {
    protected final CompositeDisposable disposables = new CompositeDisposable();


    protected Scheduler mainThreadScheduler;

    protected Scheduler backgroundThread;

    protected RxBus mRxBus;

    protected org.apache.log4j.Logger logger;

    public void dispose() {
        disposables.clear();
    }
}
