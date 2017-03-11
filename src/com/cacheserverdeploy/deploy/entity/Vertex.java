package com.cacheserverdeploy.deploy.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ÍøÂç½Úµã
 * @author qian
 *
 */
public class Vertex {
	private boolean isVisited;
	private int index;
	private List<Vertex> nextVertexs = new ArrayList<>();
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Vertex> getNextVertexs() {
		return nextVertexs;
	}
	public void setNextVertexs(List<Vertex> nextVertexs) {
		this.nextVertexs = nextVertexs;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nextVertexs.size(); i++) {
			sb.append(nextVertexs.get(i).getIndex()+",");
		}
		
		return "Vertex [isVisited=" + isVisited + ", index=" + index + ", nextVertexs=" + sb.toString() + "]";
	}
	public Vertex() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vertex(boolean isVisited, int index) {
		super();
		this.isVisited = isVisited;
		this.index = index;
	}
	public void addNextVertexs(Vertex nextVertex)
	{
		nextVertexs.add(nextVertex);
	}
	
}
