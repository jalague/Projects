import flask
import PIL
from flask import request
from flask import redirect
from imageHelperFunctions import *            #here I import all the necessairy modules, files, and fucntions 
import random
import os, os.path

app=flask.Flask(__name__)

pic=random.randint(1,3)    #this is used to determine which distorted picture will be used later

def editImage(option,filename,newname):    #this fucntion will be used in the dispalyPuzzle fucntion to change the color of the image to make it visible
    im=openImageFile(filename)
    w,h=size(im)
    
    for i in range(0,h):
        
        for j in range(0,w):    #here I take the width and height of the image and iterate over them to go to each pixel of the image
            
            r,g,b= getPixel((j,i),im)    #this assigns the current red green and blue color values to 'r,g,b' variables
            
            
            if option==1:
                setPixel((j,i),im, (r*20,0,0))    #these if statements take the option, chosen by the user, and changes the color of each pixel accordingly.
            elif option==2:
                setPixel((j,i),im, (0,g*20,0))
            elif option==3:
                setPixel((j,i),im, (0,0,b*20))
    #showImage(im)  #this was used to test the image
    saveImageFile(im,newname,"PNG")    #this saves the new iamge as a new file


@app.route('/')
def displayPuzzle():
    print("In displayPuzzle")     #this function will generate the first page to be displayed
    
    if pic==1:             #the if statements are used to determine which distorted image will be displayed and used, the numbers 1,2,3 reference each image
        editImage(1,"static/distortedImage1.png", "static/new1image1.png")     #these create and save each possible new image, based on the different color options, for each of the different distorted iamges
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
    html+='<!DOCTYPE html>\n'          #this section creates the html code necessairy to display the distorted image and radio buttons
    html+='<html>\n'
    html+='<body>\n'

    html+=" <h1>Image Puzzle</h1>\n"
    html+='<p1> Apply one of the operations below to the image, and see if you can guess what famous object is in the image! </p1>\n'                     
    if pic==1:
        html+='<img src="/static/distortedImage1.png" alt="distortedImage1"style="width:1024px;height:683px" >\n'    #depending on which integer was randomly generated at the begining this will display one of the distorted images
    elif pic==2:
        html+='<img src="/static/distortedImage2.png" alt="distortedImage2"style="width:1070px;height:731px" >\n'
    elif pic==3:
        html+='<img src="/static/distortedImage3.png" alt="distortedImage3"style="width:454px;height:303px" >\n'


    html+='<br>\n'
    
    html+='Pick an Operation:<br>\n'
    
    
    html+='<form method="POST" action="/showimage">\n'
   
    html+='<input type="radio" name="operation" value="red">Set blue and green pixels to 0 and multiple red ones by 20<br>\n'
    html+='<input type="radio" name="operation" value="green">Set blue and red pixels to 0 and multiple green ones by 20<br>\n'    #these are all the options the user can pick for changing the colors
    html+='<input type="radio" name="operation" value="blue">Set blue and green pixels to 0 and multiple red ones by 20<br>\n'
    
    html+='<input type="submit" value="Apply Operations" />\n'   #this creates the button to 'submit' the users choose
    html+='</form>\n'

    html+='</form>\n'
    html+='</body>\n'   #this completes the html code 
    html+='</html>\n'
    return html

@app.route("/showimage", methods=['POST'])
def showEditedimage():                       #this function will dispay the new image and ask the user to guess the image
    html=''
    html+='<!DOCTYPE html>\n'    #this generates the html code necessairy to 
    html+='<html>\n'
    html+='<body>\n'
    operation=request.form["operation"]   #this takes the users choice and assigns it to the variable 'oepration'
    if operation=="red":
        if pic==1:     #these nested if statements are used to determine which new image will be displayed, based on the option the user picked and which distorted image was originally 
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
    html += 'Enter your guess <input type="text" name="guess"/>\n'     #here is the html code for entering the users code guess 
    html += '</form>\n'
    html+='</form>\n'
    html+='</body>\n'
    html+='</html>\n'
    return html

@app.route("/guessImage", methods=['POST'])      #once the user submits his guess they will be directed to this function
def guessImage():       #this function will evaluate the users guess
    guess=request.form["guess"]
    if pic==1:   #for the first picture the correct answer is the White House
        if guess=="White House" or guess=="white house" or guess=="the white house" or guess=="The White House" or guess=="the White House":   #the or statements try to take in account all possible correct answers
            return "Correct!"
        else:
            return redirect('/')    #if the user guesses incorrectly they will be sent back to the main displayPuzzle page
    elif pic==3:      #the correct answer for the third picture is the Great wall of china, if the user guesses incorrectly they will be sent back to the main display page
        if guess=="Great Wall of China" or guess=="great wall of china" or guess=="the great wall of china" or guess=="The Great Wall of China" or guess=="the Great Wall of China":
            return "Correct!"
        else:
            return redirect('/')
    elif pic==2:   #the correct answer for the third picture is Rihanna
        if guess=="Rihanna" or guess=="rihanna":
            return "Correct!"
        else:
            return redirect('/')
        
if __name__ == '__main__':    
    app.run()    #this will run the flask application


	
