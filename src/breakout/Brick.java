package breakout;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/*
 * This class creates the bricks that will be destroyed. Different bricks on different levels have different point amounts
 */



public class Brick extends Rectangle{
	private  final int BRICK_HEIGHT = 20;
	private  final int NUM_BRICKS_PER_ROW = 10;
	private  final int BRICK_WIDTH = 40;
	private  final int BRICK_SPACE_BETWEEN_EACH = 10;
	private  final int BRICK_SPACE_BETWEEN_DIFFROWS = 10;
	private  final int BRICK_Y_OFFSET = 70;
	private int pointValueOfBrick;
	private Random r;
	private int result;

	public Brick(double xLocation, double yLocation, int brickWidth, int brickHeight, int pointvalue, Paint brickColor1){
		this.setFill(brickColor1);
		this.setX(xLocation);
		this.setY(yLocation);
		this.setWidth(brickWidth);
		this.setHeight(brickHeight);
		pointValueOfBrick = pointvalue;
		r = new Random();
		result = r.nextInt(1090) + 10;
	}
	public int getPointValue(Brick b){
		return b.pointValueOfBrick;
	}
	public int getRandomNumber(Brick b){
		return b.result;
	}
	public ArrayList<Brick> eachRowofBricks( int rowNum, int pointValue, Paint brickColor1, int brickWidth, int brickHeight){
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
			}
			ycoordinate = ycoordinate + (j+1)*( BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS);
		}
		return particles;
	}
}
