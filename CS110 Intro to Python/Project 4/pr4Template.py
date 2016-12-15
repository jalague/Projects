def readBooks(bfilename):
       ''' Read book titles from the file bfilename,
       append each book title to the list books and return it'''
       # FILL IN CODE



def readUserProfile(filename) :
       ''' Reads a file with the user profile. The file contains
       the name of the user on the first line and ratings for all the books
       on the next line, separated by white space'''
       # FILL IN CODE


  
def readRatings(filename):
       ''' Reads the ratings data from the filename and returns a dictionary,
       where each username is the key, and the value is a list of ratings for all the books'''
       # FILL IN CODE


       
def recommendBooks(books, dictRatings, userRatings) :
       ''' Takes the list of books,  the dictionary of ratings,
       and the list of ratings for the user, and returns a list of
       recommended books'''
       # FILL IN CODE
                      
       

def writeBooksToFile(recomBooks, filename):
       ''' Write the list of recommended books to a file'''
       # FILL IN CODE



  
def main() :
       # First, read the books, save the return value to the list books
       # Then, read the user's profile file
       # Then, read the ratings
       # Call recommendBooks to get a list of recommended books
       # Write this list to file "recommendedBooks.txt"

       # FILL IN CODE
       
main()
