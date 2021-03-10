
package vn.mht.app.data.local;


import io.reactivex.Single;


import java.util.List;
import java.util.Map;


public interface PreferencesHelper {

    Single<Map<String, Object>> getYamlConfig(String yamlFile);

    Single<String> getSourceDirPath();

    Single<String> getCurrentJarPath();

    Single<String> writeTextFile(String content, String fileNameWithoutPath);

    Single<String> readTextFile(String fileNameWithoutPath);

    Single<Boolean> deleteSourceDirPath();


    Single<String> getDeviceIdHardcode();

}
