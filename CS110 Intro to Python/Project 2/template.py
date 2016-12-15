from flask import Flask, request, redirect
from imageHelperFunctions import *
import os, os.path

app = Flask(__name__)

@app.route('/')
def displayPuzzle():
    print("In displayPuzzle")
    # create the html string that displays the heading, the image with noise,
    # the radio buttons for the three operations and the Enter button.
    # FILL IN CODE
    
    
    # check if images obtained by applying the three operations
    # on magicImage.png, exist. if not, create them by calling editImage
    # for each operation.
    # Use os.path.exists to check if the file exists:
    # if not os.path.exists('static/editedImage1.png'):
    # FILL IN CODE
    
    

@app.route('/showResult', methods = ['POST'])
def showEditedimage():
     print("showEditedImage")
  
     # Check which radio button is selected, and show html document with
     # the corresponding image
     # FILL IN CODE
          
     
@app.route('/guessImage', methods = ['POST'])
def guessImage():
    print("guessImage")
    ''' Shows a text field where the user can type their guess about
    what is displayed in the image. If the user guesses correctly, display
    "Correct!" , otherwise redirect to the main page: return redirect("/")
    '''
    # FILL IN CODE

    
          

def editImage(option, filename1, filename2):
    print("In editImage")
   '''filename1 is the input file 
      filename2 is the output file
      option is an integer, either 1, 2 or 3
      Iterate over all pixels and:
      If option is 1, then set the green and blue values to 0, and multiply red ones by 20
      If option is 2, then set the red and blue values to 0, and multiply green ones by 20
      If option is 3, then set the red and green values to 0, and multiply blue ones by 20
   '''
   # FILL IN CODE


if __name__ == '__main__':
    app.run()  # when you are debugging, try calling app.run(debug=True)
