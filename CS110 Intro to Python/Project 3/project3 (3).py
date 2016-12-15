

def loadStars(filename, t):                                              #this is the function that will generate the stars
    t.clear()
    starDict={}                                             #here is the new dictioanry that will hold the star keys and tehir values 
    bright=1.0
    data=open(filename, 'r') 
    lines=data.readlines()                                            #here I open the data file, read it, and then make the lines a list of strings
    
    for z in lines:                                               #here I iterate over each line to extract information
        dic=z.split(" ")                               #this takes each element of the list (a single line) and splits that into x,y, name values
            
        if float(dic[4])>bright:
             bright=float(dic[4])

        if len(dic)>6:
            newlist=""
            for n in range(6, len(dic)):
                dic[n]=dic[n].replace("\n", "")       #here if the star has a name it is put into a seperate list to be used as a key in the dictionary
                newlist=newlist+" "+dic[n]
                striplist=newlist.strip()         
            newkeys=striplist.split(";")
            
            
            for b in range(0, len(newkeys)):
                newkeys[b]=newkeys[b].strip()
                starDict[newkeys[b]]= (dic[0],dic[1])         #this adds the lsit to the dictionary with the name of the star as the key and the x,y as teh value
                    
        x,y=convertXY(dic[0],dic[1], 275)
        drawStar(x,y,t,float(dic[4]), "yellow")    #this calls the drawstar function to create each star based on its brightness 
    

    for m in lines:
        otherdic=m.split(" ")
        if float(otherdic[4])==bright:
            x,y=convertXY(dic[0],dic[1], 275)
            drawStar(x,y,t,float(otherdic[4]), "green")    #this finds and displays what the brightest star 
            t.up()
            t.goto(-270,270)
            t.down()
            t.color("yellow")
            t.write(("The brightest star is:", otherdic[6]+" "+otherdic[7].replace("\n", "")))
      
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
    t.circle(brightness/4)                                  #each circle has a radius of one
    t.end_fill()


def drawConstellation(dicStars,filename, t):
    const=open(filename, 'r')
    line=const.readlines()
    
    keyss=[]
    for v in range(0, len(line)):
        line[v]=line[v].replace("\n", "")      #this will get rid of the \n in the lists
        a=line[v].split(",")
        keyss.append(a)
       
    keyer=0
    for j in range(0,len(keyss)):
        x1,y1=dicStars[keyss[j][0]]
        x2,y2=dicStars[keyss[j][1]]
            
        drawLine(x1, y1, x2,y2,t, "white")
        
    
    const.close()
    
        


def drawLine(x1, y1, x2,y2,t, col):
    t.up()
    X1,Y1=convertXY(x1,y1, 275)
    t.goto(X1,Y1)
    t.down()
    t.color(col)
    X2,Y2=convertXY(x2,y2, 275)
    t.goto(X2,Y2)




def main():
    import turtle
    import glob
    
    scr=turtle.Screen()
    bob=turtle.Turtle()               #here I set up a turtle and a canvas
    scr.bgcolor("dark blue")
    bob.color("white")
    bob.write("LOADING...",move=False, align="left", font=("Arial", 16, "normal"))
    
    scr.tracer(0)
    scr.bgcolor("dark blue")
    dicStars=loadStars("stars.txt", bob)          #this calls the function I defined earlier using the file given
    
    listFiles=glob.glob("*_lines.txt")
    for filename in listFiles:
        drawConstellation(dicStars,filename, bob)
    bob.ht()                              #this hides the turtle cursor at the end
    scr.update()

main()
