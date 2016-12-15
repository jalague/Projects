import flask
import PIL
from flask import request
from flask import redirect
from imageHelperFunctions import *
import random
import os, os.path

app=flask.Flask(__name__)

pic=random.randint(1,3)
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
    #showImage(im)
    saveImageFile(im,newname,"PNG")


@app.route('/')
def displayPuzzle():
    print("In displayPuzzle")
    
    if pic==1:
        editImage(1,"static/distortedImage1.png", "static/new1image1.png")
        editImage(2,"static/distortedImage1.png", "static/new1image2.png")
        editImage(3,"static/distortedImage1.png", "static/new1image3.png")
    elif pic==2:
        editImage(1,"static/distortedImage2.png", "static/new2image1.png")
        editImage(2,"static/distortedImage2.png", "static/new2image2.png")
        editImage(3,"static/distortedImage2.png", "static/new2image3.png")
    elif pic==3:
        editImage(1,"static/distortedImage3.png", "static/new3image1.png")
        editImage(2,"static/distortedImage3.png", "static/new3image2.png")
        editImage(3,"static/distortedImage3.png", "static/new3image3.png")

    html=''
    html+='<!DOCTYPE html>\n'
    html+='<html>\n'
    html+='<body>\n'

    html+=" <h1>Image Puzzle</h1>\n"
    html+='<p1> Apply one of the operations below to the image, and see if you can guess what famous object is in the image! </p1>\n'                     
    if pic==1:
        html+='<img src="/static/distortedImage1.png" alt="distortedImage1"style="width:1024px;height:683px" >\n'
    elif pic==2:
        html+='<img src="/static/distortedImage2.png" alt="distortedImage2"style="width:1070px;height:731px" >\n'
    elif pic==3:
        html+='<img src="/static/distortedImage3.png" alt="distortedImage3"style="width:454px;height:303px" >\n'


    html+='<br>\n'
    
    html+='Pick an Operation:<br>\n'
    
    
    html+='<form method="POST" action="/showimage">\n'
   
    html+='<input type="radio" name="operation" value="red">Set blue and green pixels to 0 and multiple red ones by 20<br>\n'
    html+='<input type="radio" name="operation" value="green">Set blue and red pixels to 0 and multiple green ones by 20<br>\n'
    html+='<input type="radio" name="operation" value="blue">Set blue and green pixels to 0 and multiple red ones by 20<br>\n'
    
    html+='<input type="submit" value="Apply Operations" />\n'
    html+='</form>\n'

    html+='</form>\n'
    html+='</body>\n'
    html+='</html>\n'
    return html

@app.route("/showimage", methods=['POST'])
def showEditedimage():
    html=''
    html+='<!DOCTYPE html>\n'
    html+='<html>\n'
    html+='<body>\n'
    operation=request.form["operation"]
    if operation=="red":
        if pic==1:
            html+='<img src="/static/new1image1.png" alt="newimage" style="width:1024px;height:683px" >\n'
        elif pic==2:
            html+='<img src="/static/new2image1.png" alt="newimage" style="width:1070px;height:731px" >\n'
        elif pic==3:
            html+='<img src="/static/new3image1.png" alt="newimage" style="width:454px;height:303px" >\n'
       
    elif operation=="green":
        if pic==1:
            html+='<img src="/static/new1image2.png" alt="newimage" style="width:1024px;height:683px" >\n'
        elif pic==2:
            html+='<img src="/static/new2image2.png" alt="newimage" style="width:1070px;height:731px" >\n'
        elif pic==3:
            html+='<img src="/static/new3image2.png" alt="newimage" style="width:454px;height:303px" >\n'

    elif operation=="blue":
       if pic==1:
           html+='<img src="/static/new1image3.png" alt="newimage" style="width:1024px;height:683px" >\n'
       elif pic==2:
           html+='<img src="/static/new2image3.png" alt="newimage" style="width:1070px;height:731px" >\n'
       elif pic==3:
           html+='<img src="/static/new3image3.png" alt="newimage" style="width:454px;height:303px" >\n'
        
    
    html+='<br>\n'
    html += '<form method="POST" action="/guessImage">\n'
    html += 'Enter your guess <input type="text" name="guess"/>\n'
    html += '</form>\n'
    html+='</form>\n'
    html+='</body>\n'
    html+='</html>\n'
    return html

@app.route("/guessImage", methods=['POST'])
def guessImage():
    guess=request.form["guess"]
    if pic==1:
        if guess=="White House" or guess=="white house" or guess=="the white house" or guess=="The White House" or guess=="the White House":
            return "Correct!"
        else:
            return redirect('/')
    elif pic==3:
        if guess=="Great Wall of China" or guess=="great wall of china" or guess=="the great wall of china" or guess=="The Great Wall of China" or guess=="the Great Wall of China":
            return "Correct!"
        else:
            return redirect('/')
    elif pic==2:
        if guess=="Rihanna" or guess=="rihanna":
            return "Correct!"
        else:
            return redirect('/')
        
if __name__ == '__main__':    
    app.run()


#/home/jalague/mysite/static/distortedImage1.png
