package vn.mht.app.desktop.dagger;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideGsonFactory implements Factory<Gson> {
  @Override
  public Gson get() {
    return provideGson();
  }

  public static AppModule_ProvideGsonFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Gson provideGson() {
    return Preconditions.checkNotNull(AppModule.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideGsonFactory INSTANCE = new AppModule_ProvideGsonFactory();
  }
}
