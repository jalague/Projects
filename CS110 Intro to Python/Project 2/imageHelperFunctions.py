from PIL import Image

      
def openImageFile(filename) :
       ''' Opens an image file and returns the image object'''
       return Image.open(filename)

def saveImageFile(image, filename, ext):
    ''' Saves a given image object to the file with a given file name \
    and a given extension such as "PNG" '''
    image.save(filename, ext)
       
def showImage(image):
    ''' Dispays a given image'''      
    image.show()
       
def size(image):
    ''' Returns the size of the given image as a  tuple: (width, height)'''
    return (image.size[0], image.size[1])

def getPixel(pos, image):
    '''Returns the color of the pixel at position pos.\n
       col is a tuple that has  three numbers  (r, g, b) which correspond \n
       to the red, green and blue colors of the pixel. \n
       pos should be a tuple that has two elements i and j. \n      
    '''
    return image.getpixel(pos)

def setPixel(pos, image, rgbCol):
    ''' Given a position in the image pos that is a tuple (x,y), \n
       set the color of the pixel at this position to rgbCol. \n
       rgbCol is a tuple of values from 0 to 255: (r, g, b) '''
       
    image.putpixel(pos, rgbCol)
