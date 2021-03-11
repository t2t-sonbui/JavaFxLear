package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.desktop.app.run.RunActivity;
import vn.mht.app.desktop.app.run.RunViewModel;
import vn.mht.app.desktop.app.run.node.config.ConfigFragment;
import vn.mht.app.desktop.app.run.node.room.TalkListFragment;
import vn.mht.app.desktop.app.run.node.system.SystemConfigFragment;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideRunActivityFactory implements Factory<RunActivity> {
  private final Provider<RunViewModel> viewModelProvider;

  private final Provider<FXMLLoader> loaderProvider;

  private final Provider<TalkListFragment> talkListFragmentProvider;

  private final Provider<ConfigFragment> configFragmentProvider;

  private final Provider<SystemConfigFragment> systemFragmentProvider;

  public FxAppModule_ProvideRunActivityFactory(Provider<RunViewModel> viewModelProvider,
      Provider<FXMLLoader> loaderProvider, Provider<TalkListFragment> talkListFragmentProvider,
      Provider<ConfigFragment> configFragmentProvider,
      Provider<SystemConfigFragment> systemFragmentProvider) {
    this.viewModelProvider = viewModelProvider;
    this.loaderProvider = loaderProvider;
    this.talkListFragmentProvider = talkListFragmentProvider;
    this.configFragmentProvider = configFragmentProvider;
    this.systemFragmentProvider = systemFragmentProvider;
  }

  @Override
  public RunActivity get() {
    return provideRunActivity(viewModelProvider.get(), loaderProvider.get(), talkListFragmentProvider.get(), configFragmentProvider.get(), systemFragmentProvider.get());
  }

  public static FxAppModule_ProvideRunActivityFactory create(
      Provider<RunViewModel> viewModelProvider, Provider<FXMLLoader> loaderProvider,
      Provider<TalkListFragment> talkListFragmentProvider,
      Provider<ConfigFragment> configFragmentProvider,
      Provider<SystemConfigFragment> systemFragmentProvider) {
    return new FxAppModule_ProvideRunActivityFactory(viewModelProvider, loaderProvider, talkListFragmentProvider, configFragmentProvider, systemFragmentProvider);
  }

  public static RunActivity provideRunActivity(RunViewModel viewModel, FXMLLoader loader,
      TalkListFragment talkListFragment, ConfigFragment configFragment,
      SystemConfigFragment systemFragment) {
    return Preconditions.checkNotNull(FxAppModule.provideRunActivity(viewModel, loader, talkListFragment, configFragment, systemFragment), "Cannot return null from a non-@Nullable @Provides method");
  }
}
