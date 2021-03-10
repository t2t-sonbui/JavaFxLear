package vn.mht.app.domain.repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import vn.mht.app.domain.model.ProductBuildModel;


import java.io.File;
import java.util.List;
import java.util.Map;


public interface DataRepository {
    Single<Map<String, Object>> getYamlConfig(String yamlFile);

    Single<String> getDeviceUid();

    Single<ProductBuildModel> getLatestProductBuild();
}
