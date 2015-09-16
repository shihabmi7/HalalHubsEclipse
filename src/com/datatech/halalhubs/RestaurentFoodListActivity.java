package com.datatech.halalhubs;

import java.util.ArrayList;
import java.util.Locale;

import org.halfcycle.network.CustomListener;
import org.halfcycle.network.GetFoodListFromAPI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.applogic.model.Food;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubs.PinnedSectionListView.PinnedSectionListAdapter;
import com.datatech.halalhubstest.R;

public class RestaurentFoodListActivity extends Activity {

	RestaurentFoodListActivity activity = this;

	// TODO GET THE LIST FROM HALAL HUBS
	// TODO SET THE LSIT ITEM LAYOUT & ADAPTER
	// TODO SHOW LIST , implements pinsec list adapter

	PinnedSectionListView list_view_foodList;
	
	//ListView list_view_foodList;

	private FoodListAdapter resAdapter;

	ArrayList<Food> foodList = new ArrayList<Food>();
	private boolean isFastScroll = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_list);

		list_view_foodList = (PinnedSectionListView) findViewById(R.id.list_view_foodList);
		//list_view_foodList = (ListView) findViewById(R.id.list_view_foodList);

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

			getFoodListFromAPI();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public void getFoodListFromAPI() {
		GetFoodListFromAPI api = new GetFoodListFromAPI(activity, true);

		api.setListener(new CustomListener() {

			@Override
			public void ModificationMade() {

				foodList = ApplicationData.foodList;

				resAdapter = new FoodListAdapter(getApplicationContext(),
						foodList);

				list_view_foodList.setAdapter(resAdapter);

			}
		});

		api.execute();
	}
	@SuppressLint("NewApi")
	private void initializeAdapter() {

		list_view_foodList.setFastScrollEnabled(isFastScroll);
		if (isFastScroll) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				list_view_foodList.setFastScrollAlwaysVisible(true);
			}
			list_view_foodList.setAdapter(new FastScrollAdapter(this,
					android.R.layout.simple_list_item_1, android.R.id.text1));
		} else {
			
			
			list_view_foodList.setAdapter(new SimpleAdapter(this,
					android.R.layout.simple_list_item_1, android.R.id.text1));
		}
	}

	static class SimpleAdapter extends ArrayAdapter<Item> implements
			PinnedSectionListAdapter, SectionIndexer {

		
		private Item[] sections;
		
		private static final int[] COLORS = new int[] { R.color.green_light,
				R.color.orange_light, R.color.blue_light, R.color.red_light };

		public SimpleAdapter(Context context, int resource,
				int textViewResourceId) {
			super(context, resource, textViewResourceId);
			generateDataset('A', 'Z', false);
		}

		public void generateDataset(char from, char to, boolean clear) {

			if (clear)
				clear();

			final int sectionsNumber = to - from + 1;
			prepareSections(sectionsNumber);

			int sectionPosition = 0, listPosition = 0;

			for (char i = 0; i < sectionsNumber; i++) {
				Item section = new Item(Item.SECTION,
						String.valueOf((char) ('A' + i)));
				section.sectionPosition = sectionPosition;
				section.listPosition = listPosition++;
				onSectionAdded(section, sectionPosition);
				add(section);

				final int itemsNumber = (int) Math.abs((Math.cos(2f * Math.PI
						/ 3f * sectionsNumber / (i + 1f)) * 25f));
				for (int j = 0; j < itemsNumber; j++) {
					Item item = new Item(Item.ITEM,
							section.text.toUpperCase(Locale.ENGLISH) + " - "
									+ j);
					item.sectionPosition = sectionPosition;
					item.listPosition = listPosition++;
					add(item);
				}

				sectionPosition++;
			}
		}

//		@Override
		protected void prepareSections(int sectionsNumber) {
			sections = new Item[sectionsNumber];
		}

//		@Override
		protected void onSectionAdded(Item section, int sectionPosition) {
			sections[sectionPosition] = section;
		}

		@Override
		public Item[] getSections() {
			return sections;
		}

		@Override
		public int getPositionForSection(int section) {
			if (section >= sections.length) {
				section = sections.length - 1;
			}
			return sections[section].listPosition;
		}

		@Override
		public int getSectionForPosition(int position) {
			if (position >= getCount()) {
				position = getCount() - 1;
			}
			return getItem(position).sectionPosition;
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView view = (TextView) super.getView(position, convertView,
					parent);
			view.setTextColor(Color.DKGRAY);
			view.setTag("" + position);
			Item item = getItem(position);
			if (item.type == Item.SECTION) {
				// view.setOnClickListener(PinnedSectionListActivity.this);
				view.setBackgroundColor(parent.getResources().getColor(
						COLORS[item.sectionPosition % COLORS.length]));
			}
			return view;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			return getItem(position).type;
		}

		@Override
		public boolean isItemViewTypePinned(int viewType) {
			return viewType == Item.SECTION;
		}

	}

	class FastScrollAdapter extends SimpleAdapter implements SectionIndexer {

		private Item[] sections;

		public FastScrollAdapter(Context context, int resource,
				int textViewResourceId) {

			super(context, resource, textViewResourceId);

		}

		@Override
		protected void prepareSections(int sectionsNumber) {
			sections = new Item[sectionsNumber];
		}

		@Override
		protected void onSectionAdded(Item section, int sectionPosition) {
			sections[sectionPosition] = section;
		}

		@Override
		public Item[] getSections() {
			return sections;
		}

		@Override
		public int getPositionForSection(int section) {
			if (section >= sections.length) {
				section = sections.length - 1;
			}
			return sections[section].listPosition;
		}

		@Override
		public int getSectionForPosition(int position) {
			if (position >= getCount()) {
				position = getCount() - 1;
			}
			return getItem(position).sectionPosition;
		}

	}

	static class Item {

		public static final int ITEM = 0;
		public static final int SECTION = 1;

		public final int type;
		public final String text;

		public int sectionPosition;
		public int listPosition;

		public Item(int type, String text) {
			this.type = type;
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

	}

	
}
