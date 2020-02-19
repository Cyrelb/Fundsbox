package com.luxembourgaifm.fundsbox.model;

import java.util.List;

public class AssetsResponse {

    private boolean error;
    private List<Asset> assets;

    public AssetsResponse(boolean error, List<Asset> assets) {
        this.error = error;
        this.assets = assets;
    }

    public boolean isError() {
        return error;
    }

    public List<Asset> getAssets() {
        return assets;
    }
}
