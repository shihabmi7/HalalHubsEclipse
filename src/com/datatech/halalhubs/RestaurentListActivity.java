package com.datatech.halalhubs;

import java.util.ArrayList;

import org.halfcycle.network.CustomListener;
import org.halfcycle.network.GetNearestRestaurentFromAPI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.applogic.model.Masjid;
import com.applogic.model.Restaurent;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;

public class RestaurentListActivity extends Activity {

	RestaurentListActivity activity = this;

	// TODO GET THE LIST FROM HALAL HUBS
	// TODO SET THE LSIT ITEM LAYOUT & ADAPTER
	// TODO SHOW LIST

	ListView list_view;
	ArrayList<Masjid> masjids = new ArrayList<Masjid>();
	private MasjidListAdapter adapter;
	private RestaurentListAdapter resAdapter;

	ArrayList<Restaurent> resList = new ArrayList<Restaurent>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurent_list);

		list_view = (ListView) findViewById(R.id.list_view);

		try {

			list_view.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {

					startActivity(new Intent(activity,
							RestaurentViewActivity.class).putExtra("position",
							position));

				}
			});

			GetNearestRestaurentFromAPI api = new GetNearestRestaurentFromAPI(
					activity, true);

			api.setListener(new CustomListener() {

				@Override
				public void ModificationMade() {

					resList = ApplicationData.restaurentList;

					resAdapter = new RestaurentListAdapter(
							getApplicationContext(), resList);

					list_view.setAdapter(resAdapter);

				}
			});

			api.execute();

			//

			// GetNearestMasjidFromAPI api = new
			// GetNearestMasjidFromAPI(activity,
			// true);
			//
			// api.setListener(new CustomListener() {
			//
			// @Override
			// public void ModificationMade() {
			//
			// masjids = ApplicationData.masjidList;
			// adapter = new MasjidListAdapter(getApplicationContext(),
			// masjids);
			//
			// list_view.setAdapter(adapter);
			//
			// }
			// });
			//
			// api.execute();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
