# -*- coding: utf-8 -*-
"""
Created on Fri May 13 14:58:07 2016

@author: John
"""
import numpy as np
import time
import turtle
import random

"""
This method creates a dictionary with the keys as nodes (labeled 0 to n-1) and values as a list of neighboring nodes (a list of indecies)
Each node is connected to the same number of nodes randomly, by creating an array with all the nodes repeated as many times 
as the given degree and then the list is randomly permutated.
The method then loops through the random list and adds each adjacent nodes to their coressponding neighbor lists in the 
dictionary
"""
def configurationModel(nodes, degree):     #Does not handle self edges or multi edges
    deg=[]
    A={}
    for l in range(0, nodes):    #These two loops make an empty adjacency matrix (list of lists)
        A[l]=[]

    for m in range(0,nodes):   #This makes a 1-D list that has each node in A as an element, repeated the amount of degrees there are
        for x in range(0,degree):      #Example   [0,0,1,1,2,2,3,3,4,4] this would be a network with 5 nodes and each node has degree 2
            deg.append(m)
    degrees=np.array(deg)     #puts the list into array format so we can perform a random permutation on it
    randomDegrees = np.random.permutation(degrees)   #randomizes the list of nodes so that we can create a random graph
    #print(randomDegrees)
    n=0    
    while (n<randomDegrees.size):     # looks at the random list and connects each neighboring element, by giving them a 1, in the adjacency matrix A
        i= randomDegrees[n]
        j= randomDegrees[n+1]
        if(i!=j):        
            A[i].append(j)
            A[j].append(i)
        n+=2
    return A
"""
This method generates a dictionary that represents a network of nodes with size n randomly connected with probability p
"""  
def ERGraph(n,p):
        
    net={}
    degreeOfEachNode=[]       # this keeps track of the degree of each node (the number of the node corresponds to the index in the list of how many nodes it has)
    numberOfNodesWithDegree=[]    #keeps track of the total number od nodes with a certain degree, to be used later
    for z in range(0,n):
        net[z]=[]
        degreeOfEachNode.append(0)
        numberOfNodesWithDegree.append(0)
    for x in range(0,n):    
        for i in range(x+1,n):
            r=np.random.random_sample()
            if (r<=p):
                degreeOfEachNode[x]+=1
                degreeOfEachNode[i]+=1
                net[x].append(i)
                net[i].append(x)
        numberOfNodesWithDegree[degreeOfEachNode[x]]+=1
    return net   
    
"""
Method to simulate a single random walk or 'spread' of a disease over a given network (adjacency dictionary), given integer 
of the number of steps in the random walk or disease cycle, a pInfect or a decimal representing the the probability of 
infecting a neighboring node, and also given a decimal for the initial portion of the network population that will start off
as infected.
 """
def diseaseSpread(network, steps, pInfect, initialFraction):
    infectionList=[]      # is a list of lists that keep track of the current status of a node and the updated status of the node
    infectedEachStep=[]   #keeps track of the number of nodes infected after each step in the walk
    initialInfectedNumber=0
    
    for x in range(0, len(network)):     #this loop infects the initial portion of the populationt that will start the walk already infected
        r=np.random.random_sample()
        status=False
        if(r<=initialFraction):    # though it's only a probability of getting infected instead of a set fraction of the initial population they are very close given a network of more than 100 nodes
            status=True
            initialInfectedNumber+=1    #keeps track of the number of nodes initially infected
        infectionList.append([status, None])
    for n in range(0, steps):        # spreads the disease for the given number of steps
        numInfected=0
        for i in range(0, len(network)):
            for j in range(0, len(network[i])):           #For each node in the netowork and each of its neighbors the method checks to see if that neighbor is infected and then with probability pInfect it will infect the current node
                r=np.random.random_sample()
                if(infectionList[network[i][j]][0]):   #if neighbor is infected
                    if(r<=pInfect): 
                        infectionList[i][1]=True
            if(infectionList[i][1]):
                numInfected+=1
        infectedEachStep.append(numInfected)
        for r in range(0, len(infectionList)):   #resets the current status of a node to the updated value for the next round/step
            infectionList[r][0]=infectionList[r][1]
            infectionList[r][1]= False
    return infectedEachStep, initialInfectedNumber
    
"""
Method that will run the disease spread a given amount of times (trials) on a single/constant network and then averages the results from every trial
Is given a network as a adjacency dictionary, the number of steps in each single trial, the initial fraction of the population
that will be infected for each trial, and the number of trials or simulations of the disease that are going to averaged  
"""    
def averageSpread(network, numSteps, intialProb, probInfect, trials):
    total=[]  
    final=[]
    initials=0
    inf=[]
    for y in range(0,numSteps):  #initializes empty lists
        final.append(0)
        inf.append(0)
    for d in range(0,trials):
        steps, initial=diseaseSpread(network,numSteps,probInfect,intialProb)
        initials+=initial
        total.append(steps)
    for p in range(0, numSteps):
                
        for c in range(0,trials):
            
            final[p]=final[p]+total[c][p]   #sums up the number of nodes effected for each step, final is a list(with size numSteps) that is the sum of the results from all the trials
            
    average=[]
   
    for a in range(0,numSteps):
        average.append(final[a]/trials)    #averages the number of infected nodes for each step
    
    return str(float(initials)/trials), average    
    
