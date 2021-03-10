package vn.mht.app.domain.interactors.use.data.impl;

import io.reactivex.Single;
import vn.mht.app.domain.interactors.use.data.PrepareLoadConfigInteractor;
import vn.mht.app.domain.repository.DataRepository;


import java.util.Map;

public class PrepareLoadConfigInteractorImpl implements PrepareLoadConfigInteractor<PrepareLoadConfigInteractorImpl.Params, Map<String, Object>> {
    private final DataRepository mDataRepository;

    public PrepareLoadConfigInteractorImpl(final DataRepository repository) {
        mDataRepository = repository;
    }

    @Override
    public Single<Map<String, Object>> execute(Params parameter) {
        return mDataRepository.getYamlConfig(parameter.configFile);
    }

    public static final class Params {
        private final String configFile;

        public Params(final String file) {
            this.configFile = file;
        }

        public static Params forConfigFile(String file) {
            return new Params(file);
        }
    }
}
