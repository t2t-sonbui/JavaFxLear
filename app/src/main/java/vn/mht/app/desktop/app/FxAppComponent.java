package vn.mht.app.desktop.app;

import dagger.BindsInstance;
import dagger.Subcomponent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import vn.mht.app.desktop.app.run.RunActivity;
import vn.mht.app.desktop.app.setting.SettingActivity;
import vn.mht.app.desktop.dagger.scope.PerActivity;


import java.net.URL;
import java.util.Objects;
import java.util.function.Function;

@PerActivity
@Subcomponent(modules = {FxAppModule.class})
public interface FxAppComponent {
//    Function<URL, FXMLLoader> fxmlLoaderFactory();
//
//    default FXMLLoader loader(URL fxmlUrl) {
//        return fxmlLoaderFactory().apply(Objects.requireNonNull(fxmlUrl));
//    }

    SettingActivity getSettingActivity();

    RunActivity getRunActivity();

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        FxAppComponent build();
    }
}
