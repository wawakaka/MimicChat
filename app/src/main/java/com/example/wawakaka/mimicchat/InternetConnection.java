package com.example.wawakaka.mimicchat;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Wawakaka on 3/25/2017.
 */

public class InternetConnection {
    public static boolean checkConnection(Context context) {
        return  ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

}
