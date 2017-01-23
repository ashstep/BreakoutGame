package breakout;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/*
 * ASHKA STEPHEN AAS74
 * 
 * ABOUT THIS CLASS:
 * This is my class to make the paddle. This is used to upgrade the paddle and to initially create it in the game.
 */
public class Paddle extends Rectangle{
    public final Paint PADDLE_COLOR = Color.BISQUE; 
    public final int PADDLE_WIDTH = 40;
    public final int PADDLE_HEIGHT = 10;
    public final int PADDLE_Y_OFFSET = 25;

	public  Paddle(int paddleWidth, int paddleHeight, int widthWindow, int heightWindow ){
		double XlocationofPaddle = widthWindow/2 - paddleHeight/2; 
		double YlocationofPaddle = heightWindow - PADDLE_HEIGHT - PADDLE_Y_OFFSET; 
		this.setX(XlocationofPaddle);
		this.setY(YlocationofPaddle);
		this.setWidth(paddleWidth);
		this.setHeight(paddleHeight);
	}
}

