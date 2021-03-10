package vn.mht.app.desktop.app;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javafx.fxml.FXMLLoader;
import vn.mht.app.desktop.app.run.RunActivity;
import vn.mht.app.desktop.dagger.qualifier.fxml.MainFxml;

@Module
abstract class FxmLoadersModule {
    @Binds
    @IntoMap
    @ClassKey(RunActivity.class)
    abstract FXMLLoader bindRunActivityFXMLoader(@MainFxml FXMLLoader fxmlLoader);
}
