package com.cacheserverdeploy.deploy.entity;
/**
 * ���ѽڵ�
 * @author qian
 *
 */
public class ConsumVertex {
	
	private int index;
	private int bandwidth;
	private int netVertex;//�����ӵ�����ڵ�
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public int getNetVertex() {
		return netVertex;
	}
	public void setNetVertex(int netVertex) {
		this.netVertex = netVertex;
	}
	@Override
	public String toString() {
		return "ConsumVertex [index=" + index + ", bandwidth=" + bandwidth + ", netVertex=" + netVertex + "]";
	}
	public ConsumVertex() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsumVertex(int index, int bandwidth, int netVertex) {
		super();
		this.index = index;
		this.bandwidth = bandwidth;
		this.netVertex = netVertex;
	}
	
	
	
	
}
