# BreakoutGame
Java program to play a version of Breakout

## Splash Screen:
- Welcome to the game! (and a short description of the premise)
- Instructions, such as: bounce your ball around, destroying blocks until you hit the other side of the screen once.
- At this point you pass the level.
- There are various kinds of blocks and power ups.

## Prospective Levels of the Game:
- For each level below, you pass the level when your ball hits any location on the next wall.
- For each level below, the block configuration is a rough draft of the spacings of the blocks in each level.
- In addition to the rough draft of the locations below, there will be various kinds of blocks scattered through the design.
- Although these specific distinctions are not shown in the diagrams below, they will be in the final game, as this is how different blocks, power ups, etc. will be accounted for.
- These different kinds of blocks will make the game progressively harder (will likely appear in more quantity in the later levels of the game).
- The ways that the game will get harder as you go up levels is because:
	(1) Number of blocks that need to be destroyed to reach the other end will increase / blocks placed closer to the bottom
	(2) Number of easily-destructable boxes will decrease as levels increase (takes longer to destroy).
 
## Types of Blocks:
- Typical 1 point block that is destroyed after 1 hit
- Some 3 point blocks that are also destroyed after 1 hit.
- Blocks that take 5 hits to destroy
- Blocks that are destroyed after 100+ hits (so "never" in the case of the game).

## Power Ups:
- Get an additional life
- Paddle has a larger width (you would have to switch the image out for this one)
- Slow down the speed of the ball (thus easier to hit)

## Cheat Keys:
- Press L to gain an additional life
- Press 1, 2, 3, or 4 to jump to those levels (no matter where you previously were).
- Press Q to skip the current level you are on
- Press S to slow down the balls speed so that you won't miss it when it's coming 
- Press E to elongate paddle

## Extra Addition to the game: THE POWER HIT
If you hit the ball "x" number of times (say, for example, 10) without the ball being lost (in other words, without you missing a hit)
you will get a POWER HIT, meaning on the "x+1"th hit (next hit), you can hit a large chunck of blocks automatically. (This means that
all of the blocks in that chunk will disappear). 
