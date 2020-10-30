package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class GraphAlgorithm {

	//	double[][] graph = new double[][]{
	//	{0, 34, 46, -1,-1,19},
	//	{34,0,-1,-1,12,-1},
	//	{46,-1,0,17,-1,25},
	//	{-1,-1,17,0,38,25},
	//	{-1,12,-1,38,0,26},
	//	{19,-1,25,25,26,0}
	//	};
	static int MAXLENGTH = 999999999;

	public static void dfs(double[][] graph, int start) {
		// init an array of int to indicate which vertex is visited
		int[] visited = new int[graph.length];

		// start dfs recursively
		visited[start] = 1;
		_dfs(graph, start, visited);
	}

	// dfs recursively
	private static void _dfs(double[][] graph, int cur, int[] visited) {
		// print current vertex
		System.out.print(cur + " ");
		// find next available vertex and dfs on it
		for(int i = 0; i < graph.length; i++) {
			if(i == cur || graph[cur][i] == -1) continue;
			else if(visited[i] == 0) {
				visited[i] = 1;
				_dfs(graph,i,visited);				
			}
		}
	}

	public static void bfs(double[][] graph, int start) {
		// init an array of int to indicate which vertex is visited
		int[] visited = new int[graph.length];

		// build a queue and push start to it
		// change LinkedList to your own implementation
		LinkedList<Integer> queue = new LinkedList<>();
		visited[start] = 1;
		queue.push(start);

		// loop while queue is empty
		while (queue.size() > 0) {
  		// pop and print a vertex,
			int cur = queue.poll();
			System.out.print(cur + " ");
      // then push its available next vertex to the queue
			for(int i = 0; i < graph.length; i++) {
				if(i == cur || graph[cur][i] == -1) continue;
				else if(visited[i] == 0) {
					visited[i] = 1;
					queue.add(i);
				}
			}
		}
	}

	public static double[][] prim(double[][] graph) {
		// init a double[][] for return
		// init U and V
		int n = graph.length;
		double[][] ret = new double[n][n];
		List<Integer> U = new LinkedList<>();
		U.add(0);
		List<Integer> V = new LinkedList<>();
		for(int i = 1; i < n; i++) V.add(i);

		// loop while V is not empty
		while(!V.isEmpty()) {
			Integer minu = -1, minv = -1;
			double minlen = MAXLENGTH;
			// loop on each vertex in U
			for(Integer u : U) {
				// find closest path from U to V(u-->v)
				for(int v = 0; v < n; v++) {
					if(V.indexOf(v) != -1 && graph[u][v] != -1)
						if(graph[u][v] < minlen) {
							minu = u; minv = v; minlen = graph[u][v];
						}
				}
			}
			// move v to U
			V.remove(minv);	U.add(minv);
			ret[minu][minv] = minlen; ret[minv][minu] = minlen;
		}

		return ret;
	}

	public static double[][] kruskal(double[][] graph) {
		// construct a list of edge from graph, and sort it
		List<Edge> edges = new ArrayList<>();
		int n = graph.length;
		for(int u = 0; u < n; u++)
			for(int v = u+1; v < n; v++)
				if(graph[u][v] != -1)
					edges.add(new Edge(u,v,graph[u][v]));
		edges.sort(null);

		// init a array represent the root of each vertex
		int[] root = new int[n];
		for(int i = 0; i < n; i++) root[i] = i;

		// init a double[][] for return
		double[][] ret = new double[n][n];

		// loop on edges
		for(Edge e : edges) {
			// check if the roots of two vertex of this edge the same
			// if is same, continue
			if(_getRoot(root, e.v1) == _getRoot(root, e.v2)) continue;
			// if not, modify the double[][] for return
			//         and update root
			ret[e.v1][e.v2] = e.weight; ret[e.v2][e.v1] = e.weight;
			root[root[e.v2]] = root[e.v1];
		}

		return ret;
	}

	// get root of a vertex
	private static int _getRoot(int[] root, int query) {
		if(root[query] == query) return query;
		else {
			root[query] = _getRoot(root, root[query]);
			return root[query];
		}
	}

	// a simple class to represent edge
	// it is comparable to itself
	static class Edge implements Comparable<Edge> {
		int v1, v2;
		double weight;
		int digits = 1000000;

		public Edge(int v1, int v2, double w) {
			this.v1 = v1 < v2 ? v1 : v2;
			this.v2 = v1 < v2 ? v2 : v1;
			weight = w;
		}

		@Override
		public int compareTo(Edge o) {
			return (int)weight*digits-(int)o.weight*digits;
		}

	}

	public static double dijkstra(double[][] graph, int start, int end) {
		int n = graph.length; //get length n
		// tool class Pair, pack vertex and its distance together
		class Pair implements Comparable<Pair> {
			int _v;
			double _dis;
			int digits = 1000000;
			
			Pair(int v,double distance) {_v = v; _dis = distance;}
			
			@Override
			public int compareTo(Pair o) {
				return (int)_dis*digits - (int)o._dis*digits;
			}
		}
		//smallOnTop heap to quickly get the smallest distance
		PriorityQueue<Pair> V = new PriorityQueue<>();
		V.add(new Pair(start,0));

		// init an array indicating the shortest distance from start to each vertex
		double[] distance = new double[n];
		for(int i = 0; i < n; i++) distance[i] = Integer.MAX_VALUE;
		distance[start] = 0;

		// init an array to mark whether vertex has been confirmed
		boolean[] confirmed = new boolean[n];
		
		while(!V.isEmpty()) {
//for(int i = 0; i < n; i++) System.out.print(((Double)distance[i]).toString() + " "); System.out.println();
			Pair cur = V.poll();
			int v = cur._v;
			
			// check if current one is confirmed
			if(confirmed[v]) continue;
			else confirmed[v] = true;
			
			// loop v's neighbours
			for(int i = 0; i < n; i++) {
				// if passing v offer a shorter path to i
				// update distance i and push i into the queue
				if(graph[v][i] != -1 && distance[i] > distance[v] + graph[v][i]) {
					distance[i] = distance[v] + graph[v][i];
					V.add(new Pair(i,distance[i]));
				}
			}
		}
		return distance[end];
	}

}
