package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.desktop.app.run.node.system.SystemConfigFragment;
import vn.mht.app.desktop.app.run.node.system.SystemConfigViewModel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideSystemConfigFragmentFactory implements Factory<SystemConfigFragment> {
  private final Provider<SystemConfigViewModel> viewModelProvider;

  private final Provider<FXMLLoader> loaderProvider;

  private final Provider<Logger> loggerProvider;

  public FxAppModule_ProvideSystemConfigFragmentFactory(
      Provider<SystemConfigViewModel> viewModelProvider, Provider<FXMLLoader> loaderProvider,
      Provider<Logger> loggerProvider) {
    this.viewModelProvider = viewModelProvider;
    this.loaderProvider = loaderProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public SystemConfigFragment get() {
    return provideSystemConfigFragment(viewModelProvider.get(), loaderProvider.get(), loggerProvider.get());
  }

  public static FxAppModule_ProvideSystemConfigFragmentFactory create(
      Provider<SystemConfigViewModel> viewModelProvider, Provider<FXMLLoader> loaderProvider,
      Provider<Logger> loggerProvider) {
    return new FxAppModule_ProvideSystemConfigFragmentFactory(viewModelProvider, loaderProvider, loggerProvider);
  }

  public static SystemConfigFragment provideSystemConfigFragment(SystemConfigViewModel viewModel,
      FXMLLoader loader, Logger logger) {
    return Preconditions.checkNotNull(FxAppModule.provideSystemConfigFragment(viewModel, loader, logger), "Cannot return null from a non-@Nullable @Provides method");
  }
}
