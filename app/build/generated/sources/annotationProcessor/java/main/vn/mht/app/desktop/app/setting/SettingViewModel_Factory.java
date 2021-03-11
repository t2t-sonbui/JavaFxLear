package vn.mht.app.desktop.app.setting;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.desktop.RxBus;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SettingViewModel_Factory implements Factory<SettingViewModel> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  public SettingViewModel_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
  }

  @Override
  public SettingViewModel get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get());
  }

  public static SettingViewModel_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    return new SettingViewModel_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider);
  }

  public static SettingViewModel newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log) {
    return new SettingViewModel(main, background, rxBus, log);
  }
}
