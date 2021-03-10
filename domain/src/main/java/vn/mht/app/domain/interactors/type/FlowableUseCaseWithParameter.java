package vn.mht.app.domain.interactors.type;


import io.reactivex.Flowable;

public interface FlowableUseCaseWithParameter<P, R> {

    Flowable<R> execute(P parameter);
}
