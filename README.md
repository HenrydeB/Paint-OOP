# JPaint
## Table of Contents
* [Sprint 1](#sprint-1)
* [Sprint 2](#sprint-2)
  * [S2 Design Patterns](#s2-design-patterns)
* [Sprint 3](#sprint-2)
  * [S3 Design Patterns](#s3-design-patterns)


## Sprint 1 ##
* This includes functionality to create green rectangles and undo / redo said rectangles
* you may undo several rectangles and redo will bring all of them back in the reverse order
  * This is because of an honest attempt at a Singleton to create global access to two different lists: one for current shapes and one for removed shapes, not sure if this was done right as I've only seen it done at work
* I am not aware of any bugs, but the way I went about handling potential errors may be a little messy
* Project itself may be a little messy

## Sprint 2 ##
* Able to draw all shapes, with all colors, utilizing all shading types
* When choosing a shading type, primary color is used for all modes, and for fill with outline it is used for the fill, secondary for the outline
* In 'select' mode clicking on one shape selects that one shape, clicking off of a shape deselects currently selected shape(s), and clicking and dragging will create an invisible bounding box that will select all shapes within it
* For mouse move, this will offset the currently selected shapes by the distance between on click and offclick, in the dragged direction
* NOTE: Undo and Redo only work for the most current move for this, I was unable to successfully undo a series of moves and redo those series of moves. Will need some refactorization to get this to work properly

  ### S2 Design Patterns ###
  For this sprint, I did my best to utilize 3 design patterns, and have other sections in my code that require some refactorization to meet the criteria of being an actual implementation of said design pattern.
  Below are 3 design patterns that I believe are (or are the closest) to being correct:
  1. Static Factory Pattern: 
       I use this in models/Shapes/ShapeFactory and it is used to determine which shape will be created. One issue is that I do still have to reference the Rectangle and Triangle classes elsewhere in the code so I can correctly implement other parts of the assignment. This will need to be refactored I believe.
  2. Observer Pattern: I utilized the observer pattern to connect my GlobalShapeLists class to the PaintCanvas class. The IShapeSubject interface contains addObserver(IShapeObserver) removeObservers(IShapeObserver), and notifyObservers(). This is located in model/interfaces. The IShapeObserver interface, stored in view/interface is implemented in view/gui/PaintCanvas, and implements an update() method derived from the interface, which then calls repaint()
  3. Strategy Pattern: I utilized the Strategy Pattern in how I determine how to design my shape. Found in view/Strategy, the ShapeDesigner class uses a IShapeDesign interface to reference the 3 different classes that define how to draw the shape (OutlineStrategy, FillStrategy, and OutlineFillStrategy).
 
 Again, there is decent amount of refactorization I need to do to get more of these design patterns working across the board (such as using the state pattern for the MouseActionFactory (which at this point is not even a factory yet). 

 ## Sprint 3 ##
* Able to copy shapes to clipboard
* the copied shape is not deselected
  * If the selected shape that was copied is moved and the the paste action is pressed  then the pasted shape will appear at the origin of the copied shape + the offset I gave to pasted shapes
*  If there is anything in the clipboard while the paste action is pressed, then a duplicate shape to the one that was copied will be added to the main ShapeList, appearing diagonal to the original copied shape at a slight offset.
*  When the delete action is selected from the UI, the selected shape will be removed from the main shapelist and subsequently deleted from the canvas
*  Selected shapes now display an outline, which is at least 5px off from the original shape
*  The Undo & Redo actions now support the Paste and Delete operations

  ### S3 Design Patterns ###
  #### S2 patterns ####
   1. Static Factory Pattern: 
       I use this in models/Shapes/ShapeFactory and it is used to determine which shape will be created. One issue is that I do still have to reference the Rectangle and Triangle classes elsewhere in the code so I can correctly implement other parts of the assignment. This will need to be refactored I believe.
  2. Observer Pattern: I utilized the observer pattern to connect my GlobalShapeLists class to the PaintCanvas class. The IShapeSubject interface contains addObserver(IShapeObserver) removeObservers(IShapeObserver), and notifyObservers(). This is located in model/interfaces. The IShapeObserver interface, stored in view/interface is implemented in view/gui/PaintCanvas, and implements an update() method derived from the interface, which then calls repaint()
  #### S3 patterns ####
  NOTE: The strategy Pattern was listed in the previous sprint because I had already implemented it so I thought may as well list it. I am including it in this sprint's requirements of 4 design patterns which was discussed in class on 08/02
 
  3. Strategy Pattern: I utilized the Strategy Pattern in how I determine how to design my shape. Found in view/Strategy, the ShapeDesigner class uses a IShapeDesign interface to reference the 3 different classes that define how to draw the shape (OutlineStrategy, FillStrategy, OutlineFillStrategy, and SelectedStrategy).
  
  4. Singleton Pattern: I have two different singleton patterns in my code, but namely I want to highlight the GlobalShapeLists class. Within this (and my other singleton), I have several private Lists that I use to store the shapes affected by different actions and my general shapeList, which is my current set of shapes to be rendered on the canvas. It's like my (not) database.

Maybe another?: I was reading into the Decorator pattern and I think I may have gotten close to it? I didn't want to list it because I am pretty sure I am missing something, this would be found in my Shapes folder with my IShape interface, Shape class, and the more specific shape classes that exend Shape. I believe I am missing something before I can confidently say that it is implemented, is that correct?


### GitHub Repo ###
I know this is leading to this exact page, but I don't want to miss something
[JPaint-Henry deBuchananne](https://github.com/HenrydeB/JPaint)

