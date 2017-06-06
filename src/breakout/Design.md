
DESIGN=====================================

Status
	Overall, the code is consistent in layout and description. A thing I could have done better would have been to 
	ensure that all the naming were consistent. For example, one of my methods was named puttingthePowerUpInAction() 
	while another (also an initializing method) was named printTextInitial(). It would have been beneficial, first of
	all, to put all of the similar functions  near each other in location (all the initializers together, all of 
	the updates together, all of the labels together, etc. Other than that, my descriptiveness and style was consistent 
	throughout.Furthermore, the code is generally readable. One could go through and understand what each method does
	and where each fits in the puzzle. This is because the names I used for the methods were descriptive, so comments were
	not usually necessary. I believe that I could have made my methods shorter, however, thus being more concise and readable.
	I do have some dependencies through back channels, such as global variables. At the time they seemed okay, but in retrospect
	it would be better not to have those as it makes the code harder to extend. Some methods also call others which should
	be fixed in the future.
	
	One piece of code is as follows:
====================================================
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
	
	This code may be harder to understand because 1) there are no comments explaining that an array list of bricks to be hit is being made,
and 2) it’s pretty long so most would not want to spend time deciphering what it is if it’s not apparent from the start. 
	
====================================================

	Another is:

	//Is a power up currently in play?
	private boolean powerUpExists(){
		if(powerUp1Set || powerUp2Set || powerUp3Set){
			return true;}
		return false;}

This code is pretty self explanatory and I believe this shows good coding practice because 1) the names explain what is going on, 
2) it’s short, and 3) the reader can take it out of context and know what’s going on.




DESIGN
	Currently my code has similar functions near each other, which is good for readability. Methods are, on average, 
	pretty short but some are very long and can throw the overall structure and style of the code off balance. A way 
	to add a new level to the game is to empty the current scene by clearing everything that has been rooted, and then 
	re-rooting new items. This could be considered as a level change.
	One feature is the power ups. They can do one of three things 1. increase paddle size, 2. increase ball size, and 
	3. give extra points. This feature allows the user to gain more from the game. I made a separate class that dealt 
	with each feature by setting booleans. Since the code deals with this on a case by case basis, this is not a very extendable part 
	of the code —therefore something I can improve on.
	Another feature are the paddle settings —they change color and size. Although this is a pretty simple implementation,
	 it’s interesting because the design I used for this part of the code is very simple and straightforward. Thus I believe this
	 part is optimized. The assumptions that are made that limit its flexibility and extendibility are 1. it’s a rectangle, and
	  2. its location can only be moved L/R. I thought and still think these assumptions are okay because they define a paddle.


Alternate Design
	One decision I made was to include the levels in the main class. Originally this seemed like a great idea, as it 
	would make sense since there are so many components to a level and since it’s tied into the game so much. But in
retrospect, it would be better to make it a separate class from the beginning so it would be easier to make new levels.
It would also make the code more readable in the main. These are the pros of that design which I had not considered carefully 
enough earlier. In retrospect, the way I went about it (by hand making each new level) simply took up more time and space.
	Another design decision I made was to include many different functionalities in the step function. At the time this seemed 
	reasonable, as many components need to be checked for and updated as the clock was ticking. I chose it at the time because of ease 
	and functionality rather than with design in mind. Thus now I would choose to make the function smaller, only implementing the most 
	necessary of checks before outsourcing it.
	One bug is that the paddle does not move smoothly. Another two are very special cases. Most of the time if you skip a level, 
	the next level works fine, but there are other bugs where the score will not update if you correctly go to the next level. A 
	last bug would be the end message. At the end, the screen should display something along the lines of “game over” but currently it will
	 flash that message and then, since the timeline is still running, default back to a “keep going” message that should only appear when you are on a level.


