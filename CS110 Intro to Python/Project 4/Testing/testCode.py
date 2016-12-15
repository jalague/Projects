# Runs your program on profile1.txt and profile2.txt
# Compares output (output1.txt or output2.txt) with recommendedBooks.txt
import project4, shutil

def main():
       listOfInputFiles = ["profile1.txt", "profile2.txt"]
       listOfOutputFiles = ["output1.txt", "output2.txt"]
       
       for k in range(len(listOfInputFiles)):
              inputFile = listOfInputFiles[k]
              shutil.copyfile(inputFile, "profile.txt")
              project4.main()
              
              print()
              print("------- TEST------------")
              print("Running your code on  " + inputFile)
              print("The produced ouput recommendedBooks.txt should be identical to " + listOfOutputFiles[k])
              f1 = open("recommendedBooks.txt") 
              lines1 = f1.readlines()
              f2 = open(listOfOutputFiles[k]) 
              lines2 = f2.readlines()
              lines1 = sorted(lines1)
              lines2 = sorted(lines2)
              if len(lines1) != len(lines2):
                     print("Test failed! The number of lines in your recommendedBooks.txt \
                      file is different from the number of lines in the expected output file.")
                     return

              for i in range(len(lines1)):
                     lines1[i] = lines1[i].strip()
                     lines2[i] = lines2[i].strip()
                     if lines1[i] != lines2[i]:
                            print("Test FAILED on line " + str(i+1))
                            print("Expected the following book in " + listOfOutputFiles[k])
                            print(lines2[i])
                            print("But instead, saw the following line:")
                            print(lines1[i])
                            return
       print("-------- DONE ------------- ")
       print("Your code passed the tests. Make sure you test your code independently. Do NOT rely only on the test to tell you whether your code is working.")
        
main()
