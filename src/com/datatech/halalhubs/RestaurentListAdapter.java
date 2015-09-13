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

import com.applogic.model.Restaurent;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class RestaurentListAdapter extends BaseAdapter {

	private final Context mContext;
	ArrayList<Restaurent> newsItemList;
	DisplayImageOptions options;
	ImageLoader imageLoader = ImageLoader.getInstance();

	// we use universal image loader library

	public RestaurentListAdapter(Context context, ArrayList<Restaurent> list) {

		mContext = context;
		this.newsItemList = list;
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
		return ApplicationData.restaurentList.size();

	}

	@Override
	public Object getItem(int position) {
		return ApplicationData.restaurentList.get(position);
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
					R.layout.listview_item_a_restaurent, parent, false);

			holder = new ViewHolder();

			holder.masjid_name = (TextView) convertView
					.findViewById(R.id.restaurent_name);

			holder.masjid_area = (TextView) convertView
					.findViewById(R.id.textView_restaurent_address);

			holder.catagory_image = (ImageView) convertView
					.findViewById(R.id.imageView_restaurent_pic);

			holder.ratingBar_restaurent = (RatingBar) convertView
					.findViewById(R.id.ratingBar_restaurent);

			holder.ratingBar_restaurent.setStepSize((float) 0.25);
			holder.ratingBar_restaurent.setRating(4);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Restaurent aMasjidObj = (Restaurent) ApplicationData.restaurentList
				.get(position);
		holder.masjid_name.setText(aMasjidObj.getRestaurentName());
		holder.masjid_area.setText(aMasjidObj.getAddress());

		imageLoader.displayImage(ApplicationData.restaurentList.get(position)
				.getImage_Url(), holder.catagory_image, options);

		if (holder.catagory_image != null) {

			// mahbub vi code

		}
		return convertView;
	}

	class ViewHolder {

		private int catatory_id;
		private TextView masjid_name;
		private TextView masjid_area;
		private ImageView catagory_image;
		RatingBar ratingBar_restaurent;

	}

}
