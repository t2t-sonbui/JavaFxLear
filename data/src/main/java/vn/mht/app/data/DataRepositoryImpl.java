package vn.mht.app.data;

import com.google.gson.Gson;
import io.reactivex.Single;
import vn.mht.app.data.local.PreferencesHelper;
import vn.mht.app.domain.model.ProductBuildModel;
import vn.mht.app.domain.repository.DataRepository;

import java.util.Map;

public class DataRepositoryImpl implements DataRepository {
    private final PreferencesHelper mPreferencesHelper;
    private final Gson mGson;
    private final ProductBuildModel mProductBuild;
    private org.apache.log4j.Logger mLogger;

    public DataRepositoryImpl(PreferencesHelper preferencesHelper, Gson gson, ProductBuildModel productBuildModel, org.apache.log4j.Logger logger) {
        mPreferencesHelper = preferencesHelper;
        mGson = gson;
        mLogger = logger;
        mProductBuild = productBuildModel;
    }

    @Override
    public Single<Map<String, Object>> getYamlConfig(String yamlFile) {
        return mPreferencesHelper.getYamlConfig(yamlFile);
    }

    @Override
    public Single<String> getDeviceUid() {
//        AtomicBoolean retry = new AtomicBoolean(false);
//        if (OsUtils.isWindows()) {
//            return Single.fromCallable(() -> {
//                OperatingSystem operatingSystem = mSystemInfo.getOperatingSystem();
//                HardwareAbstractionLayer hardwareAbstractionLayer = mSystemInfo.getHardware();
//                CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
//                ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
//
//                String vendor = operatingSystem.getManufacturer();
//                String processorSerialNumber = computerSystem.getSerialNumber();
//                String processorIdentifier = centralProcessor.getProcessorIdentifier().getIdentifier();
//                int processors = centralProcessor.getLogicalProcessorCount();
//
//                String delimiter = "-";
//                String hwUUID = delimiter
//                        + String.format("%08x", vendor.hashCode()) + delimiter
//                        + String.format("%08x", processorSerialNumber.hashCode()) + delimiter
//                        + String.format("%08x", processorIdentifier.hashCode())
//                        + delimiter + processors + delimiter + "PiAudioBox";//PiAudioBox ma cho cac san pham khac nhau
//
//                String md5Hex = DigestUtils.md5Hex(hwUUID).toUpperCase();
//                return md5Hex;
////            return "fb9240a0-92fb-470e-90d2-b7da0464e733";
//            });
//        }
//        return getAllDeviceConnectState(null)
//                .map(nmcliDeviceStatusModels -> {
//                    List<String> devices = new ArrayList<>();
//                    for (NmcliDeviceStatusModel nmcliDeviceStatusModel : nmcliDeviceStatusModels) {
//                        if (nmcliDeviceStatusModel.getType().equalsIgnoreCase("wifi")) {
//                            devices.add(nmcliDeviceStatusModel.getDevice());
//                        }
//                    }
//                    if (devices.size() > 0) {
//                        Collections.sort(devices);
//                        return devices;
//                    }
//                    devices.clear();
//                    //Find ethernet
//                    for (NmcliDeviceStatusModel nmcliDeviceStatusModel : nmcliDeviceStatusModels) {
//                        if (nmcliDeviceStatusModel.getType().equalsIgnoreCase("ethernet")) {
//                            devices.add(nmcliDeviceStatusModel.getDevice());
//                        }
//                    }
//                    Collections.sort(devices);
//                    return devices;
//
//                })
//                .flatMap(devices -> queryMacAddress(null, devices.get(0)))
//                .flatMap(macUiD -> Single.fromCallable(() -> {
//                    mLogger.debug("macUiD:" + macUiD);
//                    OperatingSystem operatingSystem = mSystemInfo.getOperatingSystem();
//                    HardwareAbstractionLayer hardwareAbstractionLayer = mSystemInfo.getHardware();
//                    CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
//                    ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
//
//                    String vendor = operatingSystem.getManufacturer();
//                    String processorSerialNumber = computerSystem.getSerialNumber();
//                    String processorIdentifier = centralProcessor.getProcessorIdentifier().getIdentifier();
//                    int processors = centralProcessor.getLogicalProcessorCount();
//
//                    String delimiter = "-";
//                    String hwUUID = macUiD + delimiter
//                            + String.format("%08x", vendor.hashCode()) + delimiter
//                            + String.format("%08x", processorSerialNumber.hashCode()) + delimiter
//                            + String.format("%08x", processorIdentifier.hashCode())
//                            + delimiter + processors + delimiter + "PiAudioBox";//PiAudioBox ma cho cac san pham khac nhau
//
//                    String md5Hex = DigestUtils.md5Hex(hwUUID).toUpperCase();
//                    return md5Hex;
////            return "fb9240a0-92fb-470e-90d2-b7da0464e733";
//                }))
//                .retryWhen(errors -> {
//                    return errors
//                            .flatMap(error -> {
//                                if (error.getMessage().equals("FAIL_VALID_MAC")) {
//                                    if (retry.compareAndSet(false, true)) {
//                                        return Flowable.timer(5, TimeUnit.SECONDS);
//                                    }
//                                }
//                                return Flowable.error(error);
//                            });
//                });

        //Fix hardcode
        return mPreferencesHelper.getDeviceIdHardcode()
                .onErrorResumeNext(throwable -> Single.just("UnAssign"));
    }

    @Override
    public Single<ProductBuildModel> getLatestProductBuild() {
        return null;//mHttpDataHelper.getLatestBuildInformation();
    }
}
