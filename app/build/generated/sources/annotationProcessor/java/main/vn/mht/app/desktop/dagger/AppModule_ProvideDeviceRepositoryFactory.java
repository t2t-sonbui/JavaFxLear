package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.device.HardwareHelper;
import vn.mht.app.domain.repository.DeviceRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideDeviceRepositoryFactory implements Factory<DeviceRepository> {
  private final Provider<HardwareHelper> hardwareHelperProvider;

  public AppModule_ProvideDeviceRepositoryFactory(Provider<HardwareHelper> hardwareHelperProvider) {
    this.hardwareHelperProvider = hardwareHelperProvider;
  }

  @Override
  public DeviceRepository get() {
    return provideDeviceRepository(hardwareHelperProvider.get());
  }

  public static AppModule_ProvideDeviceRepositoryFactory create(
      Provider<HardwareHelper> hardwareHelperProvider) {
    return new AppModule_ProvideDeviceRepositoryFactory(hardwareHelperProvider);
  }

  public static DeviceRepository provideDeviceRepository(HardwareHelper hardwareHelper) {
    return Preconditions.checkNotNull(AppModule.provideDeviceRepository(hardwareHelper), "Cannot return null from a non-@Nullable @Provides method");
  }
}
