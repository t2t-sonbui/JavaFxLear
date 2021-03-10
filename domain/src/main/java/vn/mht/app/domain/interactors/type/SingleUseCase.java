package vn.mht.app.domain.interactors.type;


import io.reactivex.Single;

public interface SingleUseCase<T> {

    Single<T> execute();
}
