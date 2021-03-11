package vn.mht.app.desktop.app.run.node.system;

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
public final class SystemConfigViewModel_Factory implements Factory<SystemConfigViewModel> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  public SystemConfigViewModel_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
  }

  @Override
  public SystemConfigViewModel get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get());
  }

  public static SystemConfigViewModel_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    return new SystemConfigViewModel_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider);
  }

  public static SystemConfigViewModel newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log) {
    return new SystemConfigViewModel(main, background, rxBus, log);
  }
}
