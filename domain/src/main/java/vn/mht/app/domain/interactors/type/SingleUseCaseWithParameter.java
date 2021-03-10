package vn.mht.app.domain.interactors.type;


import io.reactivex.Single;

public interface SingleUseCaseWithParameter<P, R> {

    Single<R> execute(P parameter);
}
