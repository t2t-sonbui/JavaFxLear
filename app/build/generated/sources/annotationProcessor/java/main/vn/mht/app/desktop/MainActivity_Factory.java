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
public final class MainActivity_Factory implements Factory<MainActivity> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider;

  private final Provider<AppCommon> appCommonProvider;

  public MainActivity_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider, Provider<Gson> gsonProvider,
      Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider,
      Provider<AppCommon> appCommonProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
    this.gsonProvider = gsonProvider;
    this.getDeviceUidInteractorProvider = getDeviceUidInteractorProvider;
    this.appCommonProvider = appCommonProvider;
  }

  @Override
  public MainActivity get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get(), gsonProvider.get(), getDeviceUidInteractorProvider.get(), appCommonProvider.get());
  }

  public static MainActivity_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider, Provider<Gson> gsonProvider,
      Provider<GetDeviceUidInteractorImpl> getDeviceUidInteractorProvider,
      Provider<AppCommon> appCommonProvider) {
    return new MainActivity_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider, gsonProvider, getDeviceUidInteractorProvider, appCommonProvider);
  }

  public static MainActivity newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log, Gson gson, GetDeviceUidInteractorImpl getDeviceUidInteractor,
      AppCommon appCommon) {
    return new MainActivity(main, background, rxBus, log, gson, getDeviceUidInteractor, appCommon);
  }
}
