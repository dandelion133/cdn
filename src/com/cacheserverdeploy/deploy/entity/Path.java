package com.cacheserverdeploy.deploy.entity;
/**
 * Â·¾¶ÐÅÏ¢
 * @author qian
 *
 */

public class Path {

	private int bandwidth;
	private int price;
	private int leftBandwidth;
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLeftBandwidth() {
		return leftBandwidth;
	}
	public void setLeftBandwidth(int leftBandwidth) {
		this.leftBandwidth = leftBandwidth;
	}
	@Override
	public String toString() {
		return "Path [bandwidth=" + bandwidth + ", price=" + price + ", leftBandwidth=" + leftBandwidth + "]";
	}
	public Path(int bandwidth, int price, int leftBandwidth) {
		super();
		this.bandwidth = bandwidth;
		this.price = price;
		this.leftBandwidth = leftBandwidth;
	}
	public Path() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
