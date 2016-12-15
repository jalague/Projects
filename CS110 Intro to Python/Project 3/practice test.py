def main():
    import turtle
    import glob
    scr=turtle.Screen()
    bob=turtle.Turtle()
    scr.bgcolor("black")
    listFiles=glob.glob("*_lines.txt")
    for filename in listFiles:
        drawConstellation(dicStars,filename, bob)




def drawConstellation(dicStars,filename, t):
    const=open(filename, 'r')
    line=const.readlines()
    pic=line.split(",")
    for j in range(0,len(pic)-2):
        
        x1,y1=dicStars[pic[j]]
        x2,y2=dicStars[pic[j+1]]
        print("Adfa")
        drawLine(x1, y1, x2,y2,t, "white")
    const.close()
    
        
def drawLine(x1, y1, x2,y2,t, col):
    t.up()
    t.goto(x1,y1)
    t.down()
    t.color(col)
    t.goto(x2,y2)


main()
