package vn.mht.app.desktop.dagger;

import dagger.Module;
import dagger.Provides;
import org.yaml.snakeyaml.Yaml;
import vn.mht.app.data.local.AppPreferencesHelper;
import vn.mht.app.data.local.PreferencesHelper;

import javax.inject.Singleton;

@Module
public class ConfigModule {//Khong phai la abstact class vi duoc khoi tao trong thoi gian chay
    //Bo qua vi duoc thay bang BuilderComponent
//    private final Class mainClass;
//    public ConfigModule(final Class mainClazz) {
//        this.mainClass = mainClazz;
//    }
//
//    @Singleton
//    @Provides
//    PreferencesHelper providePreferencesHelper(final Yaml yaml, final org.apache.log4j.Logger logger) {
//        return new AppPreferencesHelper(yaml, mainClass, logger);
//    }

    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(Class mainClass, final Yaml yaml, final org.apache.log4j.Logger logger) {
        return new AppPreferencesHelper(yaml, mainClass, logger);
    }

}

