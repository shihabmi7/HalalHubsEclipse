package com.alhikmah.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreferenceValues {

	private SharedPreferences mPrefs;

	public AppPreferenceValues(Context context) {
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public SharedPreferences getPrefs() {
		return mPrefs;
	}

	public long getUser_id() {
		return mPrefs.getLong("user_id", 0);
	}

	public void setUser_id(long user_id) {
		mPrefs.edit().putLong("user_id", user_id).commit();
	}

	public String getUsername() {
		return mPrefs.getString("username", "");
	}

	public void setUsername(String username) {
		mPrefs.edit().putString("username", username).commit();
	}

	public String getPassword() {
		return mPrefs.getString("password", "");
	}

	public void setPassword(String password) {
		mPrefs.edit().putString("password", password).commit();
	}

	public String getActivityName() {
		return mPrefs.getString("activityName", "");
	}

	public void setActivityName(String activityName) {
		mPrefs.edit().putString("activityName", activityName).commit();
	}

	
	public String getLanguage() {
		return mPrefs.getString("language", "");
	}

	public void setLanguage(String language) {
		mPrefs.edit().putString("language", language).commit();
	}
}