package vn.mht.app.domain.interactors.type;


import io.reactivex.Observable;

public interface ObservableUseCase<T> {

    Observable<T> execute();
}
