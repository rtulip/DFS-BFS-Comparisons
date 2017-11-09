/* PixelVertex.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Vertex Data Structure


   B. Bird - 07/03/2017
*/




public class PixelVertex{
	// X & Y hold the x & y co-ordinates	
	int X;
	int Y;
	// a count for the number of neighbours this vertex has
	int numNeighbours = 0;

	// an array of lengh 4 with all the neighbours of a PixelVertex
	PixelVertex Neighbours[] = new PixelVertex[4];
	// a boolean to check in constant time if a PixelVertex has been visited
	boolean visited = false;

	/* Add a constructor here (if necessary) */
	// only requires the X & Y co-ordinates
	public PixelVertex(int x, int y){

		X = x;
		Y = y;

				
	
	}	


	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		return X;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return Y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(){
		return Neighbours;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
		
	// Adds a new Neighbour if possible
	public void addNeighbour(PixelVertex newNeighbour){
		if( numNeighbours != 4){
			Neighbours[numNeighbours] = newNeighbour;
			numNeighbours++;
		}	

	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	
	// removes a neighbour if it is in the list of neighbours and shifts all other Verticies in the Neighbours appropriately 
	// because Neighbours is of constant time, shifting takes constant time
	public void removeNeighbour(PixelVertex neighbour){
		
		for (int i = 0; i < numNeighbours; i++){
			if (Neighbours[i].compareTo(neighbour) == 1){
				for (int j = i+1; j < numNeighbours; j++){
					Neighbours[i] = Neighbours[j];
					i++;
				}	
				numNeighbours--;
				break;
			} 
		}
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
		return numNeighbours;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
	
		for (int i = 0; i < numNeighbours; i++){
			if (Neighbours[i].compareTo(otherVertex) == 1){
				return true;
			}
		}		

		return false;

	}
	
	public int compareTo(PixelVertex otherVertex){
		if (this.getX() == otherVertex.getX() && this.getY() == otherVertex.getY()){
			return 1;
		} else {
			return 0;
		}
	}

	// checks the visited status of a vertex
	public boolean getVisited(){
		return visited;
	}
	// updates the visited status of a vertex
	public void setVisited(boolean tf){
		visited = tf;
		
	}
	


	
}
