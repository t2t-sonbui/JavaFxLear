package vn.mht.app.desktop.app.run;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import javafx.scene.Node;
import vn.mht.app.desktop.CommonViewModel;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunViewModel extends CommonViewModel {
    private BehaviorSubject<Node> nodeShow = BehaviorSubject.create();


    @Inject
    public RunViewModel(
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
        System.out.println(this.getClass()+"->dispose()");
        nodeShow.onComplete();
        super.dispose();
    }

    public boolean getNodeNavigate(Consumer<? super Node> onNext) {
        return disposables.add(nodeShow.subscribeOn(backgroundThread).subscribe(onNext));
    }
    public void setNavigate(Node node) {
        nodeShow.onNext(node);
    }
}
