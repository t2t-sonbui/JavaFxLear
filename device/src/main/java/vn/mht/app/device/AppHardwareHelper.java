package vn.mht.app.device;

import com.fazecast.jSerialComm.SerialPort;
import io.reactivex.Single;
import jssc.SerialPortList;
import vn.mht.app.domain.OsUtils;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AppHardwareHelper implements HardwareHelper {
    private final org.apache.log4j.Logger mLogger;

    public AppHardwareHelper(final org.apache.log4j.Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<List<String>> getSerialPortNames() {
        return Single.create(emitter -> {
            if (OsUtils.isWindows()) {
                com.fazecast.jSerialComm.SerialPort[] commPorts = com.fazecast.jSerialComm.SerialPort.getCommPorts();
                List<String> list = new ArrayList<>();
                for (SerialPort serialPort : commPorts) {
                    list.add(serialPort.getSystemPortName());
                }
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(list);
                }
            } else {
                //  JSSC is Java Simple Serial Connector
                String[] dev_list = SerialPortList.getPortNames();
                List<String> list = new ArrayList<>();
                for (String serialPort : dev_list) {
                    list.add(serialPort);
                }
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(list);
                }
            }

        });
    }
}
