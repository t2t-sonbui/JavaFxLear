package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.domain.interactors.use.device.impl.GetSerialPortAvaliableInteractorImpl;
import vn.mht.app.domain.repository.DeviceRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UseCaseModule_ProvideGetSerialPortAvaliableInteractorImplFactory implements Factory<GetSerialPortAvaliableInteractorImpl> {
  private final Provider<DeviceRepository> repositoryProvider;

  public UseCaseModule_ProvideGetSerialPortAvaliableInteractorImplFactory(
      Provider<DeviceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetSerialPortAvaliableInteractorImpl get() {
    return provideGetSerialPortAvaliableInteractorImpl(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideGetSerialPortAvaliableInteractorImplFactory create(
      Provider<DeviceRepository> repositoryProvider) {
    return new UseCaseModule_ProvideGetSerialPortAvaliableInteractorImplFactory(repositoryProvider);
  }

  public static GetSerialPortAvaliableInteractorImpl provideGetSerialPortAvaliableInteractorImpl(
      DeviceRepository repository) {
    return Preconditions.checkNotNull(UseCaseModule.provideGetSerialPortAvaliableInteractorImpl(repository), "Cannot return null from a non-@Nullable @Provides method");
  }
}
