package vn.mht.app.desktop.dagger;


import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import org.yaml.snakeyaml.Yaml;
import vn.mht.app.data.DataRepositoryImpl;
import vn.mht.app.data.local.PreferencesHelper;
import vn.mht.app.desktop.Main;
import vn.mht.app.desktop.RxBus;
import vn.mht.app.desktop.dagger.qualifier.thread.ThreadBackground;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Properties;

import vn.mht.app.domain.model.ProductBuildModel;
import vn.mht.app.domain.repository.DataRepository;


@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static ProductBuildModel provideSoftwareInfo() {
        return new ProductBuildModel("1.0.0", 1);
    }

    @Singleton
    @Provides
    static org.apache.log4j.Logger provideLogger() {
        //        Logger logger = LoggerFactory.getLogger(Application.class);
        return org.apache.log4j.Logger.getLogger(Main.class);//Only this can write File
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        return new Gson();//
    }

    @Singleton
    @Provides
    static Yaml provideYaml() {
        return new Yaml();
    }

    @Singleton
    @Provides
    static Properties provideProperties() {
        return new Properties();
    }

    @Provides
    @Singleton
    static DataRepository provideDataRepository(final PreferencesHelper preferencesHelper, final Gson gson, final ProductBuildModel productBuildModel, final org.apache.log4j.Logger logger) {
        return new DataRepositoryImpl(preferencesHelper, gson, productBuildModel, logger);
    }
//
//    @Provides
//    @Singleton
//    static HardwareHelper provideHardwareHelper(final GpioController gpioController, final Map<String, GpioPinDigitalOutput> outputPins, final Map<String, GpioPinDigitalInput> inputPins, @UseSimulatorIO boolean simulator) {
//        return new AppHardwareHelper(gpioController, outputPins, inputPins, simulator);
//    }
//
//    @Provides
//    @Singleton
//    static DeviceRepository provideDeviceRepository(final HardwareHelper hardwareHelper) {
//        return new DeviceRepositoryImpl(hardwareHelper);
//    }
//
//    @Provides
//    @Singleton
//    static BusinessRepository provideSocketRepository(final MediaPlayerHelper mediaPlayerHelper, final ScheduleJobHelper scheduleJobHelper, final MqttConnectionHelper mqttConnectionHelper) {
//        return new BusinessRepositoryImpl(mediaPlayerHelper, scheduleJobHelper, mqttConnectionHelper);
//    }

    @Provides
    @Singleton
    static RxBus provideRxBus(@ThreadBackground final Scheduler backgroundThread) {
        return new RxBus(backgroundThread);
    }

}
