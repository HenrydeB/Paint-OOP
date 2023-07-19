# JPaint
## Table of Contents
* [Sprint 1](#sprint-1)
* [Sprint 2](#sprint-2)
  * [S2 Design Patterns](#s2-design-patterns)


## Sprint 1 ##
* This includes functionality to create green rectangles and undo / redo said rectangles
* you may undo several rectangles and redo will bring all of them back in the reverse order
  * This is because of an honest attempt at a Singleton to create global access to two different lists: one for current shapes and one for removed shapes, not sure if this was done right as I've only seen it done at work
* I am not aware of any bugs, but the way I went about handling potential errors may be a little messy
* Project itself may be a little messy

## Sprint 2 ##
* Able to draw all shapes, with all colors, utilizing all shading types
* When choosing a shading type, primary color is used for all modes, and for fill and outline it is used for the fill, secondary for the outline
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
 
 Again, there is decent amount of refactorization I need to do to get more of these data structures working across the board (such as using the state pattern for the MouseActionFactory (which at this point is not even a factory yet). 

### GitHub Repo ###
I know this is leading to this exact page, but I don't want to miss something
[JPaint-Henry deBuchananne](https://github.com/HenrydeB/JPaint)

