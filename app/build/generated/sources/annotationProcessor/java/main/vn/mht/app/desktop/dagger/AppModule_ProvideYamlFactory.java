package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import org.yaml.snakeyaml.Yaml;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideYamlFactory implements Factory<Yaml> {
  @Override
  public Yaml get() {
    return provideYaml();
  }

  public static AppModule_ProvideYamlFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Yaml provideYaml() {
    return Preconditions.checkNotNull(AppModule.provideYaml(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideYamlFactory INSTANCE = new AppModule_ProvideYamlFactory();
  }
}
