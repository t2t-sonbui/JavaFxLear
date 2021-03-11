package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.device.HardwareHelper;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideHardwareHelperFactory implements Factory<HardwareHelper> {
  private final Provider<Logger> loggerProvider;

  public AppModule_ProvideHardwareHelperFactory(Provider<Logger> loggerProvider) {
    this.loggerProvider = loggerProvider;
  }

  @Override
  public HardwareHelper get() {
    return provideHardwareHelper(loggerProvider.get());
  }

  public static AppModule_ProvideHardwareHelperFactory create(Provider<Logger> loggerProvider) {
    return new AppModule_ProvideHardwareHelperFactory(loggerProvider);
  }

  public static HardwareHelper provideHardwareHelper(Logger logger) {
    return Preconditions.checkNotNull(AppModule.provideHardwareHelper(logger), "Cannot return null from a non-@Nullable @Provides method");
  }
}
