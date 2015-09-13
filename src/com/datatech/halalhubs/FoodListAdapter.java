package com.datatech.halalhubs;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.applogic.model.Food;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class FoodListAdapter extends BaseAdapter {

	private final Context mContext;
	ArrayList<Food> foodList;
	DisplayImageOptions options;
	ImageLoader imageLoader = ImageLoader.getInstance();

	// we use universal image loader library

	public FoodListAdapter(Context context, ArrayList<Food> list) {

		mContext = context;
		this.foodList = list;
		// =============== UIL Initialization =================

		options = new DisplayImageOptions.Builder()

		.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.displayer(new RoundedBitmapDisplayer(5)).cacheInMemory(true)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				mContext).diskCacheExtraOptions(480, 480, null).build();
		imageLoader.init(config);

		// =====================================================
	}

	@Override
	public int getCount() {
		// return 100;
		return ApplicationData.foodList.size();

	}

	@Override
	public Object getItem(int position) {
		return ApplicationData.foodList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.listview_item_a_food, parent, false);

			holder = new ViewHolder();

			holder.food_name = (TextView) convertView
					.findViewById(R.id.food_name);

			holder.food_description = (TextView) convertView
					.findViewById(R.id.food_description);

			holder.food_image = (ImageView) convertView
					.findViewById(R.id.imageView_food_pic);

//			holder.ratingBar_restaurent = (RatingBar) convertView
//					.findViewById(R.id.ratingBar_restaurent);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();

		}

		Food aFood = (Food) ApplicationData.foodList.get(position);

		holder.food_name.setText(aFood.getFoodName());
		holder.food_description.setText(aFood.getFoodDescription());

//		holder.ratingBar_restaurent.setStepSize((float) 0.25);
//		holder.ratingBar_restaurent.setRating(4);

		imageLoader.displayImage(aFood.getFoodPictureUrl(), holder.food_image,
				options);

		if (holder.food_image != null) {

			// mahbub vi code

		}
		return convertView;
	}

	class ViewHolder {

		private int catatory_id;
		private TextView food_name;
		private TextView food_description;
		private ImageView food_image;
		RatingBar ratingBar_restaurent;

	}

}
