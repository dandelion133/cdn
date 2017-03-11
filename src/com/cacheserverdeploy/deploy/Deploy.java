package com.cacheserverdeploy.deploy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cacheserverdeploy.deploy.entity.ConsumVertex;
import com.cacheserverdeploy.deploy.entity.Path;
import com.cacheserverdeploy.deploy.entity.Vertex;

public class Deploy
{
	//public static final int MAXVNUM = 1000;100000
	public static final int MAXNUM = 100000;
	private static Path[][] graph;	//邻接矩阵												// = new Path[MAXVNUM][MAXVNUM];
	
	private static Vertex[] vertexs;
	private static ConsumVertex[] comsumVertexs;
	
	private static int[][] comsumVertexsGraph;
	
	private static int numVertex;
	private static int numEdge;
	private static int numConsumVertex;
	
	
	private static int costVideoServer;
	
	
	
	/**
     * todo
     * 
     * @param graphContent caseinfo
     * @return  caseouput info
     * @see [huawei]
     */
    public static String[] deployServer(String[] graphContent)
    {
    	
    	initGraph(graphContent);
    	//
    	for (int i = 0; i < vertexs.length; i++) {
    		for (int j = 0; j < vertexs.length; j++) {
    			Path path = graph[i][j];
    			
    			if(path == null)
    				System.out.print("+\t");
    				else
				System.out.print(graph[i][j].getPrice() + "\t");
			}
    		System.out.println();
		}
    	Map<Integer,Integer> map = new HashMap<>();
    	
    	for (int j = 0; j < numConsumVertex; j++) {
    		
    		Map<Integer, List<Integer>> rst = dijkstra(vertexs[comsumVertexs[j].getNetVertex()]);
        	System.out.println(rst);
        	
        	Iterator<Integer> iterator = rst.keySet().iterator();
        	
        	while(iterator.hasNext()) {
        		
        		Integer key = iterator.next();
        		List<Integer> list = rst.get(key);
        		for (int i = 0; i < list.size(); i++) {
    				
        			if(map.containsKey(list.get(i))) {
        				
        				Integer integer = map.get(list.get(i));
        				integer ++;
        				map.put(list.get(i), integer);
        				
        			} else {
        				map.put(list.get(i), 1);
        			}
    			}
        	}
		}
    	
    	
    	
    	
    	System.out.println(map);
    	
    	
    	
    	
    	
    	
    	

    	
    	
    	/*System.out.println(graph[19][20]);
    	System.out.println(comsumVertexs[8]);
    	System.out.println(vertexs[19]);*/
        /**do your work here**/
        return new String[]{"17","\r\n","0 8 0 20"};
    }
    
    
    public static void initGraph(String[] graphContent) {
    	
    	String[] numOfNet = graphContent[0].split(" ");
    	numVertex = Integer.valueOf(numOfNet[0]);
    	graph = new Path[numVertex][numVertex];
    	
    	
    	vertexs = new Vertex[numVertex];
    	
    	numEdge = Integer.valueOf(numOfNet[1]);
    	numConsumVertex = Integer.valueOf(numOfNet[2]);
    	comsumVertexs = new ConsumVertex[numConsumVertex];
    	comsumVertexsGraph = new int[numConsumVertex][numConsumVertex];
    	
    	for (int i = 0; i < numConsumVertex; i++) {
			for (int j = 0; j < numConsumVertex; j++) {
				comsumVertexsGraph[i][j] = Integer.MAX_VALUE;
			}
		}
    	
    	
    	costVideoServer = Integer.valueOf(graphContent[2]);
    	
    	
    	for (int i = 4; i < graphContent.length; i++) {
			
    		String[] splitContent = graphContent[i].split(" ");
    		if(splitContent.length == 4) {
    			
    			
    			int v1 = Integer.valueOf(splitContent[0]);
    			int v2 = Integer.valueOf(splitContent[1]);
    			int bandwidth = Integer.valueOf(splitContent[2]);
    			int price = Integer.valueOf(splitContent[3]);
    			
    			
    			Path path = new Path(bandwidth, price, bandwidth);
    			graph[v1][v2] = path;
    			graph[v2][v1] = path;
    			if(vertexs[v1] == null) {
    				vertexs[v1] = new Vertex(false,v1);
    			} 
    			if(vertexs[v2] == null) {
    				vertexs[v2] = new Vertex(false,v2);
    			} 
    			vertexs[v1].addNextVertexs(vertexs[v2]);
    			vertexs[v2].addNextVertexs(vertexs[v1]);
    			
    		} else if(splitContent.length == 3){
    			
    			int sonsumVertexId = Integer.valueOf(splitContent[0]);
    			int netVertexId = Integer.valueOf(splitContent[1]);
    			int bandwidth = Integer.valueOf(splitContent[2]);
    			if(comsumVertexs[sonsumVertexId] == null) {
    				comsumVertexs[sonsumVertexId] = new ConsumVertex(sonsumVertexId,bandwidth,netVertexId);
    			} 
    			
    		}
    		
    		
		}
    	
    	
    	
    }
    
