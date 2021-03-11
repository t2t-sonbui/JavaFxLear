package vn.mht.app.desktop.dagger;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import vn.mht.app.data.local.PreferencesHelper;
import vn.mht.app.domain.model.ProductBuildModel;
import vn.mht.app.domain.repository.DataRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideDataRepositoryFactory implements Factory<DataRepository> {
  private final Provider<PreferencesHelper> preferencesHelperProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<ProductBuildModel> productBuildModelProvider;

  private final Provider<Logger> loggerProvider;

  public AppModule_ProvideDataRepositoryFactory(
      Provider<PreferencesHelper> preferencesHelperProvider, Provider<Gson> gsonProvider,
      Provider<ProductBuildModel> productBuildModelProvider, Provider<Logger> loggerProvider) {
    this.preferencesHelperProvider = preferencesHelperProvider;
    this.gsonProvider = gsonProvider;
    this.productBuildModelProvider = productBuildModelProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public DataRepository get() {
    return provideDataRepository(preferencesHelperProvider.get(), gsonProvider.get(), productBuildModelProvider.get(), loggerProvider.get());
  }

  public static AppModule_ProvideDataRepositoryFactory create(
      Provider<PreferencesHelper> preferencesHelperProvider, Provider<Gson> gsonProvider,
      Provider<ProductBuildModel> productBuildModelProvider, Provider<Logger> loggerProvider) {
    return new AppModule_ProvideDataRepositoryFactory(preferencesHelperProvider, gsonProvider, productBuildModelProvider, loggerProvider);
  }

  public static DataRepository provideDataRepository(PreferencesHelper preferencesHelper, Gson gson,
      ProductBuildModel productBuildModel, Logger logger) {
    return Preconditions.checkNotNull(AppModule.provideDataRepository(preferencesHelper, gson, productBuildModel, logger), "Cannot return null from a non-@Nullable @Provides method");
  }
}
