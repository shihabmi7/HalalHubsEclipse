package org.halfcycle.network;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.applogic.model.PrayerTime;
import com.applogic.utility.ApplicationData;

public class GetPrayerTimeFromAPI extends BaseTask {

	final static String URL = "http://api.xhanch.com/islamic-get-prayer-time.php?lng=127&lat=90&yy=2010&mm=3&gmt=2&m=json";
	String start = "1";
	String requestString = "";
	public String jsonData = "";
	public String Message = "";
	public boolean Status = false;
	String email_ = "";
	String keyword;

	static String FAJR = "fajr";
	static String SUNRISE = "sunrise";
	static String ZUHR = "zuhr";
	static String ASR = "asr";
	static String MAGRIB = "maghrib";
	static String ISHA = "isha";

	public GetPrayerTimeFromAPI(Context ctx, boolean displayProgress) {

		super(ctx, displayProgress);
	}

	@Override
	boolean task() {

		try {

			HttpRequest request = HttpRequest.get(URL);
			jsonData = request.body();

			// System.out.println(jsonData);

			Log.d("FEED", jsonData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	@Override
	boolean taskOnComplete() {

		ApplicationData.prayerTimeList.clear();

		try {

			JSONObject jObj = new JSONObject(jsonData);

			for (int i = 0; i < 31; i++) {
				if (jObj.has("" + i)) {
					JSONObject jobj2 = jObj.getJSONObject("" + i);

					PrayerTime pt = new PrayerTime();
					pt.setFajr(jobj2.getString(FAJR));
					pt.setSunrise(jobj2.getString(SUNRISE));
					pt.setJohr(jobj2.getString(ZUHR));
					pt.setAsar(jobj2.getString(ASR));
					pt.setMagrib(jobj2.getString(MAGRIB));
					pt.setEisha(jobj2.getString(ISHA));

					Log.e("PrayerTime",
							"Day-" + i + " FAJR TIME: " + pt.getFajr());
					ApplicationData.prayerTimeList.add(pt);

				}
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
