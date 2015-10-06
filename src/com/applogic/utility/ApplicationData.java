package com.applogic.utility;

import java.util.ArrayList;

import com.applogic.model.Food;
import com.applogic.model.Masjid;
import com.applogic.model.PrayerTime;
import com.applogic.model.Restaurent;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ApplicationData {

	// GENERAL PRODUCT DETAILS
	public static ArrayList<String> GeoCodeTemp = new ArrayList<String>();

	public static ArrayList<PrayerTime> prayerTimeList = new ArrayList<PrayerTime>();
	public static ArrayList<Restaurent> restaurentList = new ArrayList<Restaurent>();
	public static ArrayList<Food> foodList = new ArrayList<Food>();
	public static ArrayList<Masjid> masjidList = new ArrayList<Masjid>();

	public static ImageLoader imageLoader = ImageLoader.getInstance();

	public static String GoogleMapKey = "AIzaSyCN_tzqvvH2YMCVVj3C96vKIVJjysh9CNs";
	
	public static final int MAX_NO_QUANTITY = 20 ;
	public static final int MIN_NO_QUANTITY = 1 ;
	
	public static final String CURRENCY = "BDT";

	public static String BASE_URL = "http://45.55.196.7:1337";
	public final static String SHOW_ALL_RESTAURENT = "http://45.55.196.7:1337/rest/searchRestaurant";
	public final static String SHOW_RESTAURENT_MENU = "http://45.55.196.7:1337/rest/restaurantMenu/all/with/menuItems";
	public final static String SHOW_FOOD_DETAILS = "http://45.55.196.7:1337/rest/foodItem/get/";

	// Restaurent FoodMenu

	// http://45.55.196.7:1337/rest/restaurantMenu/all/with/menuItems
	// http://45.55.196.7:1337/rest/foodItem/get/2

	public static ImageLoader ImageLoaderGetInstance() {

		return imageLoader;

	}
	// halal hubs

	// show all restaurent
	// http://45.55.196.7:1337/rest/restaurant/all/with/profile,category,tags

	// show menu of a restaurent

	// http://45.55.196.7:1337/rest/restaurantMenu/all/with/menuItems

}