"""
Helper method used in a main function to output data to a file
"""
def outputToFile(list1, file,intial):
    f= open(file, 'a')
    f.write(intial+" ")
    for i in range(0,len(list1)):
        f.write(str(list1[i])+" ")
    f.write("\n")  
    
"""-------------------------------------------------------------------------------------------------------------------------------

These methods are used to calculate the probabilities of the disease dying out    

----------------------------------------------------------------------------------------------------------------------------------- """


"""Method that takes a probability of infecting(y) and degree
and is the generating function for a fixed degree network
"""
def GenConf(y, degree):
    return y**6
"""
Method uses GenConf() to calculate the the probability of the disease
dying out on a configuration model netowrk after a given
number of steps (num).
returns a list of probaiblities 
"""
def eradicationConf(num, probOfInfect):
    l ={}
    l[0]=GenConf(1-probOfInfect)
    for i in range (1, num):
        l[i]=GenConf(probOfInfect*l[i-1]+(1-probOfInfect))     
    '''
    print()
    for x in range(0,num):
        print(l[x])
        #print(round(l[x], 4))
    '''
    return l  

""" Helper method to find probability of a node having degree k
in an ER network"""
def pK(k, numNodesWithDegreeK, totalNodes):
    numNodes=numNodesWithDegreeK[k]    
       
    p=float(numNodes)/totalNodes
    #print(p)
    return p;

""" Helper method to find probability of a neighboring node having degree k
in an ER network"""    
def qK(k, numNodesWithDegK, totalNodes, c):
    
    p= pK(k,numNodesWithDegK, totalNodes)
    q= float(p*k)*float(1/float(c))
    return q    
"""H_ER and GenER are generating fucntions used in calculating the probability of the disease
dying out with given probability of infecting y, max degree in the network, and other information from the network
"""
def GenER(y, maxDeg, numNodesWithDegK, totalNodes):
    sum1=0    
    for k in range(0, maxDeg):
        p=pK(k, numNodesWithDegK, totalNodes)
        sum1+=p*y**k
    return sum1

def H_ER(lam, totalNodes, numNodesWithDegK, c, maxDeg):
    sum2=0
    for k in range(0, maxDeg):
        q= qK(k, numNodesWithDegK, totalNodes, c)
        
        sum2+= q*lam**k
        
    
    return sum2
"""
Similar to the eradicationConf method this method calculates the probability of a disease dying out
given the number of steps and the probability of a node infecting a neighbor.
Since our equation uses the previous step's prob of dying out to caluclate the current probaiblity, the method keeps track of all the probabilities, for each step
up until the desired final step in a list. Then the method returns that list.
"""
def eradicationER(num, lam, totalNodes, c, numNodesWithDegK, maxDeg):
    G ={}
    H={}
    G[0]=GenER(1-lam, maxDeg, numNodesWithDegK, totalNodes)
    H[0]= H_ER(1-lam, totalNodes, numNodesWithDegK, c, maxDeg)
    for i in range (1, num):
        H[i]= H_ER(lam*H[i-1]+(1-lam), totalNodes, numNodesWithDegK, c, maxDeg)        
        G[i]=GenER(lam*(H[i])+(1-lam), maxDeg, numNodesWithDegK, totalNodes)     
    '''
    print("")
    for x in range(0,num):
        print(G[x])
    '''
    return G
    
    
"""------------------------------------------------------------------------------------------------------------------------------------

The below code uses Turtle Graphics to do three things:
    Create a visual representation of given network(in dictionary form), where each node is connected by a line to it's neighbor and represented by a circle that's size is determined it's the degree and is randomly drawn anywhere on the canvas
    Simulates the spread of single spread of a disease on a given network by showing each step of the disease walk. Where infected nodes are red and suseptible nodes are green
    Simulates the average spread of a disease on a constant network. So it will draw the network first and then runs the averageSpread method on the network and then draws the infection pattern based on the list of the average number of nodes infected at each step (So its really only drawing infected nodes randomly with a probability corresponding to the average number of nodes infected at that step, as opposed to drawing each specific node that's infected because its averages)


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"""


def makeCords(net):
    net1= []    
    for i in net:
        x=random.randint(-600,600)
        y=random.randint(-300,300)
        
        net1.append([x,y])
        
    return net1
    
def convertXY(orgX, orgY, width):
    x=float(orgX)*width
    y=float(orgY)*width
    return x,y

def drawStar(x, y, t, brightness,col):
    t.up()
    t.goto(x,y)                                               #the turtle will go to the cordinates given by the lines
    t.down()
    t.color(col)                                    #the circle representating each star will be filled yellow
    t.begin_fill()
    t.circle(7)                                  #each circle has a radius of one
    t.end_fill()
    
