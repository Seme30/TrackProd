package com.example.trackprod;

import android.content.Context;
import android.content.SharedPreferences;

public enum Prefs {
    INSTANCE;
    private static final String ApparelErp = "TrackProd";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public void initPrefs(Context context) {
        preferences = context.getSharedPreferences(ApparelErp, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
    }

    public String getFirstName() {
        return preferences.getString(PrefKey.firstName.toString(), "");
    }

    public void setFirstName(String firstName) {
        editor.putString(PrefKey.firstName.toString(), firstName);
        editor.commit();
    }

    public String getLastName() {
        return preferences.getString(PrefKey.lastName.toString(), "");
    }

    public void setLastName(String lastName) {
        editor.putString(PrefKey.lastName.toString(),lastName);
        editor.commit();
    }

    public String getRole() {
        return preferences.getString(PrefKey.role.toString(), "");
    }

    public void setRole(String role) {
        editor.putString(PrefKey.role.toString(),role);
        editor.commit();
    }

    public String getUserPhone() {
        return preferences.getString(PrefKey.userPhone.toString(), "");
    }

    public void setUserPhone(String phone) {
        editor.putString(PrefKey.userPhone.toString(), phone);
        editor.commit();
    }

    public String getUserOtp() {
        return preferences.getString(PrefKey.userOtp.toString(), "");
    }

    public void setUserOtp(String userOtp) {
        editor.putString(PrefKey.userOtp.toString(), userOtp);
        editor.commit();
    }

    public String getID() {
        return preferences.getString(PrefKey.id.toString(), "");
    }

    public void setID(String id) {
        editor.putString(PrefKey.id.toString(), id);
        editor.commit();
    }

    public String getOldPassowrd() {
        return preferences.getString(PrefKey.oldpassword.toString(), "");
    }

    public void setOldPassword(String oldpassword) {
        editor.putString(PrefKey.oldpassword.toString(), oldpassword);
        editor.commit();
    }

    public boolean isLogin() {
        return preferences.getBoolean(PrefKey.isLogin.toString(), false);
    }

    public void setLoginStatus(boolean loginStatus) {
        editor.putBoolean(PrefKey.isLogin.toString(), loginStatus);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    enum PrefKey {
        userPhone,
        userOtp,
        isLogin,
        firstName,
        lastName,
        id,
        oldpassword,
        role
    }
}
