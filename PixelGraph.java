/* PixelGraph.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Graph Data Structure

   B. Bird - 07/03/2017
*/ 

import java.awt.Color;

public class PixelGraph{


	PixelVertex[][] Map;
		
	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/
	public PixelGraph(Color[][] imagePixels){
		

		
		int w = imagePixels.length;

		int h = imagePixels[0].length;

		// a 2D array of PixelVerticies
		Map = new PixelVertex[w][h];
	
		// fills in the Map as PixelVerticies are added and checks for a PixelVertex's Neighbours		
		for (int row = 0; row < w; row++){
			for (int col = 0; col < h; col++){

				PixelVertex pxl = new PixelVertex(row,col);

				if (col != 0){
					if (imagePixels[row][col-1].equals(imagePixels[row][col])){

						pxl.addNeighbour(Map[row][col-1]);
						Map[row][col-1].addNeighbour(pxl);
					}									
				}
			
				if (row != 0){
			
					if (imagePixels[row-1][col].equals(imagePixels[row][col])){
						
						pxl.addNeighbour(Map[row-1][col]);
						Map[row-1][col].addNeighbour(pxl);			
	
					}

				}
				
				Map[row][col] = pxl;

			}	
		}
	

	
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		return Map[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return Map.length;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return Map[0].length;
	}
	
}
