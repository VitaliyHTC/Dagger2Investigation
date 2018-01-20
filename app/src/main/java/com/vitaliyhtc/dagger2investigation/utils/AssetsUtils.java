package com.vitaliyhtc.dagger2investigation.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public abstract class AssetsUtils {

    private AssetsUtils() {
        throw new AssertionError("No need to create instance of this class");
    }

    public static String readStringFromAsset(Context context, String assetNAme) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(assetNAme);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
