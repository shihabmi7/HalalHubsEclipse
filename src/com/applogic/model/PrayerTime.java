package com.applogic.model;

import java.io.Serializable;

import android.graphics.Bitmap;

@SuppressWarnings("serial")
public class PrayerTime implements Serializable {

	private String Fajr;
	private String Sunrise;
	private String Johr;
	private String Asar;
	private String Magrib;
	private String Eisha;
	private Bitmap image_Bitmap;

	public String getFajr() {
		return Fajr;
	}

	public void setFajr(String fajr) {
		Fajr = fajr;
	}

	/**
	 * @return the sunrise
	 */
	public String getSunrise() {
		return Sunrise;
	}

	/**
	 * @param sunrise
	 *            the sunrise to set
	 */
	public void setSunrise(String sunrise) {
		Sunrise = sunrise;
	}

	/**
	 * @return the johr
	 */
	public String getJohr() {
		return Johr;
	}

	/**
	 * @param johr
	 *            the johr to set
	 */
	public void setJohr(String johr) {
		Johr = johr;
	}

	/**
	 * @return the asar
	 */
	public String getAsar() {
		return Asar;
	}

	/**
	 * @param asar
	 *            the asar to set
	 */
	public void setAsar(String asar) {
		Asar = asar;
	}

	/**
	 * @return the eisha
	 */
	public String getEisha() {
		return Eisha;
	}

	/**
	 * @param eisha
	 *            the eisha to set
	 */
	public void setEisha(String eisha) {
		Eisha = eisha;
	}

	/**
	 * @return the magrib
	 */
	public String getMagrib() {
		return Magrib;
	}

	/**
	 * @param magrib
	 *            the magrib to set
	 */
	public void setMagrib(String magrib) {
		Magrib = magrib;
	}
}
