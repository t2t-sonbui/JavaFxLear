package vn.mht.app.desktop;

import com.google.gson.Gson;
import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainBusiness_Factory implements Factory<MainBusiness> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider;

  private final Provider<AppCommon> appCommonProvider;

  private final Provider<ActivityComponentCreator> activityComponentCreatorProvider;

  public MainBusiness_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider, Provider<Gson> gsonProvider,
      Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider,
      Provider<AppCommon> appCommonProvider,
      Provider<ActivityComponentCreator> activityComponentCreatorProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
    this.gsonProvider = gsonProvider;
    this.getDeviceUidInteractorProvider = getDeviceUidInteractorProvider;
    this.appCommonProvider = appCommonProvider;
    this.activityComponentCreatorProvider = activityComponentCreatorProvider;
  }

  @Override
  public MainBusiness get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get(), gsonProvider.get(), getDeviceUidInteractorProvider.get(), appCommonProvider.get(), activityComponentCreatorProvider.get());
  }

  public static MainBusiness_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider, Provider<Gson> gsonProvider,
      Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider,
      Provider<AppCommon> appCommonProvider,
      Provider<ActivityComponentCreator> activityComponentCreatorProvider) {
    return new MainBusiness_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider, gsonProvider, getDeviceUidInteractorProvider, appCommonProvider, activityComponentCreatorProvider);
  }

  public static MainBusiness newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log, Gson gson, GetDeviceUidInteractorImpl getDeviceUidInteractor, AppCommon appCommon,
      ActivityComponentCreator activityComponentCreator) {
    return new MainBusiness(main, background, rxBus, log, gson, getDeviceUidInteractor, appCommon, activityComponentCreator);
  }
}
