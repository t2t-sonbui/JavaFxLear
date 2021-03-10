package vn.mht.app.desktop;

import com.google.gson.Gson;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Scheduler;
import io.reactivex.subjects.BehaviorSubject;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;
import vn.mht.app.domain.model.CommonConfigModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class MainBusiness extends BaseActivity {

    private final GetDeviceUidInteractorImpl mGetDeviceUidInteractor;

    private final Gson mGson;
    private final AppCommon mAppCommon;

    private BehaviorSubject<Boolean> notifyIntervalCheck = BehaviorSubject.create();
    private final ActivityComponentCreator mActivityComponentCreator;
    private AtomicBoolean enableRunning = new AtomicBoolean(true);
    private AtomicBoolean isLimitRunning = new AtomicBoolean(false);

    private volatile String mDeviceUid;


    @Inject
    public MainBusiness(
            @ThreadMainUi Scheduler main
            , @ThreadBackground Scheduler background
            , RxBus rxBus
            , org.apache.log4j.Logger log
            , Gson gson
            , GetDeviceUidInteractorImpl getDeviceUidInteractor
            , AppCommon appCommon
            , ActivityComponentCreator activityComponentCreator

    ) {
        this.mainThreadScheduler = main;
        this.backgroundThread = background;
        this.mRxBus = rxBus;
        this.logger = log;
        this.mGson = gson;
        this.mGetDeviceUidInteractor = getDeviceUidInteractor;
        this.mAppCommon = appCommon;
        this.mActivityComponentCreator = activityComponentCreator;
    }

    @Override
    public void run() {
        //Add to run after create
        disposables.add(
                mActivityComponentCreator.getNotifyRunSubject()
                        .toFlowable(BackpressureStrategy.LATEST)
                        //.debounce(3000, TimeUnit.MILLISECONDS)
                        .subscribeOn(backgroundThread)
                        .observeOn(mainThreadScheduler)
                        .subscribe(
                                (success) -> {
                                    logger.info("Activity Trigger Run:" + success);

                                    if (mActivityComponentCreator.getMainActivity() != null) {
                                        mActivityComponentCreator.getMainActivity().run();
                                        notifyIntervalCheck.onNext(true);
                                    } else {
                                        logger.info("MainActivity Is NULL");
                                    }
                                    //Todo chay cac activity khac
                                },
                                throwable -> {
                                    logger.error("CommandActivity Trigger Run Error:", throwable);
                                },
                                () -> logger.info("CommandActivity Trigger Run:Complete")
                        )
        );
        disposables.add(
                mGetDeviceUidInteractor.execute()
                        .doOnSuccess(deviceUid -> {
                            mDeviceUid = deviceUid;
                        })
                        .subscribeOn(backgroundThread)
                        .observeOn(mainThreadScheduler)
                        .subscribe(
                                (deviceUid) -> {
                                    logger.debug("Config Activity With Device Uid:" + deviceUid);
                                    CommonConfigModel commonConfigModel = new CommonConfigModel();
                                    commonConfigModel.setDeviceUid(deviceUid);
                                    commonConfigModel.setLimitEnable(false);
                                    mActivityComponentCreator.createActivityComponent(commonConfigModel);
                                },
                                throwable -> {
                                    logger.error("Auto Start CommonActivity Error:", throwable);
                                }
                        )
        );

    }

    @Override
    public void dispose() {
        logger.debug("MainBusiness dispose");
        if (mActivityComponentCreator.getMainActivity() != null) {
            mActivityComponentCreator.getMainActivity().dispose();
            notifyIntervalCheck.onComplete();
        } else {
            logger.info("MainActivity Is NULL");
        }
        super.dispose();
    }


}
