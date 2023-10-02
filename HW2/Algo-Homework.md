The main facets of this code that need to be explained are: how are the various lines of the diagram represented, how are the lines compared to each other to figure out where they are positioned to one another, how is the actual Binary Space Partition tree assembled, and how is the tree traversed to print out the lines in order from back (left) to front (right)? To begin, the lines of the diagram are represented using a series of Coordinate objects, which are simple pairs of integers that represent the x and y coordinates of a line along a Cartesian coordinate plane. The lines have been given arbitrary values that place them positionally in accordance to where they are in the image, as the image itself has no reference for their true lengths or start and end values. Each line of the diagram, as mentioned before, has two Coordinate objects, which represent the starting point of the line and the ending point of the line. The Line object also has an orientation value, represented as a character, either "h" for horizontal, "v" for vertical, or "d" for diagonal. This orientation is important to include because it allows for later processing to be done when comparing the lines to each other. The total set of lines that are in the diagram are collected together in an array labeled "map", which we can later iterate through in order to add lines procedurally to a BSP tree. Addressing the second question, and arguably the most complicated process of the program itself, is how each line is compared to one another. When adding these lines to the BSP tree we need to know whether the line being added is in front, behind, or intersects the line previously in the tree. To handle this we operate off of some assumptions. First, if two lines have the same slope they are going to either overlap or run parallel to each other. We can first check that the two lines are not the same, and if not we can then compare either the values of the x coordinates of the first line to the second line, should the two of them be vertical lines. This can be done because vertical lines have start and end points with the same x values, so if both lines are vertical all we need to do is see if the second line (the one we are adding) has a larger x value or a smaller one, meaning the second line is either in front or behind the first line respectively. This logic also applies for horizontal lines, but instead of comparing the x values we compare the y values to each other. If the y value is larger in this case the line is behind the first line, and if not it is in front of the first line. Diagonal lines are far trickier to handle, as they would need to possess identical slopes, otherwise you would need to code and resolve some sort of intercept of the line, however for this problem there is only one diagonal line so this code was not implemented. When lines are not parallel to each other, and instead intersect perpendicularly all one needs to do is determine if the second line exceeds the upper bounds of the first line in some fashion. For example, if a horizontal line serves as our route all we need to check is that the second line's y values for the starting coordinate and ending coordinate are either both greater than/equal to or both less than/equal to the y value of the horizontal line. If they are both greater than/less than/equal to then we know that this line is either behind/in front/slices the first line, and we can handle each case as needed. The same logic applies for if the first line is vertical, where we look to see if the x coordinate values of the second line are either both greater than/less than/equal to the x value of the first line. We follow this same process, and specific to this program a series of integers correspond with the conditions described. If a line is in front of the first one the method returns a positive 1, if a line is behind the first one the method returns a negative 1, if the two lines are the same a zero is returned, if the lines intersected where the second line was vertical a positive 2 is returned, and if the lines intersected where the second line was horizontal a negative 2 is returned. These returned values are then used during the BSP tree construction process, described later. To construct the BSP tree a Node class and BSP class must be created. The Node class holds three variables, a Line value, and then a behind/back Node as well as a front Node. A newly constructed Node must be given a line to assign to its value variable, and the front and back are both null nodes. The BSP class is given an integer variable to stand for the size of the tree, and then a node variable that serves as the head of the node. Constructing/inserting new nodes into a tree is handled by the method insert(Node n, Line l) with Line l is the new line that you are adding into the tree. Inserting a new line first requires that the size of the tree is checked and ensured that it is not 0, because if the tree does have a size of 0 then this line must become the head of the tree, and then the method is exited. If the size is not 0 we then check that the head/node passed as an argument is not null. If the node is null we create a new node with the value of line l and assign n the value of this new node, then exiting out. If either of these checks is passed over, meaning the tree's size is greater than zero and the node is not null we then go to the series of comparisons, looking to see if the line being added is in front of, behind, or intersecting the line already in the tree. Depending on what is returned we recursively call insert(Node n, Line l) where Node n is now either the front Node of Node n or the back Node of Node n. If there is an intersection we divide the line along the intersection and assign the portion behind the line already added to the back Node and the portion in front of the line already added to the front Node, with these again being recursive calls. These calls work because they will eventually reach a node that is null, and will then be added to the tree at their correct positions. The final portion of the program that requires a more in depth explanation would be the inOrderTraversal() method of the BSP class, which allows a user to see the start and end coordinates for each line from the very back side of the tree to the front side. This is done by recursively calling the inOrderTraversal of a tree for the left side, which will continue calling the left side of each node until there is no longer a left node to reference, where it will then print out the start and end point of the line at the current node, and as the recursion wraps back up it will print out the parent of that left node, then it will go down the right hand side of itself, which then once again searches for a left side, ultimately only printing out the start point coordinates and end point coordinates once it finds a leaf node.
