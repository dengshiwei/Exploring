package com.exploring.pluginmodule;

import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

    public static void goMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
