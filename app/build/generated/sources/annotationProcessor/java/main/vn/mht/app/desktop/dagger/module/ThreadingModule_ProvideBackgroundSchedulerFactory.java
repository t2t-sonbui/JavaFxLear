package vn.mht.app.desktop.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ThreadingModule_ProvideBackgroundSchedulerFactory implements Factory<Scheduler> {
  private final ThreadingModule module;

  private final Provider<ExecutorService> executorProvider;

  public ThreadingModule_ProvideBackgroundSchedulerFactory(ThreadingModule module,
      Provider<ExecutorService> executorProvider) {
    this.module = module;
    this.executorProvider = executorProvider;
  }

  @Override
  public Scheduler get() {
    return provideBackgroundScheduler(module, executorProvider.get());
  }

  public static ThreadingModule_ProvideBackgroundSchedulerFactory create(ThreadingModule module,
      Provider<ExecutorService> executorProvider) {
    return new ThreadingModule_ProvideBackgroundSchedulerFactory(module, executorProvider);
  }

  public static Scheduler provideBackgroundScheduler(ThreadingModule instance,
      ExecutorService executor) {
    return Preconditions.checkNotNull(instance.provideBackgroundScheduler(executor), "Cannot return null from a non-@Nullable @Provides method");
  }
}
