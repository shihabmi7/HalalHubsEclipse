package com.datatech.halalhubs;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;

public class CustomWindow extends AppCompatActivity {

	private PopupWindow pwMyPopWindow;
	private ListView lvPopupList;
	private int NUM_OF_VISIBLE_LIST_ROWS = 3;
	Uri uri;
	Intent goToMarket;
	ArrayList<String> moreList;
	private ShareActionProvider mShareActionProvider;
	// public static ActivityAnimator activityAnimator;
	Activity activity = this;
	//
	protected int count = 0;
	private View view;
	private TextView textView;
	private View counterTextPanel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// set screen on / wake lock on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		initializeData();
		iniPopupWindow();
		// activityAnimator = new ActivityAnimator();

		// set action Bar icon & Text
		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
				| ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

	}

	/**
	 * Controls title bar menu item visibility
	 */

	public void initTitleBar() {

		// ((ImageView) findViewById(R.id.menu_item_cart))
		// .setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (pwMyPopWindow.isShowing()) {
		//
		// pwMyPopWindow.dismiss();
		// } else {
		//
		// pwMyPopWindow.showAsDropDown(v);
		// }
		//
		// }
		// });

		// ((ImageView) findViewById(R.id.imageView_app_icon))
		// .setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// finish();
		// Intent aIntent = new Intent(getApplicationContext(),
		// Res.class);
		// startActivity(aIntent);
		//
		// activityAnimator.PullRightPushLeft(activity);
		//
		// // overridePendingTransition(R.anim.fadein,
		// // R.anim.fadeout);
		//
		// }
		// });

		// ((FrameLayout) findViewById(R.id.counterPanel))
		// .setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// Toast.makeText(getApplicationContext(),
		// "FrameLayout Clicked..", Toast.LENGTH_LONG)
		// .show();
		//
		// }
		// });

	}

	/**
	 * Close extra activities except Home
	 */
	// private void closeActivityIntent() {
	// if (!CustomWindow.this.getLocalClassName().equals(
	// HomeActivity.class.getSimpleName())) {
	// CustomWindow.this.finish();
	// }
	// }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);

		MenuItem menuItem = menu.findItem(R.id.testAction);
		menuItem.setIcon(buildCounterDrawable(count, R.drawable.ic_menu_gallery));

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {

			Toast.makeText(getApplicationContext(), "action_settings",
					Toast.LENGTH_LONG).show();
			return true;

		} else if (id == R.id.testAction) {

			Toast.makeText(getApplicationContext(), "testAction",
					Toast.LENGTH_LONG).show();
		}

		return super.onOptionsItemSelected(item);
	}

	public void setOrderCount() {

		textView.setText("" + ApplicationData.foodOrderList.size());
	}

	private Drawable buildCounterDrawable(int count, int backgroundImageId) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.counter_menuitem_layout, null);
		view.setBackgroundResource(backgroundImageId);

		textView = (TextView) view.findViewById(R.id.count);
		counterTextPanel = view.findViewById(R.id.counterValuePanel);

		if (count == 0) {
			counterTextPanel.setVisibility(View.GONE);
		} else {
			textView.setText("" + count);
		}

		view.measure(View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);

		return new BitmapDrawable(getResources(), bitmap);
	}

	public void doIncrease() {

		count = ApplicationData.foodOrderList.size();
		// count++;
		invalidateOptionsMenu();
	}

	private Intent sharetheAppDialog() {

		Intent sharingIntent = null;
		// try {
		//
		// sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		// sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
		// getString(R.string.app_name));
		// sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
		// getString(R.string.share_message)
		// + Constants.SHARE_APP_DETAILS + getPackageName());
		// sharingIntent.setType("text/plain");
		// startActivity(Intent.createChooser(sharingIntent, "Share via"));
		//
		// // startActivity(sharingIntent);
		// } catch (Exception e) {
		//
		// Toast.makeText(getApplicationContext(), "can't open share intent",
		// Toast.LENGTH_LONG).show();
		//
		// }
		//
		return sharingIntent;

	}

	protected void goForRateUs() {
		uri = Uri.parse("market://details?id=" + getPackageName());
		goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
			startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getApplicationContext(), "Could't lunch the market",
					Toast.LENGTH_LONG).show();
		}
	}

	protected void goForMoreApps() {
		// Uri uri = Uri
		// .parse("market://search?q=pub:" + Constants.DEVELOPER_NAME);
		// Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		// try {
		// startActivity(goToMarket);
		// } catch (ActivityNotFoundException e) {
		// Toast.makeText(getApplicationContext(), "Could't lunch the market",
		// Toast.LENGTH_LONG).show();
		// }
	}

	private void goToGovHajjPortal() {

		try {

			String url = "http://www.hajj.gov.bd/";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		} catch (Exception e) {

		}

	}

	private void initializeData() {

		// moreList = new ArrayList<String>();
		// moreList.add(getApplication().getString(R.string.rate_this_app));
		// moreList.add(getApplication().getString(R.string.share_bangla));
		// moreList.add(getApplication().getString(R.string.more_apps));
	}

	private void iniPopupWindow() {

		// LayoutInflater inflater = (LayoutInflater) this
		// .getSystemService(LAYOUT_INFLATER_SERVICE);
		// View layout = inflater.inflate(R.layout.task_detail_popupwindow,
		// null);
		// lvPopupList = (ListView) layout.findViewById(R.id.lv_popup_list);
		// pwMyPopWindow = new PopupWindow(layout);
		// pwMyPopWindow.setFocusable(true);
		//
		// lvPopupList.setAdapter(new MenuItemListAdapter(CustomWindow.this,
		// moreList));
		// lvPopupList.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		//
		// pwMyPopWindow.dismiss();
		// if (position == 0)
		// goForRateUs();
		// else if (position == 1)
		// sharetheAppDialog();
		// else if (position == 2)
		// goForMoreApps();
		//
		// }
		//
		// });
		//
		// lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,
		// View.MeasureSpec.UNSPECIFIED);
		// pwMyPopWindow.setWidth(lvPopupList.getMeasuredWidth());
		// pwMyPopWindow.setHeight((lvPopupList.getMeasuredHeight() + 20)
		// * NUM_OF_VISIBLE_LIST_ROWS);
		//
		// pwMyPopWindow.setBackgroundDrawable(this.getResources().getDrawable(
		// R.drawable.bg_popupwindow));
		// pwMyPopWindow.setOutsideTouchable(true);
	}

}
