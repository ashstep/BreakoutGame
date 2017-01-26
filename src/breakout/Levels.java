package breakout;
//This entire file is part of my masterpiece.
//Ashka Stephen aas74
/**
 * This is the Levels class that deals with resetting each level so that the main does not have to.
 * The reason I believe this is well designed is because 1. each method has a well defined and short functionality.
 * This makes it good for those reading through the code because it is understandable.
 * Furthermore, this code uses the concepts discussed in both the lectures and the readings. 
 * For example, code is not duplicated in any area. Furthermore, the methods are defined using parameters to differentiate them among various levels.
 */
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Levels {
	private  final int FRAMES_PER_SECOND = 60;
	private  final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private  final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private int BALL_RADIUS = 10;
	private int PADDLE_WIDTH = 40;
	private  final String KEEPGOING = "Keep Going!";
	private int CURRENT_SCORE = 0;
	private int LIFE_COUNT = 3;
	private int CURRENT_LEVEL = 1;
	private  final int BRICK_HEIGHT = 20;
	private  final int BRICK_SPACE_BETWEEN_DIFFROWS = 10;
	private int LOW_POINT_BRICK = 10;
	private int MED_POINT_BRICK = 40;
	private int HIGH_POINT_BRICK = 100;
	private  final Paint LOW_POINT_BRICK_COLOR = Color.BLUE; 
	private  final Paint MED_POINT_BRICK_COLOR = Color.DARKBLUE; 
	private  final Paint HIGH_POINT_BRICK_COLOR = Color.BLUEVIOLET; 
	private Group root0;
	private int PADDLE_HEIGHT = 10;
	private  final int SIZE = 500;
	private  final int BRICK_WIDTH = 40;
	private  final int BRICK_Y_OFFSET = 70;
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
	private Timeline animation;
	private int xspeed = 30;
	private int yspeed = 30;


	private void clearWholeScreen(){
		root0.getChildren().clear();}
	
	private void resetAll(Ball b, Paddle p){
		root0.getChildren().removeAll();
		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, SIZE, SIZE);
		root0.getChildren().addAll(b, p);
	}
	
	private void printTextInitial(String text){
		Label printThis = new Label();
		printThis.setText(text);
		root0.getChildren().add(printThis);
	}

	private void playersScoreboardInitial(int CURRENT_SCORE, int CURRENTLEVEL, int LIFE_COUNT){
		Label scoreboard = new Label();
		scoreboard.setLayoutX(400);
		scoreboard.setLayoutY(0);
		scoreboard.setText("Score:  " + CURRENT_SCORE + "/n" +"Level:  " + CURRENTLEVEL +  "/n" + "Lives left:  " + LIFE_COUNT);
		root0.getChildren().add(scoreboard);
	}
	
	private void levelCommonalities(int brickPoints, Paint highPointBrickColor){
		clearWholeScreen();
		resetAll(ball, paddle);
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
	}
	
	private ArrayList<Brick> brickCreationPerLevel(int xStart, int numRows, int brickPoints, Paint highPointBrickColor) {
		brick = new Brick(xStart, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(numRows,brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);
		return brickrow;
	}
	
	private void setupLevel1(int brickPoints, Paint highPointBrickColor) {
		levelCommonalities(brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brickCreationPerLevel(0, 1, brickPoints, highPointBrickColor);
		setupTimeline(brickrow);
	}

	private void setupLevel2(int brickPoints, Paint highPointBrickColor){
		levelCommonalities(brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brickCreationPerLevel(BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS, 2, brickPoints, highPointBrickColor);
		setupTimeline(brickrow);
	}

	private void setupLevel3(int brickPoints, Paint highPointBrickColor){
		ArrayList<Brick> brickrow = brickCreationPerLevel(0, 1, brickPoints, highPointBrickColor);
		levelCommonalities(brickPoints, highPointBrickColor);
		setupTimeline(brickrow);
	}

	private void setupTimeline(ArrayList<Brick> brickrow) {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step (double elapsedTime, ArrayList<Brick> brickrow) {
		ball.setCenterX(ball.getCenterX() + xspeed * elapsedTime);
		ball.setCenterY(ball.getCenterY() + yspeed * elapsedTime);
		if (checkifBallHitsPaddle()) {
			yspeed *= -1 ;}
		if (ball.ballHitsSideWalls()) {
			xspeed  *= -1;}
		resetToNextLevelUnderFollowingConditions(brickrow);
	}
	
	private boolean checkifBallHitsPaddle(){
		Shape intersect = Shape.intersect(paddle,  ball);
		if(intersect.getBoundsInLocal().getWidth() != -1){
			return true;}
		return false;}

	private void resetToNextLevelUnderFollowingConditions(ArrayList<Brick> brickrow) {
		if(ball.ballHitsTopWall() || brickrow.isEmpty()){
			CURRENT_LEVEL++;
			if(CURRENT_LEVEL==2){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", CURRENT_LEVEL);}
			if(CURRENT_LEVEL == 3){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", CURRENT_LEVEL);}
			if(CURRENT_LEVEL >= 4){
				animation.stop();}}}

	private void buttonInitialization(String text, int levelNumber){
		Button button = new Button(text);
		button.setTranslateX(300);
		button.setTranslateY(450);
		root0.getChildren().add(button);
		if(levelNumber == 1){
			button.setOnAction(e -> setupLevel1(LOW_POINT_BRICK, LOW_POINT_BRICK_COLOR));}
		if(levelNumber == 2){
			button.setOnAction(e -> setupLevel2(MED_POINT_BRICK, MED_POINT_BRICK_COLOR));}
		if(levelNumber == 3){
			button.setOnAction(e -> setupLevel3(HIGH_POINT_BRICK, HIGH_POINT_BRICK_COLOR));}
	}
}
