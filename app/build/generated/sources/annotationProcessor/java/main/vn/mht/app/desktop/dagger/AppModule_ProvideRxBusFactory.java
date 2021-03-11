package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.desktop.RxBus;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideRxBusFactory implements Factory<RxBus> {
  private final Provider<Scheduler> backgroundThreadProvider;

  public AppModule_ProvideRxBusFactory(Provider<Scheduler> backgroundThreadProvider) {
    this.backgroundThreadProvider = backgroundThreadProvider;
  }

  @Override
  public RxBus get() {
    return provideRxBus(backgroundThreadProvider.get());
  }

  public static AppModule_ProvideRxBusFactory create(Provider<Scheduler> backgroundThreadProvider) {
    return new AppModule_ProvideRxBusFactory(backgroundThreadProvider);
  }

  public static RxBus provideRxBus(Scheduler backgroundThread) {
    return Preconditions.checkNotNull(AppModule.provideRxBus(backgroundThread), "Cannot return null from a non-@Nullable @Provides method");
  }
}
