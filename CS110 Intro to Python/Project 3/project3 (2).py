#first and second are x, y
#fifth field is brightness, #7th is name


def loadStars(filename, t):  #this is the function that will generate the stars
    starDict={}  #here is the new dictioanry that will hold the star keys and tehir values 
    bright=0
    data=open(filename, 'r') 
    lines=data.readlines()                       #here I open the data file, read it, and then make the lines a list of strings
    for z in lines:                         #here I iterate over each line to extract information
        z.strip()                                #this jsut makes sure there are no unnecessairy white spaces
        dic=z.split(" ")                        #this takes each element of the list (a single line) and splits that into x,y, name values
                                       #do it by length of line, if greater than len7 then it has names, put names back into string, then seperate that by semi colons
        if len(dic)>7:
                    

        starDict[dic[b]]= (dic[0],dic[1],dic[4])         #this adds the lsit to the dictionary with the name of the star as the key and the x,y as teh value
        
        x,y=convertXY(dic[0],dic[1], 275)
        if float(dic[4])>bright:
             bright=float(dic[4])
        drawStar(x,y,t,dic[4], "yellow")
    for m in lines:
        dic=m.split(" ")
        if float(dic[4])==bright:
            x,y=convertXY(dic[0],dic[1], 275)
            drawStar(x,y,t,dic[4], "green")
            t.up()
            t.goto(-270,270)
            t.down()
            t.write(("The brightest star is:", dic[6]), False, "left", ('Arial', "yellow", 12))
            print("monkey")
    return starDict                                           #the fucntion returns the new dictionary

    
def convertXY(orgX, orgY, width):
    x=float(orgX)*width
    y=float(orgY)*width
    return x,y

def drawStar(x, y, t, brightness,col):
    t.up()
    t.goto(x,y)                                               #the turtle will go to the cordinates given by the lines
    t.down()
    t.color(col)                                    #the circle representating each star will be filled yellow
    t.begin_fill()
    t.circle(1)                                  #each circle has a radius of one
    t.end_fill()


def drawConstellations(dicStars,filename, t):
    const=open(filename, 'r')
    line=const.readlines()
    pic=line.split(",")
    for j in range(0,len(pic)):
        
        if j==(len(pic)-1):
            break
        x1,y1,b=float(dicStars[pic[j]])
        x2,y2,b=float(dicStars[pic[j+1]])
        drawLine(x1, y1, x2,y2,t, "white")
        


def drawLine(x1, y1, x2,y2,t, col):
    t.up()
    t.goto(x1,y1)
    t.down()
    t.color(col)
    t.goto(x2,y2)





def main():
    import turtle
    scr=turtle.Screen()
    bob=turtle.Turtle()               #here I set up a turtle and canvas
    scr.bgcolor("dark blue")
    scr.tracer(0)
    dicStars=loadStars("stars", bob)          #this calls the function I defined earlier using the file given
    import glob
    listFiles=glob.glob("_lines.txt")
    #for filename in listFiles:
        #drawConstellations(dicStars,filename, bob)
    bob.ht()                          #this hides the turtle cursor at the end
    scr.update()

main()
