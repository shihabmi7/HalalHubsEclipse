package org.halfcycle.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.applogic.model.Masjid;
import com.applogic.utility.ApplicationData;

public class GetNearestMasjidFromAPI extends BaseTask {

	final static String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=23.0300,72.5800&radius=500&types=mosque&sensor=false&key=AIzaSyCPF03hvTSZT8L5nEsCPnhVE2CDtdf_cF0";
	final static String PHOTO_BASE_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=PA‌​SS PHOTO REFERENCE HERE&key=PASS API KEY HERE";

	static String PHOTO_URL_FIRST_PART = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";

	String start = "1";
	String requestString = "";
	public String jsonData = "";
	public String Message = "";
	public boolean Status = false;
	String email_ = "";
	String photo_reference = null;
	String key = "key=AIzaSyCPF03hvTSZT8L5nEsCPnhVE2CDtdf_cF0";

	public GetNearestMasjidFromAPI(Context ctx, boolean displayProgress) {

		super(ctx, displayProgress);

	}

	@Override
	boolean task() {

		try {

			HttpRequest request = HttpRequest.get(URL);
			jsonData = request.body();

			// System.out.println(jsonData);

			Log.e("GetNearest Masjid From API", jsonData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	@Override
	boolean taskOnComplete() {

		ApplicationData.masjidList.clear();

		try {

			JSONObject jObject = new JSONObject(jsonData);

			JSONArray jsonArray = jObject.getJSONArray("results");

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jobj2 = jsonArray.getJSONObject(i);

				Masjid masjid = new Masjid();
				masjid.setName(jobj2.getString("name"));

				if (jobj2.has("photos")) {

					JSONArray array = jobj2.getJSONArray("photos");

					// for (int j = 0; j < array.length(); j++) {

					JSONObject jObj3 = array.getJSONObject(0);

					photo_reference = jObj3.getString("photo_reference");
					Log.e("photo_reference", photo_reference);

					masjid.setImage_Url(PHOTO_URL_FIRST_PART + photo_reference
							+ "&" + key);

				} else {

					masjid.setImage_Url(jobj2.getString("icon"));
				}

				masjid.setArea(jobj2.getString("vicinity"));

				Log.e("Nearest Masjid", "Masjid: >> " + masjid.getName());

				ApplicationData.masjidList.add(masjid);

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
