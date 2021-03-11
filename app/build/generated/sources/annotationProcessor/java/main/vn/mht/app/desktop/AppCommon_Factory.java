package vn.mht.app.desktop;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppCommon_Factory implements Factory<AppCommon> {
  private final Provider<ExecutorService> executorProvider;

  private final Provider<Scheduler> mainProvider;

  private final Provider<Scheduler> backgroundProvider;

  public AppCommon_Factory(Provider<ExecutorService> executorProvider,
      Provider<Scheduler> mainProvider, Provider<Scheduler> backgroundProvider) {
    this.executorProvider = executorProvider;
    this.mainProvider = mainProvider;
    this.backgroundProvider = backgroundProvider;
  }

  @Override
  public AppCommon get() {
    return newInstance(executorProvider.get(), mainProvider.get(), backgroundProvider.get());
  }

  public static AppCommon_Factory create(Provider<ExecutorService> executorProvider,
      Provider<Scheduler> mainProvider, Provider<Scheduler> backgroundProvider) {
    return new AppCommon_Factory(executorProvider, mainProvider, backgroundProvider);
  }

  public static AppCommon newInstance(ExecutorService executor, Scheduler main,
      Scheduler background) {
    return new AppCommon(executor, main, background);
  }
}
