package vn.mht.app.desktop;

import com.google.gson.Gson;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;
import vn.mht.app.desktop.dagger.scope.PerActivity;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;

import javax.inject.Inject;

@PerActivity
public class MainActivity extends BaseActivity {

    private final GetDeviceUidInteractorImpl mGetDeviceUidInteractor;


    private final Gson mGson;
    private final AppCommon mAppCommon;
    private final RxBus mRxBus;

    @Inject
    public MainActivity(@ThreadMainUi Scheduler main, @ThreadBackground Scheduler background, RxBus rxBus, org.apache.log4j.Logger log, Gson gson
            , GetDeviceUidInteractorImpl getDeviceUidInteractor, AppCommon appCommon) {
        this.mainThreadScheduler = main;
        this.backgroundThread = background;
        this.mRxBus = rxBus;
        this.logger = log;
        this.mGson = gson;

        mGetDeviceUidInteractor = getDeviceUidInteractor;

        mAppCommon = appCommon;

    }

    @Override
    public void run() {

    }


    public void stopSystem() {
        disposables.add(
                Single.just(true)
                        .subscribeOn(backgroundThread)
                        .observeOn(mainThreadScheduler)
                        .subscribe(
                                (success) -> {
                                    logger.info("Enable System Shutdown:" + success);
                                    mAppCommon.setShutdown(true);
                                },
                                throwable -> {
                                    logger.error("Enable System Shutdown  Error", throwable);
                                }
                        )
        );

    }


}
