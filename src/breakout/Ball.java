package breakout;
import javafx.scene.shape.Circle;
/*
 * ASHKA STEPHEN
 * AAS74
 * 
 * ABOUT THIS CLASS: 
 * This class creates the ball and performs checks to see if it has been hit.
 * This helps us determine the next action.
 */
public class Ball extends Circle {
	private static final int SIZE = 500;
    private static final int BALL_RADIUS = 10;
    public int xspeed = 50;
    public int yspeed = 50;
    public Ball(int ballRadius, int widthWindow, int heightWindow){
    	double XlocationofBall = widthWindow/2 - ballRadius/2; 
    	double YlocationofBall = heightWindow/2 - ballRadius/2; 
    	this.setCenterX(XlocationofBall);
    	this.setCenterY(YlocationofBall);
    	this.setRadius(ballRadius);
    }
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
    

