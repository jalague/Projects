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



@app.route('/showimage', methods=['POST'])
def showEditedimage():
    operation=request.form["operation"]
    if operation=="red":
        editImage(1,"distortedImage1.png", "newimage.png")
    elif operation=="green":
        editImage(2,"distortedImage1.png", "newimage.png")
    elif operation=="blue":
        editImage(3,"distortedImage1.png", "newimage.png")
   

operation=request.form["operation"]
    if operation=="red":
        editImage(1,"distortedImage1.png", "newimage.png")
    elif operation=="green":
        editImage(2,"distortedImage1.png", "newimage.png")
    elif operation=="blue":
        editImage(3,"distortedImage1.png", "newimage.png")
    im=openImageFile("newimage.png")
    showImage(im)


    
@app.route('/')
def displayPuzzle():
    print("In displayPuzzle")
    html=''
    html+='<!DOCTYPE html>\n'
    html+='<html>\n'
    html+='<body>\n'

    html+=" <h1>Image Puzzle</h1>\n"
    html+=' <p1> Apply one of the operations below to the image, and see if you can guess what famous object is in the image! </p1>\n'                     

    html+='<img src="home/jalague/Documents/Project 2/Project 2/static/distortedImage1.png" alt="distortedimage" >\n'
    html+='<br>/n'
    

    
    html+='<form>\n'
    html+='Pick an Operation:<br>\n'
    html+='<input type="radio" name="operation" value="red">Set blue and green pixels to 0 and multiple red ones by 20<br>\n'
    html+='<input type="radio" name="operation" value="green">Set blue and red pixels to 0 and multiple green ones by 20<br>\n'
    html+='<input type="radio" name="operation" value="blue">Set blue and green pixels to 0 and multiple red ones by 20\n'
    html+='<form action="/showimage" method="POST" >\n'
    html+='<input type="button" value="Apply Operations">\n'
    html+='</form>\n'

    html+='</form>\n'
    html+='</body>\n'
    html+='</html>\n'
    return html

def guessImage(

    html+='<form>\n'
    html+='Enter Your Guess:<input type="text" name="guess">\n'
    html+='</form>\n'

    
