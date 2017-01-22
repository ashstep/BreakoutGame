package breakout;
import java.util.ArrayList;
import java.util.Random;

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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
/*
 * ASHKA STEPHEN AAS74
 * 
 * ABOUT THIS CLASS:
 * This is my main class for the game. The rules and directions are stated on the splash screen. 
 * Please read the directions carefully before playing, as you "lives" may depend on it. 
 * 
 * 
 */
public class BreakoutGame extends Application {
	private static final String TITLE = "Breakout";
	private static final int SIZE = 500;
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int KEY_INPUT_SPEED = 7;
	private static final double GROWTH_RATE = 1.1;
	//BRICK INFORMATION
	private static final int BRICK_HEIGHT = 20;
	private static final int BRICK_WIDTH = 40;
	private static final int BRICK_SPACE_BETWEEN_DIFFROWS = 10;
	private static final int BRICK_Y_OFFSET = 70;
	private int LOW_POINT_BRICK = 10;
	private int MED_POINT_BRICK = 40;
	private int HIGH_POINT_BRICK = 100;
	private static final Paint LOW_POINT_BRICK_COLOR = Color.BLUE; 
	private static final Paint MED_POINT_BRICK_COLOR = Color.DARKBLUE; 
	private static final Paint HIGH_POINT_BRICK_COLOR = Color.BLUEVIOLET; 
	//POWER UP
	private static final int POWERUP_HEIGHT = 10;
	private static final int POWERUP_WIDTH = 60;
	private int yforPowerUp = 35;
	//PADDLE
	private int BALL_RADIUS = 10;
	private int xspeed = 30;
	private int yspeed = 30;
	private int PADDLE_WIDTH = 40;
	private int PADDLE_HEIGHT = 10;
	//ENCOURAGEMENT FOR THE PLAYER AND OTHER STRINGS
	private static final String KEEPGOING = "Keep Going!";
	private static final String GAME_LOST = "Please exit the screen.";
	private static final String GAME_WON = "You have won the whole game!";
	private String header = "Directions and Rules:\n";
	private String space = "\n";
	private String startGameMessage = "Start the game!";
	//PLAYER STATS
	private int CURRENT_SCORE = 0;
	private int LIFE_COUNT = 3;
	private int CURRENT_LEVEL = 1;
	//OTHER
	private boolean levelHasBeenSkipped, powerUp1Set, powerUp2Set, powerUp3Set;
	private Timeline animation;
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
	private Group root0;
	private Powerup powerup;
	private Scene splashScreen;
	private Label printThis, scoreboard;
	private int brickRandomNum;
	
	//Set up splash screen for directions and other information
	@Override
	public void start(Stage primaryStage) throws Exception {
		root0 = new Group();
		splashScreen = new Scene(root0, SIZE, SIZE);
		splashScreen.setFill(Color.WHEAT);
		buttonInitialization(startGameMessage, 1);
		Label splashLabel = new Label("Welcome to Breakout\nBy ASHKA STEPHEN (AAS74)\n" + header + "Destroy bricks to get to the other wall. If your ball hits the ground, you lose a life.\n" + "You start with 3 lives. To win, pass all three levels by hitting the other wall.\n" + "The score is shown to track your progress. Score doesn't determine passing the level.\n\n"+ "BLUE boxes are worth 10 points.\n"+ "DEEP BLUE boxes are worth 40 points.\n" + "PURPLE boxes are worth 100 points!\n\n"  + "Some shortcuts and cheats:\n" + "PRESS L to get another life." + "PRESS S to skip to the next level!\n" + "Hint: Hit the ball with the corner of the paddle to see it slide and bounce back up!\n " + space + "POWER UPS: \n" +  "Light blue gives you more points.\n" + "Dark Gold gives a bigger paddle.\n" + "Purple gives a bigger ball.\n"  + "Some power ups are more common than others, so don't worry if you can't get them all!\n" +  "BEWARE: \n" + "The ball resets to the center of the screen IMMEDIATELY after you lose a life.\n" + "so make sure that you're prepared! We want to challenge you.\n" + "ONLY ONE power up is active at any given moment.\n" + "This means that once you hit a block with another power up, the one currently\nin play will be deactivated."+ "");
		root0.getChildren().addAll(splashLabel);
		splashScreen.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		primaryStage.setScene(splashScreen);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}

