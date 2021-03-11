package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.desktop.app.run.node.config.ConfigFragment;
import vn.mht.app.desktop.app.run.node.config.ConfigViewModel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideConfigFragmentFactory implements Factory<ConfigFragment> {
  private final Provider<ConfigViewModel> viewModelProvider;

  private final Provider<FXMLLoader> loaderProvider;

  public FxAppModule_ProvideConfigFragmentFactory(Provider<ConfigViewModel> viewModelProvider,
      Provider<FXMLLoader> loaderProvider) {
    this.viewModelProvider = viewModelProvider;
    this.loaderProvider = loaderProvider;
  }

  @Override
  public ConfigFragment get() {
    return provideConfigFragment(viewModelProvider.get(), loaderProvider.get());
  }

  public static FxAppModule_ProvideConfigFragmentFactory create(
      Provider<ConfigViewModel> viewModelProvider, Provider<FXMLLoader> loaderProvider) {
    return new FxAppModule_ProvideConfigFragmentFactory(viewModelProvider, loaderProvider);
  }

  public static ConfigFragment provideConfigFragment(ConfigViewModel viewModel, FXMLLoader loader) {
    return Preconditions.checkNotNull(FxAppModule.provideConfigFragment(viewModel, loader), "Cannot return null from a non-@Nullable @Provides method");
  }
}
