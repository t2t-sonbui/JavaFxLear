package vn.mht.app.device;

import io.reactivex.Single;
import vn.mht.app.domain.repository.DeviceRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DeviceRepositoryImpl implements DeviceRepository {
    private final HardwareHelper mHardwareHelper;

    public DeviceRepositoryImpl(final HardwareHelper hardwareHelper) {
        mHardwareHelper = hardwareHelper;
    }

    @Override
    public Single<List<String>> getSerialPortNames() {
        return mHardwareHelper.getSerialPortNames();
    }
}
