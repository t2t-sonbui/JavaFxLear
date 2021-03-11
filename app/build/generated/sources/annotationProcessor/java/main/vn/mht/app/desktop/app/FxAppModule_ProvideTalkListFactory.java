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
public final class FxAppModule_ProvideTalkListFactory implements Factory<FXMLLoader> {
  @Override
  public FXMLLoader get() {
    return provideTalkList();
  }

  public static FxAppModule_ProvideTalkListFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FXMLLoader provideTalkList() {
    return Preconditions.checkNotNull(FxAppModule.provideTalkList(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final FxAppModule_ProvideTalkListFactory INSTANCE = new FxAppModule_ProvideTalkListFactory();
  }
}
