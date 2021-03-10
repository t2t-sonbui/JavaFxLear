package vn.mht.app.desktop.app;


import dagger.Module;
import dagger.Provides;
import javafx.fxml.FXMLLoader;
import vn.mht.app.desktop.Main;
import vn.mht.app.desktop.app.run.RunActivity;
import vn.mht.app.desktop.app.run.RunViewModel;
import vn.mht.app.desktop.app.run.node.config.ConfigFragment;
import vn.mht.app.desktop.app.run.node.config.ConfigViewModel;
import vn.mht.app.desktop.app.run.node.room.TalkListFragment;
import vn.mht.app.desktop.app.run.node.room.TalkListViewModel;
import vn.mht.app.desktop.app.run.node.system.SystemConfigFragment;
import vn.mht.app.desktop.app.run.node.system.SystemConfigViewModel;
import vn.mht.app.desktop.app.setting.SettingActivity;
import vn.mht.app.desktop.app.setting.SettingViewModel;
import vn.mht.app.desktop.dagger.qualifier.fxml.MainFxml;
import vn.mht.app.desktop.dagger.qualifier.fxml.PortConfigFxml;
import vn.mht.app.desktop.dagger.qualifier.fxml.SystemConfigFxml;
import vn.mht.app.desktop.dagger.qualifier.fxml.TalkListFxml;
import vn.mht.app.desktop.dagger.scope.PerActivity;


import javax.inject.Provider;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

//@Module
@Module(includes = {FxmLoadersModule.class})
public abstract class FxAppModule {

//    @Provides
//    @PerActivity
//    static ResourceBundle provideResourceBundle() {
//        try {
//            return ResourceBundle.getBundle("ui");
//        } catch (MissingResourceException e) {
//            return ResourceBundle.getBundle("ui", Locale.ENGLISH);
//        }
//    }
//
//    @Provides
//    @PerActivity
//    static Function<URL, FXMLLoader> provideFxmlLoaderFactory(ResourceBundle resourceBundle) {
//        return url -> {
//            FXMLLoader loader = new FXMLLoader(url, resourceBundle);
//            return loader;
//        };
//    }


    @Provides
    @PerActivity
    static SettingActivity provideSettingActivity(SettingViewModel viewModel) {
        return new SettingActivity(viewModel);
    }


    @Provides
    @PerActivity
    @MainFxml
    static FXMLLoader provideMainLoader() {
        return new FXMLLoader(RunActivity.class.getResource("/fxml/Main.fxml"));
    }

    @Provides
    @PerActivity
    static RunActivity provideRunActivity(RunViewModel viewModel, @MainFxml FXMLLoader loader, TalkListFragment talkListFragment, ConfigFragment configFragment, SystemConfigFragment systemFragment) {
        return new RunActivity(viewModel, loader, talkListFragment, configFragment, systemFragment);
    }

//    static RunActivity provideRunActivity(RunViewModel viewModel, Map<Class<?>, FXMLLoader> fxmlLoaderMap) {
//        return new RunActivity(viewModel, fxmlLoaderMap.get(RunActivity.class));
//    }

    @Provides
    @PerActivity
    @TalkListFxml
    static FXMLLoader provideTalkList() {
        return new FXMLLoader(TalkListFragment.class.getResource("/fxml/talk/ListTalk.fxml"));
    }

    @Provides
    @PerActivity
    static TalkListFragment provideTalkListFragment(TalkListViewModel viewModel, @TalkListFxml FXMLLoader loader) {
        return new TalkListFragment(viewModel, loader);
    }

    @Provides
    @PerActivity
    @PortConfigFxml
    static FXMLLoader provideConfigFragmentXml() {
        return new FXMLLoader(ConfigFragment.class.getResource("/fxml/config/PortSetting.fxml"));
    }

    @Provides
    @PerActivity
    static ConfigFragment provideConfigFragment(ConfigViewModel viewModel, @PortConfigFxml FXMLLoader loader) {
        return new ConfigFragment(viewModel, loader);
    }

    @Provides
    @PerActivity
    @SystemConfigFxml
    static FXMLLoader provideSystemConfigFragmentXml() {
        return new FXMLLoader(ConfigFragment.class.getResource("/fxml/config/SystemSetting.fxml"));
    }

    @Provides
    @PerActivity
    static SystemConfigFragment provideSystemConfigFragment(SystemConfigViewModel viewModel, @SystemConfigFxml FXMLLoader loader, org.apache.log4j.Logger logger) {
        return new SystemConfigFragment(viewModel, loader, logger);
    }

}
