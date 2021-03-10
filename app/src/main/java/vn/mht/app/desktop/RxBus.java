package vn.mht.app.desktop;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;


import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RxBus {
    private Map<String, PublishSubject<Object>> sSubjectMap = new HashMap<>();
    private Map<Object, CompositeDisposable> sSubscriptionsMap = new HashMap<>();
    private final Scheduler mBackgroundThread;

    public static final String UPDATE_MQTT_MSG_RECEIVED = "MQTT_MSG_RECEIVED";
    public static final String UPDATE_HARDWARE_CHANGE = "HARDWARE_CHANGE";
    public static final String UPDATE_MEDIA_STATE_CHANGE = "MEDIA_STATE_CHANGE";
    public static final String UPDATE_MEDIA_DOWNLOAD_CHANGE = "MEDIA_DOWNLOAD_CHANGE";
    public static final String UPDATE_DEVICE_ON_OFF_CHANGE = "DEVICE_ON_OFF_CHANGE";
    public static final String UPDATE_TALK_STREAM_ENABLE = "TALK_STREAM_ENABLE";
    public static final String UPDATE_TALK_STREAM_RECEIVED = "TALK_STREAM_RECEIVED";
    public static final String UPDATE_TALK_STREAM_TRANSMITTER = "TALK_STREAM_TRANSMITTER";
    public static final String UPDATE_IN_CALLING = "IN_CALLING";

    public static final String UPDATE_MODEM_CONNECT_REMOVE = "MODEM_CONNECT_REMOVE";
    public static final String UPDATE_MODEM_CONNECT_ADDED = "MODEM_CONNECT_ADDED";
    public static final String UPDATE_MODEM_CONNECT_UPDATE = "MODEM_CONNECT_UPDATE";
    public static final String UPDATE_HOME_NETWORK_CHANGE = "HOME_NETWORK_CHANGE";
    public static final String UPDATE_IP_RE_NEW = "IP_RE_NEW";
    public static final String UPDATE_SHUTDOWN_EVENT = "SHUTDOWN_EVENT";

    public static final String UPDATE_EXECUTE_ACTION_EVENT = "EXECUTE_ACTION_RESPONSE";
    public static final String EXECUTE_ACTION_EVENT = "EXECUTE_ACTION_REQUEST";

    public static final String UPDATE_EXECUTE_RUN_EVENT = "EXECUTE_RUN_RESPONSE";
    public static final String EXECUTE_RUN_EVENT = "EXECUTE_RUN_REQUEST";

    public RxBus(@ThreadBackground final Scheduler backgroundThread) {
        mBackgroundThread = backgroundThread;
    }

    /**
     * Get the subject or create it if it's not already in memory.
     */
    @NonNull
    private PublishSubject<Object> getSubject(String subjectKey) {
        return getSubject(subjectKey, mBackgroundThread);
    }

    @NonNull
    private PublishSubject<Object> getSubject(String subjectKey, Scheduler scheduler) {
        PublishSubject<Object> subject = sSubjectMap.get(subjectKey);
        if (subject == null) {
            subject = PublishSubject.create();
            subject.subscribeOn(scheduler);
            sSubjectMap.put(subjectKey, subject);
        }

        return subject;
    }

    /**
     * Get the CompositeDisposable or create it if it's not already in memory.
     */
    @NonNull
    private CompositeDisposable getCompositeDisposable(@NonNull Object object) {
        CompositeDisposable compositeDisposable = sSubscriptionsMap.get(object);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            sSubscriptionsMap.put(object, compositeDisposable);
        }
        return compositeDisposable;
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject. Pass in an object to associate
     * your registration with, so that you can unsubscribe later.
     * <br/><br/>
     * <b>Note:</b> Make sure to call {@link RxBus#unregister(Object)} to avoid memory leaks.
     */
    public void subscribe(String subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action, Scheduler scheduler) {
        Disposable disposable = getSubject(subject).subscribeOn(scheduler).subscribe(action);
        getCompositeDisposable(lifecycle).add(disposable);
    }

    public void subscribe(String subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action) {
        this.subscribe(subject, lifecycle, action, mBackgroundThread);
    }

    public PublishSubject<Object> getPublishSubject(String subject) {//Goi truc tiep de thao tac
        return getSubject(subject);
    }


    public PublishSubject<Object> removePublishSubject(String subjectKey) {
        PublishSubject<Object> subject = sSubjectMap.get(subjectKey);
        if (subject != null) {
            subject.onComplete();
            sSubjectMap.remove(subjectKey);
        }
        return subject;
    }

    /**
     * Unregisters this object from the bus, removing all subscriptions.
     * This should be called when the object is going to go out of memory.
     */
    public void unregister(@NonNull Object lifecycle) {
        //We have to remove the composition from the map, because once you dispose it can't be used anymore
        CompositeDisposable compositeDisposable = sSubscriptionsMap.remove(lifecycle);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     */
    public void publish(String subject, @NonNull Object message) {
        getSubject(subject).onNext(message);
    }
}
