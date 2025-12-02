package com.example.mobilethelp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "MobileThelp_Session";
    private static final String KEY_AUTH_TOKEN = "auth_token";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveAuthToken(String token) {
        editor.putString(KEY_AUTH_TOKEN, token);
        // Usar commit() para garantir que a operação seja síncrona
        editor.commit(); 
    }

    public String fetchAuthToken() {
        return prefs.getString(KEY_AUTH_TOKEN, null);
    }

    public void clearAuthToken() {
        editor.remove(KEY_AUTH_TOKEN);
        // Usar commit() para garantir que a operação seja síncrona
        editor.commit();
    }
}