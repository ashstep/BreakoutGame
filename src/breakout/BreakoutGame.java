package breakout;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutGame extends Application {
	private static final String TITLE = "Breakout";
	private static final int SIZE = 500;
	private static final Paint BACKGROUND = Color.BLACK; 
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int KEY_INPUT_SPEED = 7;
	private static final double GROWTH_RATE = 1.1;


	private static final int BRICK_HEIGHT = 20;
	private static final int NUM_BRICKS_PER_ROW = 10;
	private static final int BRICK_WIDTH = 40;
	private static final int BRICK_SPACE_BETWEEN_EACH = 10;
	public static final int BRICK_SPACE_BETWEEN_DIFFROWS = 10;
	private static final int BRICK_Y_OFFSET = 70;

	private int BALL_RADIUS = 10;
	private int PADDLE_WIDTH = 40;
	private int PADDLE_HEIGHT = 10;

	private int LOW_POINT_BRICK = 10;
	private int MED_POINT_BRICK = 40;
	private int HIGH_POINT_BRICK = 100;
	private static final Paint LOW_POINT_BRICK_COLOR = Color.BLUE; 
	private static final Paint MED_POINT_BRICK_COLOR = Color.DARKBLUE; 
	private static final Paint HIGH_POINT_BRICK_COLOR = Color.BLUEVIOLET; 

	private static final String KEEPGOING = "Keep Going!";
	private static final String GAME_LOST = "You lost the whole game :( Please exit the screen.";
	private static final String GAME_WON = "You have won the whole game!";
	private static final String LEVEL_PASSED = "Look at you go you have passed a level!";

	private int CURRENT_SCORE = 0;
	private int LIFE_COUNT = 3;
	private int CURRENT_LEVEL = 1;
	private boolean hasLevelIncreased;
	private String header = "Directions and Rules:\n";
	private String space = "\n";
	private String startGameMessage = "Start the game!";

	private int xspeed = 30;
	private int yspeed = 30;
	private Timeline animation;
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
	private Group root0;
	private Scene splashScreen;
	private Label printThis, scoreboard;

	@Override
	public void start(Stage primaryStage) throws Exception {

		//splash screen
		root0 = new Group();
		splashScreen = new Scene(root0, SIZE, SIZE);
		splashScreen.setFill(Color.WHEAT);

		//splash screen items
		buttonInitialization(startGameMessage, 1);
		Label splashLabel = new Label("Welcome to Breakout\n\n" + header + "Destroy bricks to get to the other wall. If your ball hits the ground, you lose a life.\n" + "You start with 2 lives. To win, pass all three levels by hitting the other wall.\n" + "The score is shown to track your progress. Score doesn't determine passing the level."+ "BLUE boxes are worth 10 points."+ "DEEP BLUE boxes are worth 40 points." + "PURPLE boxes are worth 100 points!" + space + "Some shortcuts and cheats:\n" + "PRESS L to get another life." + "PRESS S to skip to the next level!" + space + "BEWARE: \n" + "The ball resets to the center of the screen IMMEDIATELY after you lose a life.\n" + "so make sure that you're prepared! We want to challenge you."
				+ "");

		root0.getChildren().addAll(splashLabel);
		splashScreen.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		primaryStage.setScene(splashScreen);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}

	private void setupLevel1(int brickPoints, Paint highPointBrickColor) {
		clearWholeScreen();
		//initializationofSetup(brickPoints, highPointBrickColor);
/*		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		ball.setFill(Color.BLACK);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;
*/		
		resetBall();
		
		paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, SIZE, SIZE);
		paddle.setFill(Color.ALICEBLUE);
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
		root0.getChildren().add(paddle);
		root0.getChildren().add(ball);
		

		brick = new Brick(0, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(1,brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);

		//"game loop"
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	private void buttonInitialization(String text, int levelNumber){
		Button button = new Button(text);
		button.setTranslateX(300);
		button.setTranslateY(350);
		root0.getChildren().add(button);
		if(levelNumber == 1){
			button.setOnAction(e -> setupLevel1(LOW_POINT_BRICK, LOW_POINT_BRICK_COLOR));}
		if(levelNumber == 2){
			button.setOnAction(e -> setupLevel2(MED_POINT_BRICK, MED_POINT_BRICK_COLOR));}
		if(levelNumber == 3){
			button.setOnAction(e -> setupLevel3(HIGH_POINT_BRICK, HIGH_POINT_BRICK_COLOR));}}
	
	private void resetBall(){
		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		ball.setFill(Color.BLACK);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;
	}
	
	private void initializationofSetup( int brickPoints, Paint highPointBrickColor){
		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		ball.setFill(Color.BLACK);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;
		paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, SIZE, SIZE);
		paddle.setFill(Color.ALICEBLUE);
		
		root0.getChildren().add(paddle);
		root0.getChildren().add(ball);
	
	}

	private void setupLevel2(int brickPoints, Paint highPointBrickColor){
		clearWholeScreen();
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);

		//initializationofSetup(brickPoints, highPointBrickColor);
/*		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		ball.setFill(Color.BLACK);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;
*/		
		resetBall();

		paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, SIZE, SIZE);
		paddle.setFill(Color.ALICEBLUE);
		
		root0.getChildren().add(paddle);
		root0.getChildren().add(ball);
		
		brick = new Brick(BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(2, brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

	}

	private void setupLevel3(int brickPoints, Paint highPointBrickColor){
		animation.stop();
		clearWholeScreen();
		//initializationofSetup(brickPoints, highPointBrickColor);
/*		ball = new Ball(BALL_RADIUS, SIZE, SIZE);
		ball.setFill(Color.BLACK);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;*/
		
		paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, SIZE, SIZE);
		paddle.setFill(Color.ALICEBLUE);
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);

		brick = new Brick(BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(3,brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().add(paddle);
		root0.getChildren().add(ball);
		root0.getChildren().addAll(brickrow);

		//"game loop"
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
			yspeed *= -1 ;
		}
		if (ball.ballHitsSideWalls()) {
			xspeed  *= -1;
		}
		if(checkifBallHitsBrick(brickrow, brick)){
			yspeed*=-1;
		}
		if (LIFE_COUNT==0 || (CURRENT_LEVEL>3 && hasLevelIncreased)){
			clearWholeScreen();
			printTextInitial(GAME_LOST);
			animation.stop();
		}

		if (ball.ballHitsBottomWall()) {
			LIFE_COUNT--;
			//reset ball at the center
			ball.setCenterX(SIZE/2 - ball.getRadius()/2);
			ball.setCenterY(SIZE/2 - ball.getRadius()/2);
		}

		//if the level is passed
		if(levelPassed() || brickrow.isEmpty()){
			hasLevelIncreased = false;
			CURRENT_LEVEL++;
			printText(LEVEL_PASSED);

			//set on action for level 2
			if(CURRENT_LEVEL==2){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", 2);

				/*Button a = new Button("For next Level, click here");
				a.setTranslateX(300);
				a.setTranslateY(350);
				root0.getChildren().add(a);
				a.setOnAction(e -> setupLevel2(MED_POINT_BRICK, MED_POINT_BRICK_COLOR));*/
			}

			//if you have passed all levels display win message
			if(CURRENT_LEVEL == 3){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", 3);

				/*Button a = new Button("For next Level, click here");
				a.setTranslateX(300);
				a.setTranslateY(350);
				root0.getChildren().add(a);
				a.setOnAction(e -> setupLevel3(HIGH_POINT_BRICK, HIGH_POINT_BRICK_COLOR));*/
				}
			if(CURRENT_LEVEL == 4){
				printText(GAME_WON);}}
		ball.setCenterX(ball.getCenterX() + xspeed * elapsedTime);
		ball.setCenterY(ball.getCenterY() + yspeed * elapsedTime);
		updateLabel(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
	}

	private boolean levelPassed(){
		return (ball.ballHitsTopWall() || hasLevelIncreased);
	}

	private void updateLabel(int CURRENT_SCORE,  int CURRENT_LEVEL, int LIFE_COUNT) {

		if(LIFE_COUNT == 0){
			printText(GAME_LOST);
		}
		else  {
			printText(KEEPGOING);
		}
		playersScoreboard(CURRENT_SCORE, CURRENT_LEVEL, LIFE_COUNT);
	}

	private void printTextInitial(String text){
		printThis = new Label();
		printText(text);
		root0.getChildren().add(printThis);
	}
	private void printText(String text){
		printThis.setText(text);
	}


	private void playersScoreboardInitial(int CURRENT_SCORE, int CURRENTLEVEL, int LIFE_COUNT){
		scoreboard = new Label();
		scoreboard.setLayoutX(400);
		scoreboard.setLayoutY(0);
		playersScoreboard(CURRENT_SCORE,  CURRENTLEVEL,  LIFE_COUNT);
		root0.getChildren().add(scoreboard);
	}

	private void playersScoreboard(int realTimeScore, int realTimeLevel, int realTimeLife){
		scoreboard.setText("Score:  " + realTimeScore + space +"Level:  " + realTimeLevel + space + "Lives left:  " + realTimeLife);
	}

	private void clearWholeScreen(){
		root0.getChildren().clear();
	}
	public boolean checkifBallHitsBrick(ArrayList<Brick> brickrow , Brick brick){
		for(Brick b: brickrow ){
			Shape collisionCheck = Shape.intersect(b, ball);
			if (collisionCheck.getBoundsInLocal().getWidth()  != -1){
				root0.getChildren().remove(b);
				brickrow.remove(b);
				CURRENT_SCORE += brick.getPointValue(brick);
				return true;
			}
		}
		return false;
	}
	private boolean checkifBallHitsPaddle(){
		Shape intersect = Shape.intersect(paddle,  ball);
		if(intersect.getBoundsInLocal().getWidth() != -1){
			return true;
		}
		return false;
	}


	// What to do each time a key is pressed
	private  void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			paddle.setX(paddle.getX() + KEY_INPUT_SPEED);
		}
		else if (code == KeyCode.LEFT) {
			paddle.setX(paddle.getX() - KEY_INPUT_SPEED);
		}
		else if (code == KeyCode.S){
			hasLevelIncreased = true;
		}
		else if (code == KeyCode.L){
			LIFE_COUNT++;
		}
	}

	//Start
	public static void main (String[] args) {
		launch(args);
	}
}