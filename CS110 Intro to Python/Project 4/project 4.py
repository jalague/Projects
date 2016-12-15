def readBooks(booksFilename):
    book=open(booksFilename,'r')
    lines=book.readlines()
    for x in range(0, len(lines)-1):
        lines[x]=lines[x].replace("\n", "")
    return lines


def readUserProfile(filename):
    profile=open(filename, 'r')
    lines=profile.readlines()
    return lines


def readRatings(filename):
    ratings=open(filename, 'r')
    lines=ratings.readlines()
    ratingdic={}
    for x in range(0, len(lines)):
        lines[x]=lines[x].replace("\n", "")
    for v in range(0, len(lines),2):
        ratingdic[lines[v]]=(lines[v+1].split(" "))     
    return ratingdic

def recommendBooks(books, dictRatings, userRatings):
    similaritylist=[]
    
    user=userRatings[1].split(" ")
    
    for x in dictRatings:
        similarity=0
        rate=dictRatings[x]
        
        for n in range(0, len(rate)-1):
                       
            
            similarity=similarity+int(user[n])*int(rate[n])
                
        
        similaritylist=similaritylist+[[similarity,x]]
    
     
    sortedsimilarity=sorted(similaritylist, reverse=True)    
  
    
    recommendations=""

    for m in range(0,4):
        recommender=sortedsimilarity[m][1]
        
        ratings=dictRatings[recommender]
        
        for rates in range(0, len(ratings)-2):
            
            if int(ratings[rates])==5 and int(user[rates])!=0 and books[rates] not in recommendations :
                    
                    recommendations= recommendations+books[rates]+", "
                            
    print(recommendations)
    return recommendations
               
        
def writeBooksToFile(recomBooks, filename):
    newrecommend=open(filename, 'w')
    lines=newrecommend.write(recomBooks)        

def main():
    books=readBooks("books.txt")  
    userRatings=readUserProfile("profile.txt")
    dicRatings=readRatings("ratings.txt")
    recomBooks=recommendBooks(books,dicRatings,userRatings)
    writeBooksToFile(recomBooks, "MyRecommendations")
    
main()
