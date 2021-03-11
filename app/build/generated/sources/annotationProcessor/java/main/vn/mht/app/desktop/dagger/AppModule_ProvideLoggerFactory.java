package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import org.apache.log4j.Logger;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideLoggerFactory implements Factory<Logger> {
  @Override
  public Logger get() {
    return provideLogger();
  }

  public static AppModule_ProvideLoggerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Logger provideLogger() {
    return Preconditions.checkNotNull(AppModule.provideLogger(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideLoggerFactory INSTANCE = new AppModule_ProvideLoggerFactory();
  }
}
