package com.datatech.halalhubs;

import java.util.ArrayList;

import org.halfcycle.network.CustomListener;
import org.halfcycle.network.GetFoodListFromAPI;
import org.halfcycle.network.GetNearestRestaurentFromAPI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.applogic.model.Food;
import com.applogic.model.Masjid;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;

public class RestaurentFoodListActivity extends Activity {

	RestaurentFoodListActivity activity = this;

	// TODO GET THE LIST FROM HALAL HUBS
	// TODO SET THE LSIT ITEM LAYOUT & ADAPTER
	// TODO SHOW LIST

	ListView list_view_foodList;
	private FoodListAdapter resAdapter;

	ArrayList<Food> resList = new ArrayList<Food>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_list);

		list_view_foodList = (ListView) findViewById(R.id.list_view_foodList);

		try {

			list_view_foodList
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {

							// startActivity(new Intent(activity,
							// RestaurentViewActivity.class).setFlags(
							// Intent.FLAG_ACTIVITY_NEW_TASK).putExtra(
							// "position", position));

						}
					});

			GetFoodListFromAPI api = new GetFoodListFromAPI(activity, true);

			api.setListener(new CustomListener() {

				@Override
				public void ModificationMade() {

					resList = ApplicationData.foodList;

					resAdapter = new FoodListAdapter(getApplicationContext(),
							resList);

					list_view_foodList.setAdapter(resAdapter);

				}
			});

			api.execute();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
