package breakout;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


public class Ball extends Circle {
	
    public static final int SIZE = 500;
    public static final Paint BALL_COLOR = Color.ALICEBLUE; 
    public static final int BALL_RADIUS = 10;
    public static final int BALL_SPEED = 50;
    public int xspeed = 50;
    public int yspeed = 50;
    Random rand = new Random();
    int angleInDegree = rand.nextInt(360);
    private double getCenterX, getCenterY;



    public Ball(int ballRadius, int widthWindow, int heightWindow){
    	double XlocationofBall = widthWindow/2 - ballRadius/2; 
    	double YlocationofBall = heightWindow/2 - ballRadius/2; 
    	this.setCenterX(XlocationofBall);
    	this.setCenterY(YlocationofBall);
    	this.setRadius(ballRadius);
    }
	
	//ball hitting different walls
    public boolean ballHitsTopWall(){
    	return(this.getCenterY() - BALL_RADIUS <= 0);
    }
    public boolean ballHitsBottomWall(){
    	return (this.getCenterY() + BALL_RADIUS >= SIZE);
    }
    private boolean ballHitsLeftWall(){
    	return (this.getCenterX() - BALL_RADIUS <= 0 );
    }
    private boolean ballHitsRightWall(){
    return (this.getCenterX() + BALL_RADIUS >= SIZE);
    }
    public boolean ballHitsSideWalls(){
    	return (ballHitsRightWall() || ballHitsLeftWall());
    }

    
}

