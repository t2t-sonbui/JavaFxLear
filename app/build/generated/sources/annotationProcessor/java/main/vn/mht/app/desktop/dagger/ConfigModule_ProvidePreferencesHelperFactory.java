package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import vn.mht.app.data.local.PreferencesHelper;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ConfigModule_ProvidePreferencesHelperFactory implements Factory<PreferencesHelper> {
  private final ConfigModule module;

  private final Provider<Class> mainClassProvider;

  private final Provider<Yaml> yamlProvider;

  private final Provider<Logger> loggerProvider;

  public ConfigModule_ProvidePreferencesHelperFactory(ConfigModule module,
      Provider<Class> mainClassProvider, Provider<Yaml> yamlProvider,
      Provider<Logger> loggerProvider) {
    this.module = module;
    this.mainClassProvider = mainClassProvider;
    this.yamlProvider = yamlProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public PreferencesHelper get() {
    return providePreferencesHelper(module, mainClassProvider.get(), yamlProvider.get(), loggerProvider.get());
  }

  public static ConfigModule_ProvidePreferencesHelperFactory create(ConfigModule module,
      Provider<Class> mainClassProvider, Provider<Yaml> yamlProvider,
      Provider<Logger> loggerProvider) {
    return new ConfigModule_ProvidePreferencesHelperFactory(module, mainClassProvider, yamlProvider, loggerProvider);
  }

  public static PreferencesHelper providePreferencesHelper(ConfigModule instance, Class mainClass,
      Yaml yaml, Logger logger) {
    return Preconditions.checkNotNull(instance.providePreferencesHelper(mainClass, yaml, logger), "Cannot return null from a non-@Nullable @Provides method");
  }
}
