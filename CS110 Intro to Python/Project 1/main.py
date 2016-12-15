WALL=1    #Wall is given the value of 1 here
def readMaze(mazefile):   #this is the function that will take the maze file and return a matrix
    d=open(mazefile, 'r')     
    lines=d.readlines()     #here I open and read the lines of the file
    
    maze=[]  #here I create the list that will eventually hold the lines of the maze
    for v in lines:   #this corresponds to each line of the file
        lis=[]   #this is so that for each new line of the file there will be a new list
        for x in v:   #this corresponds to each term within one line of the file
            
            if x=="#":
                lis.append(1)    #here each term is read and determined whether it is a 'wall'(1) or empty space(0)
            else:
                lis.append(0)
        
        maze.append(lis)   #here I add each list of each indivdual row to the main maze list
    return maze   #then I return that maze list


def drawSquare(i,j,t,side=10,col="black"):   #this fucntion draws one individual square
    t.goto(i,j)   #the square will start at the 
    t.down()
    t.color(col)
    t.begin_fill()         #the users the color input is used to fill the square
    for x in range(1,5):    #the square is made
        t.forward(side)
        t.left(90)
    t.end_fill()
    t.up()


def drawMaze(maze, t, col):   #this function takes the list of the maze and draws it
    y=150   #these are the intial cordiantes
    
    for i in maze:  
        x=-150        #the x cordinate will always go back to the far left side for each new row
        for j in i:
            
            if j==WALL:     #if the value of the spot (i,j) in the maze is a 1(WALL)   then a black square will be drawn
                t.up()
                
                drawSquare(x,y,t)  
            x=x+10   #the x and y cordinates change after each iteration so that the turtle will follow the list even if there is no square to be drawn
        y=y-10    


def nextMove(maze,i, j, currDirection):   #this fucntion determines what direction the turtle will draw the next square
    if maze[i-1][j]==WALL:
        IsWallNorth=True     #here the function finds where walls surrounding the current square are
    else:
        IsWallNorth=False
    if maze[i][j+1]==WALL:
        IsWallEast=True
    else:
        IsWallEast=False
    if maze[i+1][j]==WALL:
        IsWallSouth=True
    else:
        IsWallSouth=False
    if maze[i][j-1]==WALL:
        IsWallWest=True
    else:
        IsWallWest=False
    if currDirection=="east":        #here the function takes the current heading of the turtle and the information found above, about where the walls are, and determines which direction the turtle should go next 
        if not IsWallNorth:
            return "north"
        elif not IsWallEast:       #it tries left first, then straight, then right, then backwards for all the possiblities of the currentdircetion 
            return "east"
        elif not IsWallSouth:
            return "south"
        elif not IsWallWest:
            return "west"
    elif currDirection=="south":
        if not IsWallEast:
            return "east"
        elif not IsWallSouth:
            return "south"
        elif not IsWallWest:
            return "west"
        elif not IsWallNorth:
            return "north"
    elif currDirection=="west":
        if not IsWallSouth:
            return "south"
        elif not IsWallWest:
            return "west"
        elif not IsWallNorth:
            return "north"
        elif not IsWallEast:
            return "east"
    elif currDirection=="north":
        if not IsWallWest:
            return "west"
        elif not IsWallNorth:
            return "north"
        elif not IsWallEast:
            return "east"
        elif not IsWallSouth:
            return "south"
    

def followLeftWall(maze,t,col):     #this function actually solves the maze
    i=1
    j=0    #these mark the starting points of the maze
    drawSquare(-150,140,t,10,col)   #this calls the draw square function to draw the intial starting point square
    currDirection="east"   #this is the initial direction
    while i!=(len(maze)) or j!=(len(maze[1])): 
        currDirection=nextMove(maze,i,j,currDirection)   #this will find the next direction which the turtle will go
        
        if currDirection=="north":          #these conditionals simply tell change the values of i and j based on which direction the turtle is
            i=i-1
        elif currDirection=="east":
            j=j+1                        
        elif currDirection=="south":
            i=i+1
        elif currDirection=="west":
            j=j-1
        drawSquare(-150+1*(j*10),150-(i*10),t,10,col)
        
        


def main(mazefile):
    maze=readMaze(mazefile)
    import turtle
    scr=turtle.Screen()
    bob=turtle.Turtle()
    scr.tracer(2)
    drawMaze(maze,bob,"black")
    
    followLeftWall(maze,bob,"yellow")
    scr.update()
    


main("testMaze1.txt")   #here I test one of the mazes



