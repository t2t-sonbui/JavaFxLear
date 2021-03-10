package vn.mht.app.domain.model;


public class ProductBuildModel {
    private String versionName;
    private int versionCode;

    public ProductBuildModel(String version, int code) {
        this.versionName = version;
        this.versionCode = code;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