	public static Map<Integer,List<Integer>> dijkstra(Vertex startVertex)  {
		
		ArrayList<Vertex> vertexList = new ArrayList<>();
		
		//初始化起点v0
		int startIndex = startVertex.getIndex();
	//	System.out.println("startIndex: " + startIndex);
		
		vertexList.add(vertexs[startIndex]);
		
		for (int i = 0; i < startIndex; i++) {
			vertexList.add(vertexs[i]);
		}
		
		for (int i = startIndex + 1; i < numVertex; i++) {
			 
			vertexList.add(vertexs[i]);
		}
		
		boolean[] hasVisited = new boolean[numVertex];
		
		//构建prev[]数组，保存该点对应的先前节点
		int[] prev = new int[numVertex];
		
		int[] dist = new int[numVertex];
		
		
		for (int i = 0; i < dist.length; i++) {
			
			if(graph[startIndex][vertexList.get(i).getIndex()] == null) {
				dist[i] = MAXNUM;
				prev[i] = -1;
			} else {
				dist[i] = graph[startIndex][vertexList.get(i).getIndex()].getPrice();
				prev[i] = 0;
			}
			
		}

		hasVisited[0] = true;
		dist[0] = 0;
		for (int i = 1; i < dist.length; i++) {
			int minDist = MAXNUM;
			int u = 0;
			//找到当前距离startIndex最近并且为访问过的点
			for (int j = 1; j < dist.length; j++) {
				if (!hasVisited[j] && dist[j] < minDist) {
					u = j;
					minDist = dist[j];
				}
			}
			
			if (u  == 0 )
				break;
			
			hasVisited[u] = true;
			//根据当前点u松弛节点
			for (int j = 1; j < dist.length; j++) {
				
				
				Path path = graph[vertexList.get(u).getIndex()][vertexList.get(j).getIndex()];
				if(path != null) {
					if (!hasVisited[j]) {
						if (dist[u] + path.getPrice() < dist[j]) {
							dist[j] = dist[u] + path.getPrice();
							prev[j] = u;
						}
					}
				}
				
				
			}
		}
		/*for (int i = 0; i < dist.length; i++) {
			System.out.println("startIndex: "+startIndex+"endIndex: "+vertexList.get(i).getIndex() + "," + dist[i]);
		}*/
		
		
		   Map<Integer,List<Integer>> rstMap = new HashMap<>();
		
			
			for (int j = 0; j < numConsumVertex; j++) {       //找出消费节点之间经过的点的集合
				
				int index = comsumVertexs[j].getNetVertex();
				int position;
				if(index == startIndex) {
					continue;
				} else if(index < startIndex) {
					position  = index + 1;
				} else {
					position  = index;
				}
				
				//消费节点对应网络节点的index  
				List<Integer> list = new ArrayList<>();
				
				while (position != 0) {
					
					position = prev[position];
					if(position == -1 || position == 0) {
						break;
					}
					list.add(vertexList.get(position).getIndex());
								
				}
				rstMap.put(comsumVertexs[j].getIndex(), list);
				
			}
			return rstMap;
			
			
	
	}
	
 
}
