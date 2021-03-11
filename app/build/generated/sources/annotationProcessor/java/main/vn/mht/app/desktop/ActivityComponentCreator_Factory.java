package vn.mht.app.desktop;

import dagger.internal.Factory;
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
public final class ActivityComponentCreator_Factory implements Factory<ActivityComponentCreator> {
  private final Provider<ActivityComponent.Factory> proxyFactoryProvider;

  public ActivityComponentCreator_Factory(
      Provider<ActivityComponent.Factory> proxyFactoryProvider) {
    this.proxyFactoryProvider = proxyFactoryProvider;
  }

  @Override
  public ActivityComponentCreator get() {
    return newInstance(proxyFactoryProvider.get());
  }

  public static ActivityComponentCreator_Factory create(
      Provider<ActivityComponent.Factory> proxyFactoryProvider) {
    return new ActivityComponentCreator_Factory(proxyFactoryProvider);
  }

  public static ActivityComponentCreator newInstance(ActivityComponent.Factory proxyFactory) {
    return new ActivityComponentCreator(proxyFactory);
  }
}
