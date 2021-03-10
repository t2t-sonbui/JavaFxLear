package vn.mht.app.domain.interactors.type;


import io.reactivex.Observable;

public interface ObservableUseCaseWithParameter<P, R> {

    Observable<R> execute(P parameter);
}
