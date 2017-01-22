package breakout;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javafx.scene.shape.Rectangle;
public class Paddle extends Rectangle{
    public static final Paint PADDLE_COLOR = Color.BISQUE; 
    public static final int PADDLE_WIDTH = 40;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_Y_OFFSET = 25;

	public  Paddle(int paddleWidth, int paddleHeight, int widthWindow, int heightWindow ){
		double XlocationofPaddle = widthWindow/2 - paddleWidth/2; 
		double YlocationofPaddle = heightWindow - paddleHeight - PADDLE_Y_OFFSET; 
		this.setX(XlocationofPaddle);
		this.setY(YlocationofPaddle);
		this.setWidth(paddleWidth);
		this.setHeight(paddleHeight);
	}
}

