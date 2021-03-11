package vn.mht.app.desktop.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import vn.mht.app.domain.interactors.use.data.impl.PrepareLoadConfigInteractorImpl;
import vn.mht.app.domain.repository.DataRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UseCaseModule_ProvidePrepareLoadConfigInteractorImplFactory implements Factory<PrepareLoadConfigInteractorImpl> {
  private final Provider<DataRepository> repositoryProvider;

  public UseCaseModule_ProvidePrepareLoadConfigInteractorImplFactory(
      Provider<DataRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PrepareLoadConfigInteractorImpl get() {
    return providePrepareLoadConfigInteractorImpl(repositoryProvider.get());
  }

  public static UseCaseModule_ProvidePrepareLoadConfigInteractorImplFactory create(
      Provider<DataRepository> repositoryProvider) {
    return new UseCaseModule_ProvidePrepareLoadConfigInteractorImplFactory(repositoryProvider);
  }

  public static PrepareLoadConfigInteractorImpl providePrepareLoadConfigInteractorImpl(
      DataRepository repository) {
    return Preconditions.checkNotNull(UseCaseModule.providePrepareLoadConfigInteractorImpl(repository), "Cannot return null from a non-@Nullable @Provides method");
  }
}
