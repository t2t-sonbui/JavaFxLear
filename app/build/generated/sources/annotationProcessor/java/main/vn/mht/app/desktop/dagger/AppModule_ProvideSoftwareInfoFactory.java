package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import vn.mht.app.domain.model.ProductBuildModel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideSoftwareInfoFactory implements Factory<ProductBuildModel> {
  @Override
  public ProductBuildModel get() {
    return provideSoftwareInfo();
  }

  public static AppModule_ProvideSoftwareInfoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProductBuildModel provideSoftwareInfo() {
    return Preconditions.checkNotNull(AppModule.provideSoftwareInfo(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideSoftwareInfoFactory INSTANCE = new AppModule_ProvideSoftwareInfoFactory();
  }
}
