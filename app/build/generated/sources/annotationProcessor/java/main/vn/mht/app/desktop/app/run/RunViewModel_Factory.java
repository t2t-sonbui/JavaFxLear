package vn.mht.app.desktop.app.run;

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
public final class RunViewModel_Factory implements Factory<RunViewModel> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  public RunViewModel_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
  }

  @Override
  public RunViewModel get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get());
  }

  public static RunViewModel_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    return new RunViewModel_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider);
  }

  public static RunViewModel newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log) {
    return new RunViewModel(main, background, rxBus, log);
  }
}
