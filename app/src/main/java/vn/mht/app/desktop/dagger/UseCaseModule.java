package vn.mht.app.desktop.dagger;

import dagger.Module;
import dagger.Provides;
import vn.mht.app.domain.interactors.use.data.impl.GetDeviceUidInteractorImpl;
import vn.mht.app.domain.interactors.use.data.impl.PrepareLoadConfigInteractorImpl;
import vn.mht.app.domain.repository.DataRepository;

import javax.inject.Singleton;


@Module
public final class UseCaseModule {
////Dung Binds/abstract de khi co mot cai da biet duoc khoi tao voi inject trong ham tao(HelloWorldCommand) de tra ve 1 cai chua ro nhu 1 interface (Command)
//@Binds
//abstract Command helloWorldCommand(HelloWorldCommand command);
////Dung Provides  khi can 1 ham cu the de tra ve mot doi tuong. chu y voi co static va ko co static. Xem config module voi khong co static khi can bien trong thoi gian cahy
////

    @Provides
    @Singleton
    static PrepareLoadConfigInteractorImpl providePrepareLoadConfigInteractorImpl(final DataRepository repository) {
        return new PrepareLoadConfigInteractorImpl(repository);
    }

//    //Data
//    @Provides
//    @Singleton
//    static DownloadFileInteractorImpl provideDonwloadFileInteractorImpl(final DataRepository repository) {
//        return new DownloadFileInteractorImpl(repository);
//    }
//
//    @Provides
//    @Singleton
//    static DownloadUpdateInteractorImpl provideDownloadUpdateInteractor(final DataRepository repository) {
//        return new DownloadUpdateInteractorImpl(repository);
//    }
//
//    @Provides
//    @Singleton
//    static ExecuteCmdInteractorImpl provideExecuteCmdInteractor(final DataRepository repository) {
//        return new ExecuteCmdInteractorImpl(repository);
//    }

    @Provides
    @Singleton
    static GetDeviceUidInteractorImpl provideGetDeviceUidInteractorImpl(final DataRepository repository) {
        return new GetDeviceUidInteractorImpl(repository);
    }
}

