import flask
import PIL
from imageHelperFunctions import
app=flask.Flask(__name__)

def editImage(option,filename,newname):
    im=openImageFile(filename)
    w,h=size(im)
    
    for i in range(0,h):
        
        for j in range(0,w):
            
            r,g,b= getPixel((j,i),im)
            
            
            if option==1:
                setPixel((j,i),im, (r*20,0,0))
            elif option==2:
                setPixel((j,i),im, (0,g*20,0))
            elif option==3:
                setPixel((j,i),im, (0,0,b*20))
    showImage(im)
    saveImageFile(im,newname,"JPEG")


def showEditedimage(edit

@app.route('/')
def displayPuzzle():
    print("In displayPuzzle")


def guessImage(    
