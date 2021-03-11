package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.desktop.app.run.node.room.TalkListFragment;
import vn.mht.app.desktop.app.run.node.room.TalkListViewModel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideTalkListFragmentFactory implements Factory<TalkListFragment> {
  private final Provider<TalkListViewModel> viewModelProvider;

  private final Provider<FXMLLoader> loaderProvider;

  public FxAppModule_ProvideTalkListFragmentFactory(Provider<TalkListViewModel> viewModelProvider,
      Provider<FXMLLoader> loaderProvider) {
    this.viewModelProvider = viewModelProvider;
    this.loaderProvider = loaderProvider;
  }

  @Override
  public TalkListFragment get() {
    return provideTalkListFragment(viewModelProvider.get(), loaderProvider.get());
  }

  public static FxAppModule_ProvideTalkListFragmentFactory create(
      Provider<TalkListViewModel> viewModelProvider, Provider<FXMLLoader> loaderProvider) {
    return new FxAppModule_ProvideTalkListFragmentFactory(viewModelProvider, loaderProvider);
  }

  public static TalkListFragment provideTalkListFragment(TalkListViewModel viewModel,
      FXMLLoader loader) {
    return Preconditions.checkNotNull(FxAppModule.provideTalkListFragment(viewModel, loader), "Cannot return null from a non-@Nullable @Provides method");
  }
}
