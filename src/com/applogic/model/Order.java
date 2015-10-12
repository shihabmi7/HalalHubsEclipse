package com.applogic.model;

public class Order {

	private String foodName;
	private String foodPrice;
	private int quatity;
	private String deliveryFree;
	private String salesTax;
	private String total;
	private String subTotal;
	/**
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * @param foodName the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * @return the foodPrice
	 */
	public String getFoodPrice() {
		return foodPrice;
	}
	/**
	 * @param foodPrice the foodPrice to set
	 */
	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}
	/**
	 * @return the quatity
	 */
	public int getQuatity() {
		return quatity;
	}
	/**
	 * @param quatity the quatity to set
	 */
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	/**
	 * @return the deliveryFree
	 */
	public String getDeliveryFree() {
		return deliveryFree;
	}
	/**
	 * @param deliveryFree the deliveryFree to set
	 */
	public void setDeliveryFree(String deliveryFree) {
		this.deliveryFree = deliveryFree;
	}
	/**
	 * @return the salesTax
	 */
	public String getSalesTax() {
		return salesTax;
	}
	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the subTotal
	 */
	public String getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
}
