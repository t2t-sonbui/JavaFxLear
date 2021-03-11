package vn.mht.app.desktop.dagger;

import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import vn.mht.app.data.local.PreferencesHelper;
import vn.mht.app.desktop.ActivityComponent;
import vn.mht.app.desktop.ActivityComponentCreator;
import vn.mht.app.desktop.ActivityComponentCreator_Factory;
import vn.mht.app.desktop.AppCommon;
import vn.mht.app.desktop.AppCommon_Factory;
import vn.mht.app.desktop.MainActivity;
import vn.mht.app.desktop.MainActivity_Factory;
import vn.mht.app.desktop.MainBusiness;
import vn.mht.app.desktop.MainBusiness_Factory;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.desktop.app.FxAppComponent;
import vn.mht.app.desktop.app.FxAppModule_ProvideConfigFragmentFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideConfigFragmentXmlFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideMainLoaderFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideRunActivityFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideSettingActivityFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideSystemConfigFragmentFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideSystemConfigFragmentXmlFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideTalkListFactory;
import vn.mht.app.desktop.app.FxAppModule_ProvideTalkListFragmentFactory;
import vn.mht.app.desktop.app.run.RunActivity;
import vn.mht.app.desktop.app.run.RunViewModel;
import vn.mht.app.desktop.app.run.RunViewModel_Factory;
import vn.mht.app.desktop.app.run.node.config.ConfigFragment;
import vn.mht.app.desktop.app.run.node.config.ConfigViewModel;
import vn.mht.app.desktop.app.run.node.config.ConfigViewModel_Factory;
import vn.mht.app.desktop.app.run.node.room.TalkListFragment;
import vn.mht.app.desktop.app.run.node.room.TalkListViewModel;
import vn.mht.app.desktop.app.run.node.room.TalkListViewModel_Factory;
import vn.mht.app.desktop.app.run.node.system.SystemConfigFragment;
import vn.mht.app.desktop.app.run.node.system.SystemConfigViewModel;
import vn.mht.app.desktop.app.run.node.system.SystemConfigViewModel_Factory;
import vn.mht.app.desktop.app.setting.SettingActivity;
import vn.mht.app.desktop.app.setting.SettingViewModel;
import vn.mht.app.desktop.app.setting.SettingViewModel_Factory;
import vn.mht.app.desktop.dagger.module.ThreadingModule;
import vn.mht.app.desktop.dagger.module.ThreadingModule_ProvideBackgroundSchedulerFactory;
import vn.mht.app.desktop.dagger.module.ThreadingModule_ProvideMainSchedulerFactory;
import vn.mht.app.desktop.dagger.module.ThreadingModule_ProvideThreadPoolExecutorFactory;
import vn.mht.app.device.HardwareHelper;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;
import vn.mht.app.domain.interactors.use.data.impl.PrepareLoadConfigInteractorImpl;
import vn.mht.app.domain.interactors.use.device.impl.GetSerialPortAvaliableInteractorImpl;
import vn.mht.app.domain.model.CommonConfigModel;
import vn.mht.app.domain.model.ProductBuildModel;
import vn.mht.app.domain.repository.DataRepository;
import vn.mht.app.domain.repository.DeviceRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
  private Provider<Logger> provideLoggerProvider;

  private Provider<ExecutorService> provideThreadPoolExecutorProvider;

  private Provider<Scheduler> provideMainSchedulerProvider;

  private Provider<Scheduler> provideBackgroundSchedulerProvider;

  private Provider<AppCommon> appCommonProvider;

  private Provider<RxBus> provideRxBusProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<Class> mainClassProvider;

  private Provider<Yaml> provideYamlProvider;

  private Provider<PreferencesHelper> providePreferencesHelperProvider;

  private Provider<ProductBuildModel> provideSoftwareInfoProvider;

  private Provider<DataRepository> provideDataRepositoryProvider;

  private Provider<GetDeviceUidInteractorImpl> provideGetDeviceUidInteractorImplProvider;

  private Provider<ActivityComponent.Factory> activityComponentFactoryProvider;

  private Provider<ActivityComponentCreator> activityComponentCreatorProvider;

  private Provider<MainBusiness> mainBusinessProvider;

  private Provider<PrepareLoadConfigInteractorImpl> providePrepareLoadConfigInteractorImplProvider;

  private Provider<HardwareHelper> provideHardwareHelperProvider;

  private Provider<DeviceRepository> provideDeviceRepositoryProvider;

  private Provider<GetSerialPortAvaliableInteractorImpl> provideGetSerialPortAvaliableInteractorImplProvider;

  private DaggerAppComponent(ConfigModule configModuleParam, ThreadingModule threadingModuleParam,
      Class mainClassParam) {

    initialize(configModuleParam, threadingModuleParam, mainClassParam);
  }

  public static AppComponent.Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final ConfigModule configModuleParam,
      final ThreadingModule threadingModuleParam, final Class mainClassParam) {
    this.provideLoggerProvider = DoubleCheck.provider(AppModule_ProvideLoggerFactory.create());
    this.provideThreadPoolExecutorProvider = DoubleCheck.provider(ThreadingModule_ProvideThreadPoolExecutorFactory.create(threadingModuleParam));
    this.provideMainSchedulerProvider = DoubleCheck.provider(ThreadingModule_ProvideMainSchedulerFactory.create(threadingModuleParam));
    this.provideBackgroundSchedulerProvider = DoubleCheck.provider(ThreadingModule_ProvideBackgroundSchedulerFactory.create(threadingModuleParam, provideThreadPoolExecutorProvider));
    this.appCommonProvider = DoubleCheck.provider(AppCommon_Factory.create(provideThreadPoolExecutorProvider, provideMainSchedulerProvider, provideBackgroundSchedulerProvider));
    this.provideRxBusProvider = DoubleCheck.provider(AppModule_ProvideRxBusFactory.create(provideBackgroundSchedulerProvider));
    this.provideGsonProvider = DoubleCheck.provider(AppModule_ProvideGsonFactory.create());
    this.mainClassProvider = InstanceFactory.create(mainClassParam);
    this.provideYamlProvider = DoubleCheck.provider(AppModule_ProvideYamlFactory.create());
    this.providePreferencesHelperProvider = DoubleCheck.provider(ConfigModule_ProvidePreferencesHelperFactory.create(configModuleParam, mainClassProvider, provideYamlProvider, provideLoggerProvider));
    this.provideSoftwareInfoProvider = DoubleCheck.provider(AppModule_ProvideSoftwareInfoFactory.create());
    this.provideDataRepositoryProvider = DoubleCheck.provider(AppModule_ProvideDataRepositoryFactory.create(providePreferencesHelperProvider, provideGsonProvider, provideSoftwareInfoProvider, provideLoggerProvider));
    this.provideGetDeviceUidInteractorImplProvider = DoubleCheck.provider(UseCaseModule_ProvideGetDeviceUidInteractorImplFactory.create(provideDataRepositoryProvider));
    this.activityComponentFactoryProvider = new Provider<ActivityComponent.Factory>() {
      @Override
      public ActivityComponent.Factory get() {
        return new ActivityComponentFactory();
      }
    };
    this.activityComponentCreatorProvider = DoubleCheck.provider(ActivityComponentCreator_Factory.create(activityComponentFactoryProvider));
    this.mainBusinessProvider = DoubleCheck.provider(MainBusiness_Factory.create(provideMainSchedulerProvider, provideBackgroundSchedulerProvider, provideRxBusProvider, provideLoggerProvider, provideGsonProvider, provideGetDeviceUidInteractorImplProvider, appCommonProvider, activityComponentCreatorProvider));
    this.providePrepareLoadConfigInteractorImplProvider = DoubleCheck.provider(UseCaseModule_ProvidePrepareLoadConfigInteractorImplFactory.create(provideDataRepositoryProvider));
    this.provideHardwareHelperProvider = DoubleCheck.provider(AppModule_ProvideHardwareHelperFactory.create(provideLoggerProvider));
    this.provideDeviceRepositoryProvider = DoubleCheck.provider(AppModule_ProvideDeviceRepositoryFactory.create(provideHardwareHelperProvider));
    this.provideGetSerialPortAvaliableInteractorImplProvider = DoubleCheck.provider(UseCaseModule_ProvideGetSerialPortAvaliableInteractorImplFactory.create(provideDeviceRepositoryProvider));
  }

  @Override
  public FxAppComponent.Builder fxAppComponent() {
    return new FxAppComponentBuilder();
  }

  @Override
  public Logger logger() {
    return provideLoggerProvider.get();
  }

  @Override
  public AppCommon appCommon() {
    return appCommonProvider.get();
  }

  @Override
  public MainBusiness mainBusiness() {
    return mainBusinessProvider.get();
  }

  @Override
  public PrepareLoadConfigInteractorImpl getPrepareLoadConfigInteractor() {
    return providePrepareLoadConfigInteractorImplProvider.get();
  }

  private static final class Builder implements AppComponent.Builder {
    private Class mainClass;

    @Override
    public Builder mainClass(Class mainClazz) {
      this.mainClass = Preconditions.checkNotNull(mainClazz);
      return this;
    }

    @Override
    public AppComponent build() {
      Preconditions.checkBuilderRequirement(mainClass, Class.class);
      return new DaggerAppComponent(new ConfigModule(), new ThreadingModule(), mainClass);
    }
  }

  private final class FxAppComponentBuilder implements FxAppComponent.Builder {
    private Application application;

    @Override
    public FxAppComponentBuilder application(Application application) {
      this.application = Preconditions.checkNotNull(application);
      return this;
    }

    @Override
    public FxAppComponent build() {
      Preconditions.checkBuilderRequirement(application, Application.class);
      return new FxAppComponentImpl(application);
    }
  }

  private final class FxAppComponentImpl implements FxAppComponent {
    private Provider<SettingViewModel> settingViewModelProvider;

    private Provider<SettingActivity> provideSettingActivityProvider;

    private Provider<RunViewModel> runViewModelProvider;

    private Provider<FXMLLoader> provideMainLoaderProvider;

    private Provider<TalkListViewModel> talkListViewModelProvider;

    private Provider<FXMLLoader> provideTalkListProvider;

    private Provider<TalkListFragment> provideTalkListFragmentProvider;

    private Provider<ConfigViewModel> configViewModelProvider;

    private Provider<FXMLLoader> provideConfigFragmentXmlProvider;

    private Provider<ConfigFragment> provideConfigFragmentProvider;

    private Provider<SystemConfigViewModel> systemConfigViewModelProvider;

    private Provider<FXMLLoader> provideSystemConfigFragmentXmlProvider;

    private Provider<SystemConfigFragment> provideSystemConfigFragmentProvider;

    private Provider<RunActivity> provideRunActivityProvider;

    private FxAppComponentImpl(Application application) {

      initialize(application);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Application application) {
      this.settingViewModelProvider = SettingViewModel_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider);
      this.provideSettingActivityProvider = DoubleCheck.provider(FxAppModule_ProvideSettingActivityFactory.create(settingViewModelProvider));
      this.runViewModelProvider = RunViewModel_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider);
      this.provideMainLoaderProvider = DoubleCheck.provider(FxAppModule_ProvideMainLoaderFactory.create());
      this.talkListViewModelProvider = TalkListViewModel_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider);
      this.provideTalkListProvider = DoubleCheck.provider(FxAppModule_ProvideTalkListFactory.create());
      this.provideTalkListFragmentProvider = DoubleCheck.provider(FxAppModule_ProvideTalkListFragmentFactory.create(talkListViewModelProvider, provideTalkListProvider));
      this.configViewModelProvider = ConfigViewModel_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider, DaggerAppComponent.this.provideGetSerialPortAvaliableInteractorImplProvider);
      this.provideConfigFragmentXmlProvider = DoubleCheck.provider(FxAppModule_ProvideConfigFragmentXmlFactory.create());
      this.provideConfigFragmentProvider = DoubleCheck.provider(FxAppModule_ProvideConfigFragmentFactory.create(configViewModelProvider, provideConfigFragmentXmlProvider));
      this.systemConfigViewModelProvider = SystemConfigViewModel_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider);
      this.provideSystemConfigFragmentXmlProvider = DoubleCheck.provider(FxAppModule_ProvideSystemConfigFragmentXmlFactory.create());
      this.provideSystemConfigFragmentProvider = DoubleCheck.provider(FxAppModule_ProvideSystemConfigFragmentFactory.create(systemConfigViewModelProvider, provideSystemConfigFragmentXmlProvider, DaggerAppComponent.this.provideLoggerProvider));
      this.provideRunActivityProvider = DoubleCheck.provider(FxAppModule_ProvideRunActivityFactory.create(runViewModelProvider, provideMainLoaderProvider, provideTalkListFragmentProvider, provideConfigFragmentProvider, provideSystemConfigFragmentProvider));
    }

    @Override
    public SettingActivity getSettingActivity() {
      return provideSettingActivityProvider.get();
    }

    @Override
    public RunActivity getRunActivity() {
      return provideRunActivityProvider.get();
    }
  }

  private final class ActivityComponentFactory implements ActivityComponent.Factory {
    @Override
    public ActivityComponent create(CommonConfigModel arg0) {
      Preconditions.checkNotNull(arg0);
      return new ActivityComponentImpl(arg0);
    }
  }

  private final class ActivityComponentImpl implements ActivityComponent {
    private final CommonConfigModel arg0;

    private Provider<MainActivity> mainActivityProvider;

    private ActivityComponentImpl(CommonConfigModel arg0Param) {
      this.arg0 = arg0Param;
      initialize(arg0Param);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final CommonConfigModel arg0Param) {
      this.mainActivityProvider = DoubleCheck.provider(MainActivity_Factory.create(DaggerAppComponent.this.provideMainSchedulerProvider, DaggerAppComponent.this.provideBackgroundSchedulerProvider, DaggerAppComponent.this.provideRxBusProvider, DaggerAppComponent.this.provideLoggerProvider, DaggerAppComponent.this.provideGsonProvider, DaggerAppComponent.this.provideGetDeviceUidInteractorImplProvider, DaggerAppComponent.this.appCommonProvider));
    }

    @Override
    public CommonConfigModel commonConfigModel() {
      return arg0;
    }

    @Override
    public MainActivity getMainActivity() {
      return mainActivityProvider.get();
    }
  }
}
