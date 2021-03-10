package vn.mht.app.domain.interactors.type;


import io.reactivex.Flowable;

public interface FlowableUseCase<T> {

    Flowable<T> execute();
}
