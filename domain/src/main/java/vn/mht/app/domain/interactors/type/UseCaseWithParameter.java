package vn.mht.app.domain.interactors.type;


import io.reactivex.Observable;

public interface UseCaseWithParameter<P, R> {

    Observable<R> execute(P parameter);
}
