package vn.mht.app.desktop.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FxAppModule_ProvideMainLoaderFactory implements Factory<FXMLLoader> {
  @Override
  public FXMLLoader get() {
    return provideMainLoader();
  }

  public static FxAppModule_ProvideMainLoaderFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FXMLLoader provideMainLoader() {
    return Preconditions.checkNotNull(FxAppModule.provideMainLoader(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final FxAppModule_ProvideMainLoaderFactory INSTANCE = new FxAppModule_ProvideMainLoaderFactory();
  }
}
