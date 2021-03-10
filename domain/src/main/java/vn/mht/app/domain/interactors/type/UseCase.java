package vn.mht.app.domain.interactors.type;


import io.reactivex.Observable;

public interface UseCase<T> {

    Observable<T> execute();
}
