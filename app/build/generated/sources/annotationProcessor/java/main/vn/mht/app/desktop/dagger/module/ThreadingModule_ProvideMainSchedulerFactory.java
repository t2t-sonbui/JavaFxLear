package vn.mht.app.desktop.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ThreadingModule_ProvideMainSchedulerFactory implements Factory<Scheduler> {
  private final ThreadingModule module;

  public ThreadingModule_ProvideMainSchedulerFactory(ThreadingModule module) {
    this.module = module;
  }

  @Override
  public Scheduler get() {
    return provideMainScheduler(module);
  }

  public static ThreadingModule_ProvideMainSchedulerFactory create(ThreadingModule module) {
    return new ThreadingModule_ProvideMainSchedulerFactory(module);
  }

  public static Scheduler provideMainScheduler(ThreadingModule instance) {
    return Preconditions.checkNotNull(instance.provideMainScheduler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
