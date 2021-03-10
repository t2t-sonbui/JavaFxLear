
package vn.mht.app.desktop;

import io.reactivex.subjects.PublishSubject;
import vn.mht.app.domain.model.CommonConfigModel;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ActivityComponentCreator {

    private final ActivityComponent.Factory mCommonProxyFactory;
    private ActivityComponent mActivityComponent;
    private PublishSubject<Boolean> notifyRunSubject = PublishSubject.create();

    @Inject
    public ActivityComponentCreator(
            ActivityComponent.Factory proxyFactory) {
        this.mCommonProxyFactory = proxyFactory;
    }

    public void setActivityComponent(ActivityComponent activityComponent) {
        this.mActivityComponent = activityComponent;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public ActivityComponent createActivityComponent(CommonConfigModel commonConfig) {
        mActivityComponent = mCommonProxyFactory.create(commonConfig);
        notifyRunSubject.onNext(true);
        return mActivityComponent;
    }

    public MainActivity getMainActivity() {
        if (mActivityComponent != null) {
            return mActivityComponent.getMainActivity();
        } else {
            return null;
        }
    }

    public CommonConfigModel getCommonConfigModel() {
        return mActivityComponent.commonConfigModel();
    }


    public PublishSubject<Boolean> getNotifyRunSubject() {
        return notifyRunSubject;
    }
}
