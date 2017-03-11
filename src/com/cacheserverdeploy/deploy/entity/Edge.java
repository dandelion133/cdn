package com.cacheserverdeploy.deploy.entity;
/**
 * ÍøÂçÖÐÄ³Ìõ±ß  Â·¾¶
 * @author qian
 *
 */
public class Edge {
	
	private int sourceID;
	private int destinationID;
	private int bandwidth;
	public int getSourceID() {
		return sourceID;
	}
	public Edge(int sourceID, int destinationID, int bandwidth) {
		super();
		this.sourceID = sourceID;
		this.destinationID = destinationID;
		this.bandwidth = bandwidth;
	}
	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}
	public int getDestinationID() {
		return destinationID;
	}
	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	@Override
	public String toString() {
		return "Edge [sourceID=" + sourceID + ", destinationID=" + destinationID + ", bandwidth=" + bandwidth + "]";
	}
	public Edge() {
		super();
	}

	
	
	
	
	
}
