package vn.mht.app.desktop.app.run.node.config;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.domain.interactors.use.device.impl.GetSerialPortAvaliableInteractorImpl;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ConfigViewModel_Factory implements Factory<ConfigViewModel> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  private final Provider<GetSerialPortAvaliableInteractorImpl> getSerialPortAvaliableInteractorProvider;

  public ConfigViewModel_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider,
      Provider<GetSerialPortAvaliableInteractorImpl> getSerialPortAvaliableInteractorProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
    this.getSerialPortAvaliableInteractorProvider = getSerialPortAvaliableInteractorProvider;
  }

  @Override
  public ConfigViewModel get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get(), getSerialPortAvaliableInteractorProvider.get());
  }

  public static ConfigViewModel_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider,
      Provider<GetSerialPortAvaliableInteractorImpl> getSerialPortAvaliableInteractorProvider) {
    return new ConfigViewModel_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider, getSerialPortAvaliableInteractorProvider);
  }

  public static ConfigViewModel newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log, GetSerialPortAvaliableInteractorImpl getSerialPortAvaliableInteractor) {
    return new ConfigViewModel(main, background, rxBus, log, getSerialPortAvaliableInteractor);
  }
}