	/*
	 * Set up level one settings and game loop
	 */
	private void setupLevel1(int brickPoints, Paint highPointBrickColor) {
		clearWholeScreen();
		resetBall(BALL_RADIUS,Color.BLACK);
		resetPaddle(PADDLE_WIDTH, Color.ALICEBLUE);
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
		brick = new Brick(0, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(1,brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		}

	/*
	 * Set up level two settings and game loop (again because we want to ensure the ball speeds up) 
	 */
	private void setupLevel2(int brickPoints, Paint highPointBrickColor){
		clearWholeScreen();
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
		resetBall(BALL_RADIUS, Color.BLACK);
		resetPaddle(PADDLE_WIDTH+5, Color.ALICEBLUE);
		brick = new Brick(BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(2, brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();}
	
	/*
	 * Set up level three settings and game loop(again because we want to ensure the ball speeds up)
	 */
	private void setupLevel3(int brickPoints, Paint highPointBrickColor){
		animation.stop();
		clearWholeScreen();
		resetBall(BALL_RADIUS, Color.BLACK);
		resetPaddle(PADDLE_WIDTH+10, Color.ALICEBLUE);
		printTextInitial(KEEPGOING);
		playersScoreboardInitial(CURRENT_SCORE,  CURRENT_LEVEL,  LIFE_COUNT);
		brick = new Brick(BRICK_HEIGHT + BRICK_SPACE_BETWEEN_DIFFROWS, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, brickPoints, highPointBrickColor);
		ArrayList<Brick> brickrow = brick.eachRowofBricks(3,brickPoints, highPointBrickColor, BRICK_WIDTH, BRICK_HEIGHT);
		root0.getChildren().addAll(brickrow);

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, brickrow));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/*
	 * Initialize buttons for various levels and actions
	 */
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
			button.setOnAction(e -> setupLevel3(HIGH_POINT_BRICK, HIGH_POINT_BRICK_COLOR));}}

	//reset the ball onto the screen 
	private void resetBall(int radius, Paint color){
		ball = new Ball(radius, SIZE, SIZE);
		ball.setFill(color);
		ball.xspeed = xspeed;
		ball.yspeed = yspeed;
		root0.getChildren().add(ball);}

	//reset the paddle onto the screen 
	private void resetPaddle(int paddleWidth, Paint color){
		paddle = new Paddle(paddleWidth, PADDLE_HEIGHT, SIZE, SIZE);
		paddle.setFill(color);
		root0.getChildren().add(paddle);}

	/*
	 * Step function to ensure movement of BOTH the ball and the power ups as they fall (IF they exist).
	 * Performs checks IN REAL TIME (such as checking if any collision has occurred and what to do in each case. 
	 * These checks are necessary for the functioning of the program, as it prevents lag.
	 */
	private void step (double elapsedTime, ArrayList<Brick> brickrow) {
		ball.setCenterX(ball.getCenterX() + xspeed * elapsedTime);
		ball.setCenterY(ball.getCenterY() + yspeed * elapsedTime);
		if(powerUpExists()){
			powerup.setY(powerup.getY() - yforPowerUp * -elapsedTime*0.3);
			resetPowerUpBoolean();}
		if (checkifBallHitsPaddle()) {
			yspeed *= -1 ;}
		if (ball.ballHitsSideWalls()) {
			xspeed  *= -1;}
		if(checkifBallHitsBrick(brickrow, brick)){
			yspeed*=-1;}
		
		//Power up enacted and removed once it hits the paddle 
		if(checkifPowerUpHitsPaddle()){
			if(powerUp1Set){
				CURRENT_SCORE+=powerup.getPointValue(powerup);
				powerUp1Set = false;}
			if(powerUp2Set){
				PADDLE_WIDTH+=powerup.getPaddleLengthValue(powerup);
				root0.getChildren().remove(paddle);
				resetPaddle(powerup.getPaddleLengthValue(powerup), Color.TURQUOISE);
				powerUp2Set = false;}
			if(powerUp3Set){
				BALL_RADIUS+=powerup.getRadiusLengthValue(powerup);
				System.out.println("third");
				resetBall(BALL_RADIUS, Color.DARKSALMON);
				System.out.println("fourth");
				powerUp3Set = false;}
			root0.getChildren().remove(powerup);
		}
		
		if (LIFE_COUNT == 0 || (CURRENT_LEVEL>3 && levelHasBeenSkipped)){
			clearWholeScreen();
			printTextInitial(GAME_LOST);}
		if (ball.ballHitsBottomWall()) {
			LIFE_COUNT--;
			ballResetCenterLocation();}
		
		//Action to reset to the next level under the following conditions
		if(ball.ballHitsTopWall() || levelHasBeenSkipped || brickrow.isEmpty()){
			levelHasBeenSkipped = false;
			CURRENT_LEVEL++;
			if(CURRENT_LEVEL==2){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", CURRENT_LEVEL);}
			if(CURRENT_LEVEL == 3){
				clearWholeScreen();
				buttonInitialization("For next Level, click here", CURRENT_LEVEL);}
			if(CURRENT_LEVEL >= 4){
				printText(GAME_WON);
				updateLabel(0, 0,  0);
				animation.stop();}}
		ball.setCenterX(ball.getCenterX() + xspeed * elapsedTime);
		ball.setCenterY(ball.getCenterY() + yspeed * elapsedTime);
		
		//If power up exists keep updating it
		if(powerUpExists()){
			powerup.setY(powerup.getY() - yforPowerUp * -elapsedTime*0.3);}
		
		//if you haven't finished the game, keep updating score
		if(CURRENT_LEVEL < 4){
			updateLabel(CURRENT_SCORE, CURRENT_LEVEL, LIFE_COUNT);}}

	/*
	 * Resetting the booleans that keep track of which power ups result in which actions
	 * In other parts of the code this helps us see what to do.
	 */
	private void resetPowerUpBoolean() {
		if((powerup.powerUpHitsBottomWall() && powerUpExists())){
			clearCurrentPowerUp();
			if(powerUp1Set){
				powerUp1Set = false;}
			if(powerUp2Set){
				powerUp2Set = false;}
			if(powerUp3Set){
				powerUp3Set = false;}}}

	//Reset the ball to the center location (usually once it falls off the screen)
	private void ballResetCenterLocation() {
		ball.setCenterX(SIZE/2 - ball.getRadius()/2);
		ball.setCenterY(SIZE/2 - ball.getRadius()/2);}

	//Update the game label in real time so that an updated version of the score is always seen
	private void updateLabel(int CURRENT_SCORE,  int CURRENT_LEVEL, int LIFE_COUNT) {
		if(LIFE_COUNT == 0){
			printText(GAME_LOST);}
		else  {
			printText(KEEPGOING);}
		playersScoreboard(CURRENT_SCORE, CURRENT_LEVEL, LIFE_COUNT);}

	//Print the text to the screen initializer (the encouraging message in the corner)
	private void printTextInitial(String text){
		printThis = new Label();
		printText(text);
		root0.getChildren().add(printThis);
	}
	
	//Print the text to the screen thereafter (the encouraging message in the corner)
	private void printText(String text){
		printThis.setText(text);
	}
	//Print the score to the screen initializer
	private void playersScoreboardInitial(int CURRENT_SCORE, int CURRENTLEVEL, int LIFE_COUNT){
		scoreboard = new Label();
		scoreboard.setLayoutX(400);
		scoreboard.setLayoutY(0);
		playersScoreboard(CURRENT_SCORE,  CURRENTLEVEL,  LIFE_COUNT);
		root0.getChildren().add(scoreboard);
	}
	
	//Print the score to the screen thereafter
	private void playersScoreboard(int realTimeScore, int realTimeLevel, int realTimeLife){
		scoreboard.setText("Score:  " + realTimeScore + space +"Level:  " + realTimeLevel + space + "Lives left:  " + realTimeLife);
	}

	//Clear screen and start afresh -> level transitions
	private void clearWholeScreen(){
		root0.getChildren().clear();}

	//Clear power ups
	private void clearCurrentPowerUp(){
		if(powerUpExists()){
			root0.getChildren().remove(powerup);}}

	//If ball hits brick, carry out actions of 1. deleting the brick, 2. increasing points, 3. checking for a power up through random numbers (brickRandomNum picks the power up).
	public boolean checkifBallHitsBrick(ArrayList<Brick> brickrow , Brick brick){
		for(Brick b: brickrow ){
			Shape collisionCheck = Shape.intersect(b, ball);
			if (collisionCheck.getBoundsInLocal().getWidth()  != -1){
				double saveBrickXlocationforPowerUp = b.getX();
				double saveBrickYlocationforPowerUp = b.getY();
				root0.getChildren().remove(b);
				brickrow.remove(b);
				CURRENT_SCORE += brick.getPointValue(brick);
				brickRandomNum = b.getRandomNumber(b);
				generateRandomPowerUp(saveBrickXlocationforPowerUp, saveBrickYlocationforPowerUp, brickRandomNum);
				return true;}}
		return false;}
	
	/*
	 * Power up generated based on whether a random number generated by the brick falls in specified ranges
	 * Since ranges differ in size some power ups appear more than others!
	 */
	private void generateRandomPowerUp(double saveBrickXlocationforPowerUp, double saveBrickYlocationforPowerUp, int brickRandomNum ) {
		if(brickRandomNum > 200 && brickRandomNum< 500){
			clearCurrentPowerUp();
			powerUp1Set = true;
			powerup = new Powerup(true, false, false, POWERUP_HEIGHT, POWERUP_WIDTH, saveBrickXlocationforPowerUp, saveBrickYlocationforPowerUp, Color.DEEPSKYBLUE);
			root0.getChildren().add(powerup);
		}
		if(brickRandomNum>=500 && brickRandomNum<700){
			clearCurrentPowerUp();
			powerUp2Set = true;
			powerup = new Powerup(false, true, false, POWERUP_HEIGHT, POWERUP_WIDTH, saveBrickXlocationforPowerUp, saveBrickYlocationforPowerUp, Color.DARKGOLDENROD);
			root0.getChildren().add(powerup);
		}
		if(brickRandomNum>=700 && brickRandomNum<750){
			clearCurrentPowerUp();
			powerUp3Set = true;
			powerup = new Powerup(false, false, true, POWERUP_HEIGHT, POWERUP_WIDTH, saveBrickXlocationforPowerUp, saveBrickYlocationforPowerUp, Color.MEDIUMSLATEBLUE);
			root0.getChildren().add(powerup);}}

	//Is a power up currently in play?
	private boolean powerUpExists(){
		if(powerUp1Set || powerUp2Set || powerUp3Set){
			return true;}
		return false;}

	//If power up hits paddle 1. it must disappear and 2. it must carry out its functions
	private boolean checkifPowerUpHitsPaddle(){
		if(powerUpExists()){
			Shape intersect = Shape.intersect(paddle,  powerup);
			if(intersect.getBoundsInLocal().getWidth() != -1){
				return true;}}
		return false;}

	//If ball hits paddle check
	private boolean checkifBallHitsPaddle(){
		Shape intersect = Shape.intersect(paddle,  ball);
		if(intersect.getBoundsInLocal().getWidth() != -1){
			return true;}
		return false;}

	//Taking care of cheat keys and paddle movement 
	private  void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			paddle.setX(paddle.getX() + KEY_INPUT_SPEED);}
		else if (code == KeyCode.LEFT) {
			paddle.setX(paddle.getX() - KEY_INPUT_SPEED);}
		else if (code == KeyCode.S){
			levelHasBeenSkipped = true;}
		else if (code == KeyCode.L){
			LIFE_COUNT++;}}
	
	//Start
	public static void main (String[] args) {
		launch(args);}}