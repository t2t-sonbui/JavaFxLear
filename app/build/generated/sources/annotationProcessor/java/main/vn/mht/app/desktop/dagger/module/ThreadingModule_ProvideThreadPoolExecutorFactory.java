package vn.mht.app.desktop.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ThreadingModule_ProvideThreadPoolExecutorFactory implements Factory<ExecutorService> {
  private final ThreadingModule module;

  public ThreadingModule_ProvideThreadPoolExecutorFactory(ThreadingModule module) {
    this.module = module;
  }

  @Override
  public ExecutorService get() {
    return provideThreadPoolExecutor(module);
  }

  public static ThreadingModule_ProvideThreadPoolExecutorFactory create(ThreadingModule module) {
    return new ThreadingModule_ProvideThreadPoolExecutorFactory(module);
  }

  public static ExecutorService provideThreadPoolExecutor(ThreadingModule instance) {
    return Preconditions.checkNotNull(instance.provideThreadPoolExecutor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
