package vn.mht.app.desktop.dagger;


import dagger.BindsInstance;
import dagger.Component;
import vn.mht.app.desktop.ActivityBindingModule;
import vn.mht.app.desktop.AppCommon;

import vn.mht.app.desktop.MainBusiness;
import vn.mht.app.desktop.app.FxAppComponent;
import vn.mht.app.desktop.app.StageBuilderModule;
import vn.mht.app.desktop.dagger.module.ThreadingModule;
import vn.mht.app.domain.interactors.use.data.impl.PrepareLoadConfigInteractorImpl;

import javax.inject.Singleton;


@Singleton
//We also annotate our @Component type with @Singleton to declare that instances of classes annotated with @Singleton should be shared among other objects that depend on them in this component.
@Component(modules = {
        ConfigModule.class,
        AppModule.class,
        UseCaseModule.class,
        ThreadingModule.class,
        ActivityBindingModule.class,//Them de bind subcomponent
        StageBuilderModule.class,//Them de bind Fxsubcomponent
})
public interface AppComponent {
    //Them Builder thay cho khoi tao ConfigModule.class
    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder mainClass(Class mainClazz);
    }

    FxAppComponent.Builder fxAppComponent();

    org.apache.log4j.Logger logger();

    AppCommon appCommon();

    MainBusiness mainBusiness();

    PrepareLoadConfigInteractorImpl getPrepareLoadConfigInteractor();
}
