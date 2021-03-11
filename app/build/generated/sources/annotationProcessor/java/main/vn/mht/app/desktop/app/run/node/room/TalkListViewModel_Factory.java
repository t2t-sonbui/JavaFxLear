package vn.mht.app.desktop.app.run.node.room;

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
public final class TalkListViewModel_Factory implements Factory<TalkListViewModel> {
  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  private final Provider<RxBus> rxBusProvider;

  private final Provider<Logger> logProvider;

  public TalkListViewModel_Factory(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
    this.rxBusProvider = rxBusProvider;
    this.logProvider = logProvider;
  }

  @Override
  public TalkListViewModel get() {
    return newInstance(mainProvider.get(), backgroundProvider.get(), rxBusProvider.get(), logProvider.get());
  }

  public static TalkListViewModel_Factory create(Provider<Scheduler> mainProvider,
      Provider<Scheduler> backgroundProvider, Provider<RxBus> rxBusProvider,
      Provider<Logger> logProvider) {
    return new TalkListViewModel_Factory(mainProvider, backgroundProvider, rxBusProvider, logProvider);
  }

  public static TalkListViewModel newInstance(Scheduler main, Scheduler background, RxBus rxBus,
      Logger log) {
    return new TalkListViewModel(main, background, rxBus, log);
  }
}
