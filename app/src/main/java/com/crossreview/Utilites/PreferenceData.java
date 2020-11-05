package com.crossreview.Utilites;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceData {
    private SharedPreferences preferences;

    public PreferenceData(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getValueFromKey(String key) {
        return preferences.getString(key,null);
    }

    public int getValueIntFromKey(String key) {
        return preferences.getInt(key, -1);
    }

    public long getValueLongFromKey(String key) {
        return preferences.getLong(key, -1);
    }

    /**
     * getting boolean value from key
     */
    public boolean getValueBooleanFromKey(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * set String value
     */
    public PreferenceData setValue(String key, String value) {
        if (value == null)
            preferences.edit().remove(key).apply();
        else
            preferences.edit().putString(key, value).apply();
        return this;
    }

    /**
     * set int value
     */
    public PreferenceData setValueInt(String key, int value) {
        preferences.edit().putInt(key, value).apply();
        return this;
    }

    /**
     * set int value
     */
    public PreferenceData setValueLong(String key, long value) {
        preferences.edit().putLong(key, value).apply();
        return this;
    }

    /**
     * set boolean value
     */
    public PreferenceData setValueBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
        return this;
    }

}