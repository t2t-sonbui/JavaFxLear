package vn.mht.app.domain.repository;

import io.reactivex.Single;

import java.util.List;

public interface DeviceRepository {
    Single<List<String>> getSerialPortNames();
}
