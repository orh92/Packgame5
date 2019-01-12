#  About the project: 
This is a packman game! So how it works? 
Run the game, and by the menu bar you can start.
Choose the way that you want to play:
First, click the item "Start game from a file" and upload a CSV file to get preset locations:
![github-small](https://raw.githubusercontent.com/orh92/Packgame5/master/1.bmp)

An example of how a CSV file should look:
![github-small](https://raw.githubusercontent.com/orh92/Packgame5/master/2.bmp)

Now, in the item "options" you can play by yourself on "Start game":
![github-small](https://raw.githubusercontent.com/orh92/Packgame5/master/3.bmp)



Or automatically (As soon as the Run button is pressed, the Packmans will move toward the fruit, and each Pacman will run to the nearest fruit and eat it. The goal of all the Packmen is to finish all the fruits that are on the map or until the time is over.):
![github-small](https://raw.githubusercontent.com/orh92/Packgame5/master/4.bmp)



How the game look like:
![github-small](https://raw.githubusercontent.com/orh92/Packgame5/master/5.bmp)



In the end you can see your score, and compare with othe players.


#  How is the project built?
The project built from the next packages: 

  - Geom:
A package of geometry that includes points, lines, paths, circles, squares, etc.
*POINT3D- implements  Geom_element. representing a point in space or on map using both polar coordinates or cartezian. 

*MyCoords -implements coords_converter. Used to calculate mathematical functions.

  - Game:
*Game class- Contains an array of objects related to the game.
*MoveManager- This class implements THREADS. Promotes each Pacman according to the allotted time, and synchronizes all of them.
*PackmanMove- This class implements THREADS. Manages the thread of each Pacman.

 - GameObjects:
 *Player-A class that represents a the main player.
 *Fruit-A class that represents a "target" in a known geographic location.
 *GameObjects class-Contains information of location, speed, radius of the objects.
 *Packman- A class that represents a "robot" with a location, orientation and ability to move (The speed Is defined).
 *Ghost-A class that represents a "robot" with a location, orientation and ability to move (The speed Is defined).
 *Box-A class that represents a obstacle with a location.

 - Packman:
 *main- by this class you can start the game.

 - Path:
 *path class- This class calculates the length of the route that Pacman passed through.
 *AlgoIntersection-Check if the boxes are inside of eachother.
 *ShortestPathAlgo- This class contains the algorithm in which each Pacman searches for the nearest fruit by dijkstra algorithm. The class contains a set of fruits and packmans, and moves along the arrays respectively, matching the fruit to its closest packman and collecting him.
 
 - Map:
 *Map class-This class takes the coordinates of the map in Google Earth and the pixels of the image and converts from pixels to coordinates and vice versa.

 - GUI:
 *MyFrame- This class uses a GUI, produces a window, a menu, uploads the images of the Pacman, fruit and map. In addition, the class implements a MouseListener and ComponentListener with which you can use the mouse and create as many Pacman and Fruit as you want and redesigned the pictures if we increased or reduced the window.
 
 
#  Author: Guy Ankri & Or Hadar
