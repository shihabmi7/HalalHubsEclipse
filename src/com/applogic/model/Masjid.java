package com.applogic.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Masjid implements Serializable {

	private String name;
	private String Lat;
	private String Long;
	private String distance;
	private String area;
	private String image_Url;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return Lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		Lat = lat;
	}

	/**
	 * @return the long
	 */
	public String getLong() {
		return Long;
	}

	/**
	 * @param _long
	 *            the long to set
	 */
	public void setLong(String _long) {
		Long = _long;
	}

	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the image_Url
	 */
	public String getImage_Url() {
		return image_Url;
	}

	/**
	 * @param image_Url
	 *            the image_Url to set
	 */
	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}

}
