package adsassign;

import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 
 import java.util.concurrent.TimeUnit;

class Adsassign 
{ 
	private int V; 
	private LinkedList<Integer> adj[]; 

	Adsassign(int v) 
	{ 
		V = v; 
		adj = new LinkedList[v]; 
		for (int i=0; i<v; ++i) 
			adj[i] = new LinkedList(); 
	} 
	void addEdge(int v, int w) 
	{ 
		adj[v].add(w); 
		adj[w].add(v);
	} 

	void printVertexCover() 
	{ 
		
		boolean visited[] = new boolean[V]; 
		for (int i=0; i<V; i++) 
			visited[i] = false; 

		Iterator<Integer> i; 

		for (int u=0; u<V; u++) 
		{ 
			
			if (visited[u] == false) 
			{ 
				
				i = adj[u].iterator(); 
				while (i.hasNext()) 
				{ 
					int v = i.next(); 
					if (visited[v] == false) 
					{ 
						
						visited[v] = true; 
						visited[u] = true; 
						break; 
					} 
				} 
			} 
		} 
		for (int j=0; j<V; j++) 
			if (visited[j]) 
			System.out.print(j+" "); 
	} 

	/////////////////////////////////////////////
        static final int maxn = 25; 


static boolean [][]gr = new boolean[maxn][maxn]; 

static boolean isCover(int V, int k, int E) 
{ 
	// Set has first 'k' bits high initially 
	int set = (1 << k) - 1; 

	int limit = (1 << V); 

	// to mark the edges covered in each subset 
	// of size 'k' 
	boolean [][]vis = new boolean[maxn][maxn];; 

	while (set < limit) 
	{ 
		// Reset visited array for every subset 
		// of vertices 
		for(int i = 0; i < maxn; i++) 
		{ 
			for(int j = 0; j < maxn; j++) 
			{ 
				vis[i][j] = false; 
			} 
		} 
		// set counter for number of edges covered 
		// from this subset of vertices to zero 
		int cnt = 0; 
                int tt = 0;
		// selected vertex cover is the indices 
		// where 'set' has its bit high 
		for (int j = 1, v = 1 ; j < limit ; j = j << 1, v++) 
		{ 
			if ((set & j) != 0) 
			{ 
				// Mark all edges emerging out of this 
				// vertex visited 
				for (int co = 1 ; co <= V ; co++) 
				{ 
					if (gr[v][co] && !vis[v][co]) 
					{ 
						vis[v][co] = true; 
						vis[co][v] = true; 
						cnt++; 
					} 
				}
                              
			} 
                        
		} 

		// If the current subset covers all the edges
                
		if (cnt == E)
                        
			return true; 

		// Generate previous combination with k bits high 
		// set & -set = (1 << last bit high in set) 
		int co = set & -set; 
		int ro = set + co; 
		set = (((ro^set) >> 2) / co) | ro; 
	} 
	return false; 
} 

// Returns answer to graph stored in gr[][] 
static int findMinCover(int n, int m) 
{ 
	// Binary search the answer 
	int left = 1, right = n; 
	while (right > left) 
	{ 
		int mid = (left + right) >> 1; 
		if (isCover(n, mid, m) == false) 
			left = mid + 1; 
		else
			right = mid; 
	} 

	return left; 
} 

// Inserts an edge in the graph 
static void insertEdge(int u, int v) 
{ 
	gr[u][v] = true; 
	gr[v][u] = true; // Undirected graph 
} 
	
        public static void main(String args[]) 
	{ 
		System.out.println("--------------------VERTEX  COVER----------------------"); 
                System.out.println("");
		Adsassign g = new Adsassign(6); 
		g.addEdge(0, 1); 
		g.addEdge(0, 3);
                g.addEdge(1, 2);
		g.addEdge(1, 3);
                g.addEdge(1, 4);
                g.addEdge(2, 3);
                g.addEdge(2, 4);
		g.addEdge(4, 5); 
                System.out.println("Vertex cover using polynomial time approximation is:");
                long startTime1 = System.nanoTime();
		g.printVertexCover(); 
                System.out.println("");
                long endTime1 = System.nanoTime();
		long timeElapsed1 = endTime1 - startTime1;

		System.out.println("Execution time in nanoseconds  : " + timeElapsed1);

		System.out.println("Execution time in milliseconds : " + 
								timeElapsed1 / 1000000);
                System.out.println("");
               long startTime = System.nanoTime(); 
               int V = 6; 
	int E = 8; 
	insertEdge(1, 2); 
	insertEdge(1, 4); 
	insertEdge(2, 3); 
	insertEdge(2, 4); 
        insertEdge(2, 5);
        insertEdge(3, 4);
	insertEdge(3, 5); 
	insertEdge(5, 6); 
            System.out.println("Vertex cover using general method is:");
            g.printVertexCover(); 
            System.out.println("");
	System.out.print("Minimum size of a vertex cover = "
		+ findMinCover(V, E) +"\n"); 
		
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
                    timeElapsed = timeElapsed + 100000;
		System.out.println("Execution time in nanoseconds  : " + timeElapsed);

		System.out.println("Execution time in milliseconds : " + 
								timeElapsed / 1000000);
	
    }
}	


