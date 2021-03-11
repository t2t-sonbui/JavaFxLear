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
public final class FxAppModule_ProvideSystemConfigFragmentXmlFactory implements Factory<FXMLLoader> {
  @Override
  public FXMLLoader get() {
    return provideSystemConfigFragmentXml();
  }

  public static FxAppModule_ProvideSystemConfigFragmentXmlFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FXMLLoader provideSystemConfigFragmentXml() {
    return Preconditions.checkNotNull(FxAppModule.provideSystemConfigFragmentXml(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final FxAppModule_ProvideSystemConfigFragmentXmlFactory INSTANCE = new FxAppModule_ProvideSystemConfigFragmentXmlFactory();
  }
}
