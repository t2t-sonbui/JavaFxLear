package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;
import vn.mht.app.domain.repository.DataRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UseCaseModule_ProvideGetDeviceUidInteractorImplFactory implements Factory<GetDeviceUidInteractorImpl> {
  private final Provider<DataRepository> repositoryProvider;

  public UseCaseModule_ProvideGetDeviceUidInteractorImplFactory(
      Provider<DataRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetDeviceUidInteractorImpl get() {
    return provideGetDeviceUidInteractorImpl(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideGetDeviceUidInteractorImplFactory create(
      Provider<DataRepository> repositoryProvider) {
    return new UseCaseModule_ProvideGetDeviceUidInteractorImplFactory(repositoryProvider);
  }

  public static GetDeviceUidInteractorImpl provideGetDeviceUidInteractorImpl(
      DataRepository repository) {
    return Preconditions.checkNotNull(UseCaseModule.provideGetDeviceUidInteractorImpl(repository), "Cannot return null from a non-@Nullable @Provides method");
  }
}
