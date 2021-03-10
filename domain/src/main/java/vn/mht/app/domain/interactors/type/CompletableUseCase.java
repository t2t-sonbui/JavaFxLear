package vn.mht.app.domain.interactors.type;


import io.reactivex.Completable;

public interface CompletableUseCase {

    Completable execute();
}
