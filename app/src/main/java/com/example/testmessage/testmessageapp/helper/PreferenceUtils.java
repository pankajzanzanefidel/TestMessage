package com.example.testmessage.testmessageapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {

	private static PreferenceUtils INSTANCE = null;
	private Context ctx = null;

	private PreferenceUtils(Context context){
		ctx = context;
	}
	public static PreferenceUtils getINSTANCE(Context context){
		if(INSTANCE == null){
			INSTANCE = new PreferenceUtils(context);
		}
		return INSTANCE;
	}
	private static String PREFERENCE_NAME = "TEST_MESSAGE";
	public static String PREF_JOB_ID = "PREF_JOB_ID";

	public void putString(String key, String value) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		edt.putString(key, value);
		edt.commit();
	}

	public String getString(String key, String defaultValue) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getString(key, defaultValue);
	}
	public String getString(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getString(key, null);
	}


	public void putInteger(String key, Integer value) {
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		if(value!=null){
			edt.putInt(key, value);
		}
		else{
			edt.remove(key);	
		}
		edt.commit();

	}

	public Integer getInteger(String key, int defaultValue) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getInt(key, defaultValue);
	}

	public Integer getInteger(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getInt(key, 0);
	}

	public void putLong(String key, Long value) {
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		edt.putLong(key, value);
		edt.commit();

	}

	public Long getLong(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getLong(key, 0);
	} 
	
	public void putFloat(String key, Float value) {
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		edt.putFloat(key, value);
		edt.commit();

	}

	public void putDouble(String key, double value) {
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		edt.putLong(key, Double.doubleToRawLongBits(value));
		edt.commit();

	}

	public Double getDouble(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return Double.longBitsToDouble(shared.getLong(key, 0));
	}

	public Float getFloat(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getFloat(key, 0);
	}

	public void putBoolean(String key, boolean value) {
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		Editor edt = shared.edit();
		edt.putBoolean(key, value);
		edt.commit();

	}

	public Boolean getBoolean(String key) {

		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		return shared.getBoolean(key, false);
	}

	public void remove(String key){
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		shared.edit().remove(key);
	}
	public void clearAllExceptFcmToken(){
		SharedPreferences shared = ctx.getSharedPreferences(PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		//String fcmToken = shared.getString(Constants.PreferenceConstants.FIREBASE_TOKEN,"");
		shared.edit().clear().apply();
		//putString(Constants.PreferenceConstants.FIREBASE_TOKEN,fcmToken);
	}
}
