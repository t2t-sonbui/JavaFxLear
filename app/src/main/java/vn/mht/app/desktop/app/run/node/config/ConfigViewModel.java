package vn.mht.app.desktop.app.run.node.config;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import vn.mht.app.desktop.CommonViewModel;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadMainUi;
import vn.mht.app.domain.interactors.use.device.impl.GetSerialPortAvaliableInteractorImpl;

import javax.inject.Inject;
import java.util.List;

public class ConfigViewModel extends CommonViewModel {
    private BehaviorSubject<List<String>> listSerialPort = BehaviorSubject.create();
    private final GetSerialPortAvaliableInteractorImpl mGetSerialPortAvaliableInteractor;
    private BehaviorSubject<Boolean> triggerScanPort = BehaviorSubject.createDefault(true);

    private Binding<ObservableList<String>> binding;

    @Inject
    public ConfigViewModel(
            @ThreadMainUi Scheduler main
            , @ThreadBackground Scheduler background
            , RxBus rxBus
            , org.apache.log4j.Logger log
            , GetSerialPortAvaliableInteractorImpl getSerialPortAvaliableInteractor
    ) {
        this.mainThreadScheduler = main;
        this.backgroundThread = background;
        this.mRxBus = rxBus;
        this.logger = log;
        this.mGetSerialPortAvaliableInteractor = getSerialPortAvaliableInteractor;

        disposables.add(triggerScanPort.doFinally(() -> logger.debug("triggerScanPort Final")).subscribe());
        disposables.add(listSerialPort.doFinally(() -> logger.debug("listSerialPort Final")).subscribe());
    }


    @Override
    public void dispose() {
        triggerScanPort.onComplete();
        listSerialPort.onComplete();
        binding.dispose();
        super.dispose();
    }

    public boolean getListSerialPortUpdate(Consumer<? super List<String>> onNext) {
        return disposables.add(listSerialPort.subscribeOn(backgroundThread).observeOn(JavaFxScheduler.platform()).subscribe(onNext));
    }

    public void getListSerials() {
        disposables.add(mGetSerialPortAvaliableInteractor.execute()
                .subscribeOn(backgroundThread)//Nen dung nhu vay de kiem soat luong rieng khi can subscrible
                .observeOn(mainThreadScheduler)
                .subscribe(
                        (list) -> {
                            listSerialPort.onNext(list);
                        },
                        throwable -> {
                            logger.error("getListSerials", throwable);
                        }
                )

        );

    }

    public void triggerListSerialsEvent() {
        triggerScanPort.onNext(true);
    }

    public Binding<ObservableList<String>> getSerialPortNameBinding() {
        binding = JavaFxObserver.toLazyBinding(
                triggerScanPort.flatMap(aBoolean ->
                        mGetSerialPortAvaliableInteractor.execute().toObservable())
                        .subscribeOn(backgroundThread)
                        .map(list -> {
                            ObservableList<String> observableList = FXCollections.observableArrayList();
                            observableList.setAll(list);
                            return observableList;
                        })
        );
        return binding;
    }

}
