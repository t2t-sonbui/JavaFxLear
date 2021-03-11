package vn.mht.app.domain.interactors.use.device.impl;

import io.reactivex.Single;
import vn.mht.app.domain.interactors.use.device.GetSerialPortAvaliableInteractor;
import vn.mht.app.domain.repository.DeviceRepository;

import java.util.List;


public class GetSerialPortAvaliableInteractorImpl implements GetSerialPortAvaliableInteractor<List<String>> {
    private final DeviceRepository mBusinessRepository;

    public GetSerialPortAvaliableInteractorImpl(final DeviceRepository repository) {
        mBusinessRepository = repository;
    }

    @Override
    public Single<List<String>> execute() {
        return mBusinessRepository.getSerialPortNames();
    }

}