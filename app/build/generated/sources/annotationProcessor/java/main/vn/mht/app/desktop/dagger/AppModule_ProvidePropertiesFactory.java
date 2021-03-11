package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Properties;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvidePropertiesFactory implements Factory<Properties> {
  @Override
  public Properties get() {
    return provideProperties();
  }

  public static AppModule_ProvidePropertiesFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Properties provideProperties() {
    return Preconditions.checkNotNull(AppModule.provideProperties(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvidePropertiesFactory INSTANCE = new AppModule_ProvidePropertiesFactory();
  }
}
