package breakout;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
/* 
 * ABOUT THIS CLASS: 
 * This class creates the power ups as needed based on random decisions. It is closely tied to the brick class (relation can be seen in the main class) 
 * because, depending on information provided from the brick class, the power up randomly decides whether to generate or not. Thus, this ensures not every brick will have a power up.
 * This is good because then the user cannot predict what kind of power up will be generated and when.
 */

public class Powerup extends Rectangle{
	private int pointValueofPowerup;
	private int paddleLenghtPowerup;
	private int ballRadiusofPowerup;
	private  final int POWERUP_WIDTH = 70;
	private  final int POWERUP_PADDLE_WIDTH = 70;
	private  final int POWERUP_RADUIS_SIZE = 50;
	private  final int SIZE = 500;

	public Powerup(boolean initializePoints, boolean paddleLength, boolean ballSize, int powerUpWidth, int powerUpHeight, double xLocation, double yLocation, Paint color){
		if(initializePoints){
			pointValueofPowerup = 700;
		}
		if(paddleLength){
			paddleLenghtPowerup = POWERUP_PADDLE_WIDTH;
		}
		if(ballSize){
			ballRadiusofPowerup = POWERUP_RADUIS_SIZE;
		}
		this.setX(xLocation);
		this.setY(yLocation);
		this.setWidth(powerUpWidth);
		this.setHeight(powerUpHeight);
		this.setFill(color);
	}
	public int getPointValue(Powerup powerup){
		return powerup.pointValueofPowerup;
	}
	public int getPaddleLengthValue(Powerup powerup){
		return powerup.paddleLenghtPowerup;
	}
	public int getRadiusLengthValue(Powerup powerup){
		return powerup.ballRadiusofPowerup;
	}
	public boolean powerUpHitsBottomWall(){
		return (this.getY() + POWERUP_WIDTH >= SIZE);
	}	
}
