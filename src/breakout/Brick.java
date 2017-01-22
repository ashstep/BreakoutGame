package breakout;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle{
    public static final Paint BRICK_COLOR1 = Color.CRIMSON; 
    public static final Paint BRICK_COLOR2 = Color.FUCHSIA; 
    public static final Paint BRICK_COLOR3 = Color.BLANCHEDALMOND; 
    public static final int BRICK_HEIGHT = 20;
    public static final int NUM_BRICKS_PER_ROW = 10;
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_SPACE_BETWEEN_EACH = 10;
    public static final int BRICK_SPACE_BETWEEN_DIFFROWS = 10;
    public static final int BRICK_Y_OFFSET = 70;
    private int point;
    private double xcoordinateofBrick;

	public Brick(double xLocation, double yLocation, int brickWidth, int brickHeight, int pointvalue, Paint brickColor1){
		this.setFill(brickColor1);
		this.setX(xLocation);
		this.setY(yLocation);
		this.setWidth(brickWidth);
		this.setHeight(brickHeight);
		point = pointvalue;
	}
	
	public int getPointValue(Brick b){
		return b.point;
	}
	

	public ArrayList<Brick> eachRowofBricks(int rowNum, int pointValue, Paint brickColor1, int brickWidth, int brickHeight){
		Brick b;
		ArrayList<Brick> particles = new ArrayList<Brick>();
		double xcoordinate = 0;
		double ycoordinate = BRICK_Y_OFFSET;

		for(int j = 0; j < rowNum ; j++){
			xcoordinate = 0;
			for(int i = 0; i < NUM_BRICKS_PER_ROW ; i++){
				b = new Brick(xcoordinate, ycoordinate, brickWidth, brickHeight, pointValue, brickColor1);
				particles.add(b);
				xcoordinate += BRICK_WIDTH + BRICK_SPACE_BETWEEN_EACH;
				
				//for each brick, if its in the top row, then drop power ups
				//powerup1
				if(xcoordinate == 0){
					
				}
				
				
				
			}
			ycoordinate = ycoordinate + (j+1)*( BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS);
		}
		return particles;
	}
	
	

}