def drawLine(x1, y1, x2,y2,t, col):
    t.up()
    t.speed(7)
  #  X1,Y1=convertXY(x1,y1, 275)
    t.goto(x1,y1)
    t.down()
    t.color(col)
  #  X2,Y2=convertXY(x2,y2, 275)
    t.goto(x2,y2)

def drawNetwork(net, t, scr, net1):
    scr.tracer(0)
    scr.update()
    scr.tracer(None, None)
    
    for x in range(0,len(net1)):
        thisCord=net1[x]
        colors = ["gold", "light green", "white", "purple"]
        color =random.choice(colors)
        for z in net[x]:
            otherCord=net1[z]
            drawLine(thisCord[0],thisCord[1],otherCord[0],otherCord[1],t, color)
    
    for i in range(0,len(net1)):
       # print(net1[i])
        drawStar(net1[i][0],net1[i][1],t,len(net[i]), "green")
        
def drawAvg(net1, network, averages, scr):
    infected=[]
    for p in range(0, len(net1)):
        infected.append(0)
    scr.tracer(1750,1000)    
    for i in range(0, len(averages)):
        prob = averages[i]/len(network)
        for j in range(0, len(net1)):
            r=np.random.random_sample()
            x=0
            if(r<=prob):
                x=1
            infected[j]=x
        for w in range(0, len(net1)):
            t1= turtle.Turtle()
            l=net1[w]              
            if(infected[w]==1):
                              
                drawStar(l[0],l[1], t1, len(network[i]),"red")
            else:
                drawStar(l[0],l[1], t1, len(network[i]),"green")
            t1.ht()
            infected[w]=0
        scr.update()
        

# Main method to run a single disease spread
'''
def main():
     
    type1 = input("Enter 'ER' or 'Conf' for type of graph: ")
    nodes= int(input("Enter Number of Nodes: "))
    if(type1=="Conf"):
            print("Configuration Model")
            degree= int(input("Enter Number of Degrees: "))
                
            print("Configuring....")
            network= configurationModel(nodes,degree)
            
    elif(type1=="ER"):
            print("ER")
            degree= int(input("Enter Average Number of Degrees: "))  
           
            prob2= float(degree)/nodes
            print("Making Graph....")
            network=ERGraph(nodes,prob2)
    steps= int(input("Enter the number of steps or iterations of the disease: "))    
    intialPercent= float(input("Enter intial percent infected: "))
    prob= float(input("Enter probability of getting infected: "))
       
    print("Processing (Should be less than a minute)....")
    start = time.clock()
    scr=turtle.Screen()
    scr.screensize(-1500,1500)
    bob=turtle.Turtle()               #here I set up a turtle and a canvas
    #scr.bgcolor("")
    bob.color("white")
    bob.speed(10)
    scr.tracer(150,15)
    scr.bgcolor("blue")
    net=network            #ERGraph(20, .45)
    net1=makeCords(net)
    drawNetwork(net,bob, scr,net1)
    bob.ht()                              #this hides the turtle cursor at the end
    scr.update()    
    diseaseSpread(network, steps, intialPercent, prob, net1, scr)
    turtle.done()  
    end = time.clock()
    print('Time to complete: ')    
    print(float(end-start)/60) 
    print("min")
'''

#Main method to visualize the averageSpread

'''
def main():
     
    type1 = input("Enter 'ER' or 'Conf' for type of graph: ")
    nodes= int(input("Enter Number of Nodes: "))
    
    if(type1=="Conf"):
            print("Configuration Model")
            degree= int(input("Enter Number of Degrees: "))
                
            print("Configuring....")
            network= configurationModel(nodes,degree)
            
    elif(type1=="ER"):
            print("ER")
            degree= int(input("Enter Average Number of Degrees: "))  
           
            prob2= float(degree)/nodes
            print("Making Graph....")
            network=ERGraph(nodes,prob2)
    steps= int(input("Enter the number of steps or iterations of the disease: "))    
    
       
       
    intialPercent= float(input("Enter intial percent infected: "))
    prob= float(input("Enter probability of getting infected: "))
    trials =int(input("Enter number of trials: "))
    print("Processing (Should be less than a minute)....")
    start = time.clock()
    
    initialN,average=averageSpread(network, steps, intialPercent, prob,trials) 
    print(average)
    scr=turtle.Screen()
    scr.screensize(-1500,1500)
    bob=turtle.Turtle()               #here I set up a turtle and a canvas
    #scr.bgcolor("")
    bob.color("white")
    bob.speed(10)
    scr.tracer(1500,100)
    scr.bgcolor("blue")
    net=network            #ERGraph(20, .45)
    net1=makeCords(net)
    drawNetwork(net,bob, scr,net1)
    bob.ht()                              #this hides the turtle cursor at the end
    
    scr.update()
    
    
  #  diseaseSpread(network, steps, intialPercent, prob, net1, scr)
    drawAvg(net1,network, average ,scr)

    turtle.done()  
    #turtle.bye()
   
    end = time.clock()
    print('Time to complete: ')    
    print(float(end-start)/60) 
    print("min")
'''
