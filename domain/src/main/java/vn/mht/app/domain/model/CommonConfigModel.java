package vn.mht.app.domain.model;

import java.util.List;

public class CommonConfigModel {
    private String deviceUid;
    private boolean limitEnable = false;//Limit by licence fail

    public String getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(String deviceUid) {
        this.deviceUid = deviceUid;
    }


    public boolean isLimitEnable() {
        return limitEnable;
    }

    public void setLimitEnable(boolean limitEnable) {
        this.limitEnable = limitEnable;
    }
}
