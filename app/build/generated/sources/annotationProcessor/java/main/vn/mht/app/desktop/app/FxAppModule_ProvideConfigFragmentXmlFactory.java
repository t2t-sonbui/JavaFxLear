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
public final class FxAppModule_ProvideConfigFragmentXmlFactory implements Factory<FXMLLoader> {
  @Override
  public FXMLLoader get() {
    return provideConfigFragmentXml();
  }

  public static FxAppModule_ProvideConfigFragmentXmlFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FXMLLoader provideConfigFragmentXml() {
    return Preconditions.checkNotNull(FxAppModule.provideConfigFragmentXml(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final FxAppModule_ProvideConfigFragmentXmlFactory INSTANCE = new FxAppModule_ProvideConfigFragmentXmlFactory();
  }
}
