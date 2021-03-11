package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.desktop.app.setting.SettingActivity;
import vn.mht.app.desktop.app.setting.SettingViewModel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideSettingActivityFactory implements Factory<SettingActivity> {
  private final Provider<SettingViewModel> viewModelProvider;

  public FxAppModule_ProvideSettingActivityFactory(Provider<SettingViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  @Override
  public SettingActivity get() {
    return provideSettingActivity(viewModelProvider.get());
  }

  public static FxAppModule_ProvideSettingActivityFactory create(
      Provider<SettingViewModel> viewModelProvider) {
    return new FxAppModule_ProvideSettingActivityFactory(viewModelProvider);
  }

  public static SettingActivity provideSettingActivity(SettingViewModel viewModel) {
    return Preconditions.checkNotNull(FxAppModule.provideSettingActivity(viewModel), "Cannot return null from a non-@Nullable @Provides method");
  }
}
