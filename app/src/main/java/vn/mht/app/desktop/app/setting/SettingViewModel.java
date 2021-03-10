package vn.mht.app.desktop.app.setting;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import vn.mht.app.desktop.CommonViewModel;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingViewModel extends CommonViewModel {
    private BehaviorSubject<String> timeUpdate = BehaviorSubject.create();


    @Inject
    public SettingViewModel(
            @ThreadMainUi Scheduler main
            , @ThreadBackground Scheduler background
            , RxBus rxBus
            , org.apache.log4j.Logger log) {
        this.mainThreadScheduler = main;
        this.backgroundThread = background;
        this.mRxBus = rxBus;
        this.logger = log;
    }

    @Override
    public void dispose() {
        timeUpdate.onComplete();
        super.dispose();
    }

    public boolean getTimeUpdate(Consumer<? super String> onNext) {
        return disposables.add(timeUpdate.subscribeOn(backgroundThread).subscribe(onNext));

    }

    public void updateDateTime() {
        System.out.println("Button Clicked!");
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        // Dữ liệu Model
        String dateTimeString = df.format(now);
        mRxBus.getPublishSubject(RxBus.UPDATE_IN_CALLING).onNext(true);
        timeUpdate.onNext(dateTimeString);

    }

    public void hidePannel() {

        mRxBus.getPublishSubject(RxBus.UPDATE_IN_CALLING).onNext(false);


    }
}
