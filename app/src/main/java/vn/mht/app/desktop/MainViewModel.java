package vn.mht.app.desktop;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import javafx.scene.Node;

public class MainViewModel extends CommonViewModel {
    private BehaviorSubject<Node> nodeShow = BehaviorSubject.create();

    public MainViewModel() {
        this.mainThreadScheduler = Schedulers.io();
        this.backgroundThread = Schedulers.computation();
        this.logger = org.apache.log4j.Logger.getLogger(MainViewModel.class);
    }

    @Override
    public void dispose() {
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
