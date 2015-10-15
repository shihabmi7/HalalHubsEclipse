package com.datatech.halalhubs;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applogic.model.Order;
import com.applogic.utility.ApplicationData;
import com.datatech.halalhubstest.R;

public class ShowShopingDetailsActivity extends CustomWindow {

	LinearLayout dynamic_layout, dynamic_calculation;

	String[] list = { "Sub Total", "Delivery fee", "Sales tax", "Total" };

	Double subTotal = 0.0;
	Double delivery_fee = 30.0;
	Double tax_rate = 7.5;
	private Double total_tax = 0.0;
	private Double total = 0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO SHOW all order to CHECK OUT PAGE
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoping_details);

		dynamic_layout = (LinearLayout) findViewById(R.id.dynamic_layout);
		dynamic_calculation = (LinearLayout) findViewById(R.id.dynamic_calculation);

		for (int i = 0; i < ApplicationData.foodOrderList.size(); i++) {

			dynamic_layout.addView(makeAFoodItemLayout(i));

		}

		for (int i = 0; i < list.length; i++) {

			switch (i) {
			case Order.SUBTOTAL:
				dynamic_calculation.addView(makeACalculationLayout(i,
						subTotal.toString()));
				break;

			case Order.DELIVERY_FEE:

				dynamic_calculation.addView(makeACalculationLayout(i,
						delivery_fee.toString()));
				break;
			case Order.SALES_TAX:

				total_tax = (subTotal * tax_rate) / 100;
				dynamic_calculation.addView(makeACalculationLayout(i,
						total_tax.toString()));
				break;
			case Order.TOTAL:
				
				total = subTotal + delivery_fee + total_tax;

				dynamic_calculation.addView(makeACalculationLayout(i,
						total.toString()));
				break;
			}

		}

	}

	/**
	 * @param i
	 * @return
	 */
	public RelativeLayout makeAFoodItemLayout(int i) {
		Order aOrder = ApplicationData.foodOrderList.get(i);
		double item_price = (aOrder.getQuantity() * aOrder.getFoodPrice());
		subTotal += item_price;

		// Create new Layout
		RelativeLayout primary_layout = new RelativeLayout(this);

		LayoutParams layoutParam = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		primary_layout.setLayoutParams(layoutParam);
		// primary_layout.setOrientation(LinearLayout.HORIZONTAL);
		// primary_layout.setBackgroundColor(0xff99ccff);

		String cross = " × ";

		String makeString = cross.toUpperCase() + aOrder.getQuantity() + " "
				+ aOrder.getFoodName();

		primary_layout.addView(createATextView(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_LEFT,
				makeString, 20, 10, 20));
		primary_layout.addView(createATextView(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_RIGHT,
				"" + item_price, 20, 10, 20));
		return primary_layout;
	}

	/**
	 * @param i
	 * @return
	 */
	public RelativeLayout makeACalculationLayout(int i, String value) {
		// Create new LinearLayout
		RelativeLayout primary_layout = new RelativeLayout(this);

		LayoutParams layoutParam = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		primary_layout.setLayoutParams(layoutParam);

		// FOR LINEAR LAYOUT SET ORIENTATION
		// primary_layout.setOrientation(LinearLayout.HORIZONTAL);

		// FOR BACKGROUND COLOR
		// primary_layout.setBackgroundColor(0xff99ccff);

		primary_layout.addView(createATextView(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_LEFT, list[i],
				20, 10, 20));
		primary_layout.addView(createATextView(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_RIGHT,
				value, 20, 10, 20));
		return primary_layout;
	}

	TextView createATextView(int layout_widh, int layout_height, int align,
			String text, int fontSize, int margin, int padding) {

		TextView textView_item_name = new TextView(this);

		// LayoutParams layoutParams = new LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		// layoutParams.gravity = Gravity.LEFT;
		RelativeLayout.LayoutParams _params = new RelativeLayout.LayoutParams(
				layout_widh, layout_height);

		_params.setMargins(margin, margin, margin, margin);
		_params.addRule(align);
		textView_item_name.setLayoutParams(_params);

		textView_item_name.setText(text);
		textView_item_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
		textView_item_name.setTextColor(Color.parseColor("#000000"));
		// textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
		textView_item_name.setPadding(padding, padding, padding, padding);

		return textView_item_name;

	}

	@Override
	protected void onResume() {

		super.onResume();
		doIncrease();

	}

}
