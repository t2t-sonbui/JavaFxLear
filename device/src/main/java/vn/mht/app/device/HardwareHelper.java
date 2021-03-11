package vn.mht.app.device;

import io.reactivex.Single;

import java.util.List;

public interface HardwareHelper {
    Single<List<String>> getSerialPortNames();
}
