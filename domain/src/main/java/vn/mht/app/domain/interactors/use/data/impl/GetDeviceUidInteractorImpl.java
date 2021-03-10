package vn.mht.app.domain.interactors.use.data.impl;


import io.reactivex.Single;
import vn.mht.app.domain.interactors.use.data.GetDeviceUidInteractor;
import vn.mht.app.domain.repository.DataRepository;


public class GetDeviceUidInteractorImpl implements GetDeviceUidInteractor<String> {
    private final DataRepository mDataRepository;

    public GetDeviceUidInteractorImpl(final DataRepository repository) {
        mDataRepository = repository;
    }

    @Override
    public Single<String> execute() {
        return mDataRepository.getDeviceUid();
    }
}
