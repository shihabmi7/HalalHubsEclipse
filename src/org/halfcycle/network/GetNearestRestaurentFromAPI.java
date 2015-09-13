package org.halfcycle.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.applogic.model.Restaurent;
import com.applogic.utility.ApplicationData;

public class GetNearestRestaurentFromAPI extends BaseTask {

	String start = "1";
	String requestString = "";
	public String jsonData = "";
	public String Message = "";
	public boolean Status = false;

	public String TAGS = "tags";
	public String PROFILE = "profile";
	public String NAME = "name";
	public String EMAIL = "email";
	public String ADDRESS = "address";
	public String POSTCODE = "postalCode";
	public String FOOD_TYPE_TAG = "tag";
	public String FOOD_LOGO_IMAGE = "logoImage";
	public String STATUS = "status";
	public String OPENING_TIME = "openingTime";
	public String CLOSING_TIME = "closingTime";

	// "openingTime": "1970-01-01T04:00:00.000Z",
	// "closingTime": "1970-01-01T16:00:00.000Z",
	// "deliveryStartTime": "1970-01-01T05:00:00.000Z",
	// "deliveryEndTime"

	// logoImage

	public GetNearestRestaurentFromAPI(Context ctx, boolean displayProgress) {

		super(ctx, displayProgress);

	}

	@Override
	boolean task() {

		try {

			HttpRequest request = HttpRequest
					.get(ApplicationData.SHOW_ALL_RESTAURENT);
			jsonData = request.body();

			// System.out.println(jsonData);

			Log.e("HALAL RESPONSE", jsonData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	@Override
	boolean taskOnComplete() {

		ApplicationData.restaurentList.clear();

		try {

			// JSONObject jObject = new JSONObject(jsonData);

			JSONArray jsonArray = new JSONArray(jsonData);

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jobj2 = jsonArray.getJSONObject(i);

				Restaurent resObj = new Restaurent();
				resObj.setRestaurentName(jobj2.getString(NAME));
				resObj.setAddress(jobj2.getString(ADDRESS));
				resObj.setEmail(jobj2.getString(EMAIL));
				resObj.setStatus(jobj2.getString(STATUS));
				resObj.setOpening_Time(jobj2.getString(OPENING_TIME));
				resObj.setClosing_Time(jobj2.getString(CLOSING_TIME));

				Log.e("Nearest Res", "Res: >> " + resObj.getRestaurentName());

				if (jobj2.has(TAGS)) {

					JSONArray array = jobj2.getJSONArray(TAGS);

					for (int j = 0; j < array.length(); j++) {

						JSONObject jObj3 = array.getJSONObject(j);

						String tag = jObj3.getString(FOOD_TYPE_TAG);

						Log.e(" fOOD tag >> ", tag);

						// photo_reference = jObj3.getString("photo_reference");
						// resObj.setImage_Url(PHOTO_URL_FIRST_PART
						// + photo_reference + "&" + key);
					}
				}
				if (jobj2.has(PROFILE)) {

					// JSONArray array = jobj2.getJSONArray(PROFILE);
					//
					JSONObject jObj3 = jobj2.getJSONObject(PROFILE);
					String image_url = jObj3.getString(FOOD_LOGO_IMAGE);
					resObj.setImage_Url(ApplicationData.BASE_URL + image_url);

					Log.e(" Res Image >> ", image_url);

				}
				/*
				 * else {
				 * 
				 * resObj.setImage_Url(jobj2.getString("icon")); }
				 */

				ApplicationData.restaurentList.add(resObj);

			}

		} catch (JSONException e) {

			e.printStackTrace();
		} catch (Exception e) {

		}

		return true;
	}

	@Override
	boolean taskOnFailure() {

		return false;

	}

	@Override
	public void setListener(CustomListener cust) {
		listener = cust;
	}
}
