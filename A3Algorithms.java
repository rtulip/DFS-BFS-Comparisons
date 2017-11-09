/* A5Algorithms.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Image Algorithms


   B. Bird - 07/03/2017
*/ 

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

public class A3Algorithms{




	/* FloodFillDFS(v, viewer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/

	// referenced Lecture Slides and this video: https://www.youtube.com/watch?v=bIA8HEEUxZI
	// referenced Stack Documentation: https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html

	// has a loop that runs at most n times, and constant time operations within the loop
	// total running time: n
	
	public static void FloodFillDFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){
		
		// a stack is used to navigate the graph		
		Stack<PixelVertex> s = new Stack<PixelVertex>();	
		
		// the chosen Pixel is pushed on the stack
		s.push(v);

		// while the stack is not empty, continue
		// items are only pushed on the stack if they haven't been visited
		// as such, at most n Pixels can go on the stack, thus this loop will run at most n times
		while (!s.empty()){
			
			// look at the top item of the stack (contant time)			
			v = s.peek();
			
			
	
				
			// if the pixel hasn't been visited, visit
			// set the colour of the Pixel (contant time)
			// every PixelVertex has a vistied boolean, so getVisited, takes constant time
			if (!v.getVisited()){
				// setVisited takes constant time
				v.setVisited(true);
				viewer.setPixel(v.getX(), v.getY(), fillColour);
			}

			// neighbours is an array of PixelVerticies of size 4
			PixelVertex[] neighbours = v.getNeighbours();			

			boolean unvisitedNeighbour = false;
			
			// check to see if neighbours are visited and pushes the first one that hasn't been onto the stack
			// getDegree takes constant time
			// this loop will take at most 4 iterations
			// thus this loop is constant
			for (int i = 0; i < v.getDegree(); i++){
				if (!neighbours[i].getVisited()){
					unvisitedNeighbour = true;
					s.push(neighbours[i]);
					break;
				}
			}
			
			// if no neighbours were unvisited, pop the pixel off the stack
			if (!unvisitedNeighbour){
				v = s.pop();
			}


		}


	}
	
	/* FloodFillBFS(v, viewer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/

	// a loop that runs at most n times and has constant time operations within
	// total running time: n	

	public static void FloodFillBFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){

		// a LinkedList is being used as a queue		

		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();

		// add the first pixel to the queue
		q.addLast(v);
		// mark the vertex as visited
		v.setVisited(true);
		// change the colour of the pixel
		viewer.setPixel(v.getX(), v.getY(), fillColour);

		
		// the queue can have at most n items placed on it
		while(q.size() != 0){
			// remove the first item in the queue		
			v = q.remove();
			// get the neighbours
			// neighbours is an array of PixelVerticies of size 4
			PixelVertex[] neighbours = v.getNeighbours();
									
			// Visit all unvisited neighbours and add them to the queue
			// runs at most 4 times through the loop
			for (int i = 0; i < v.getDegree(); i++){
				if (!neighbours[i].getVisited()){
					neighbours[i].setVisited(true);					
					viewer.setPixel(neighbours[i].getX(), neighbours[i].getY(), fillColour);
					q.addLast(neighbours[i]);
				}			
			
			}	

		}

	}
	
	/* OutlineRegionDFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	// works the same as FloodFillDFS only with an additional check to see if a PixelVertex is an edge before filling.
	// total running time: n
	
	public static void OutlineRegionDFS(PixelVertex v, ImageViewer225 viewer, Color outlineColour){

		Stack<PixelVertex> s = new Stack<PixelVertex>();	
		
		s.push(v);

		while (!s.empty()){			
			v = s.peek();
			
			if (v.getDegree() < 4){

				viewer.setPixel(v.getX(), v.getY(), outlineColour);
			}

			if (!v.getVisited()){
			
				v.setVisited(true);
			}
			

		
			PixelVertex[] neighbours = v.getNeighbours();			

			boolean unvisitedNeighbour = false;
			
			for (int i = 0; i < v.getDegree(); i++){
				if (!neighbours[i].getVisited()){
					unvisitedNeighbour = true;
					s.push(neighbours[i]);
					break;
				}
			}

			if (!unvisitedNeighbour){
				v = s.pop();
			}

		}

		
	}
	
	/* OutlineRegionBFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.https://www.youtube.com/watch?v=bIA8HEEUxZI
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	
	// same as FloodFillBFS only adds with an additional check to see if a PixelVertex is an edge before filling
	// total running time: n

	public static void OutlineRegionBFS(PixelVertex v, ImageViewer225 viewer, Color outlineColour){

		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();

		q.addLast(v);
		v.setVisited(true);
		if (v.getDegree()< 4){
			viewer.setPixel(v.getX(), v.getY(), outlineColour);
		}
		
		while(q.size() != 0){
		
			v = q.remove();
			PixelVertex[] neighbours = v.getNeighbours();
									
			for (int i = 0; i < v.getDegree(); i++){
				if (!neighbours[i].getVisited()){
					neighbours[i].setVisited(true);
					if (neighbours[i].getDegree() < 4){

						viewer.setPixel(neighbours[i].getX(), neighbours[i].getY(), outlineColour);
					}
					q.addLast(neighbours[i]);
				}			
			
			}	

		}
		

	
	}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/

	// Visits Every PixelVertex once, thus has running time of n

	public static int CountComponents(PixelGraph G){
		

		// a count of the number of visited items		
		int numVisited = 0;
		// a 2D array of booleans representing if a vertex has been visited
		boolean[][] visited = new boolean[G.getWidth()][G.getHeight()];
	
		// a LinkedList acting a queue
		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();
		// a count for the number of Components
		int count = 0;
		
		// get the first PixelVertex and add it to the queue
		PixelVertex v = G.getPixelVertex(0,0);
		q.addLast(v);
	
		// the total number of Verticies = Width * Height thus this outer loop runs until every vertex has been visited
		// every vertex only gets visited once, thus this loop will run in n time
		while (numVisited < G.getWidth() * G.getHeight()){
			
			// perform a BFS counting the number of verticies visited
			// takes at most n time			
			while (q.size() != 0){
				v = q.remove();
				if (!visited[v.getX()][v.getY()]){				
					visited[v.getX()][v.getY()] = true;
					numVisited++;
				}
				PixelVertex[] neighbours = v.getNeighbours();				
				
				
				for (int i = 0; i < v.getDegree(); i++){
					if (!visited[neighbours[i].getX()][neighbours[i].getY()]){
						visited[neighbours[i].getX()][neighbours[i].getY()] = true;
						numVisited++;
						q.addLast(neighbours[i]);
					}
				}
			}

			count++;	

			// search through the array of visited Verticies and find an unvisited one
			// takes at most n time
			
			boolean b = false;
			for (int row = 0; row < G.getWidth(); row++){
				for (int col = 0; col < G.getHeight(); col++){
					// if an unvisited Vertex is found, add it to the queue to perform another BFS					
					if (!visited[row][col]){
						b = true;
						
						v = G.getPixelVertex(row,col);
						q.addLast(v);	
	
						break;
					}
				}
				if(b){
					break;
				}
			}
					
		}


		return count;
	}
}
