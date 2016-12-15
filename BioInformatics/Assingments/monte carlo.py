# -*- coding: utf-8 -*-
"""
Created on Thu Nov 17 10:14:18 2016

@author: John
"""
import numpy as np
import math
import time as t

def readFile(file):
    all= open(file, 'r')
    atoms= all.readlines()
    size=atoms[0]
    a=[]
    
    for i in range(1, len(atoms)):
        atom= atoms[i].split()
        for x in range(0, len(atom)):
            atom[x]=float(atom[x])
        #print(atom)
        a.append(atom)      
    return size, a

def readFileVector(file):
    #all= open(file, 'r')
    #lines=np.loadtxt(file, skiprows=1)
    atoms=np.genfromtxt(file,skip_header=True, unpack=True)
  
    return atoms

def cube(atoms):
        cube=[0,0,0]
        maxX=0
        maxY=0
        maxZ=0
        maxR=0
        for a in atoms:
            if abs(a[0])>abs(maxX):
                maxX=abs(a[0])
            if abs(a[1])>abs(maxY):
                maxY=abs(a[1])
            if abs(a[2])>abs(maxZ):
                maxZ=abs(a[2])
            if abs(a[3])>abs(maxR):
                maxR=abs(a[3])
        #print(maxR)         prints the maximum radius found
        cube[0]=maxX+maxR
        cube[1]=maxY+maxR
        cube[2]=maxZ+maxR
        #print(cube)         prints the maximum x,y,z values found
        return cube
        
def cubeVector(atoms):
    radius=np.amax(abs(atoms[3]))
    cube=[np.amax(abs(atoms[0]))+radius,np.amax(abs(atoms[1]))+radius,np.amax(abs(atoms[2]))+radius]
    #print(cube)
    return cube

def distance(cord1, cord2):
    dist = math.sqrt((cord1[0]-cord2[0])**2 +(cord1[1]-cord2[1])**2+(cord1[2]-cord2[2])**2)
    return dist

def monte_Volume(atoms, cube, N):
    volume=0
    cubeDarts=0
    atomDarts=0    
    dartX,dartY,dartZ=0,0,0
    points=[]
    for i in range(0,N):
        dartX=np.random.uniform(-cube[0], cube[0])
        dartY=np.random.uniform(-cube[1],cube[1])
        dartZ=np.random.uniform(-cube[2],cube[2])
        point=[dartX, dartY,dartZ]        
        points.append(point)
        inAtom=False
        for atom in atoms:
            d = distance(atom, point)
            if (d<=atom[3]):
                inAtom=True
        if (inAtom):
            atomDarts+=1
        else:
            cubeDarts+=1
    print("number of atom darts: ", str(atomDarts), ", number of outside atom darts: ", str(cubeDarts))
    
    volume= (atomDarts/N)*((2*cube[0]*2*cube[1]*2*cube[2]))
    std=volume*math.sqrt(((cubeDarts/N)-(atomDarts/N))/N)    
    return volume,std
    
def monte_Volume_Fast(atoms,cube,N):
    cubeDarts=0
    atomDarts=0    
    dartX,dartY,dartZ=0,0,0
    for i in range(0,N):
        dartX=np.random.uniform(-cube[0], cube[0])
        dartY=np.random.uniform(-cube[1],cube[1])
        dartZ=np.random.uniform(-cube[2],cube[2])
       # point=[dartX, dartY,dartZ]        
        #points.append(point)
        distance=np.sqrt((atoms[0]-dartX)**2 +(atoms[1]-dartY)**2+(atoms[2]-dartZ)**2)
        inAtom=False     
        for i in range(0,len(distance)):            
            if distance[i]<=atoms[3][i]:
                inAtom=True

        if (inAtom):
            atomDarts+=1
        else:
            cubeDarts+=1
    print("Vectorized---------------------")
    print("number of atom darts: ", str(atomDarts), ", number of outside atom darts: ", str(cubeDarts))
    
    volume_est= (atomDarts/N)*((2*cube[0]*2*cube[1]*2*cube[2]))
    std=volume_est*np.sqrt(((cubeDarts/N)-(atomDarts/N))/N)    
    return volume_est, std

def monte_SA(atoms,N):
    outerS=0
    innerS=0
    
    totalPossibleSA=0
    for x in range(0, len(atoms[0])):
        r=atoms[3][x]+.0001
        totalPossibleSA+= (4*np.pi * r**2)
        for i in range(0, N):
            z=np.random.uniform(-r, r)
            dartPhi=np.random.uniform(0, 2*np.pi)
            theta= np.arcsin(z/r)
            x=r*np.cos(theta)*np.cos(dartPhi)+atoms[0][x]
            y= r*np.cos(theta)*np.sin(dartPhi)+atoms[1][x]
            z=z+atoms[2][x]
            distance=np.sqrt((atoms[0]-x)**2 +(atoms[1]-y)**2+(atoms[2]-z)**2)
            outer=True
            
            for i in range(0,len(distance)):            
                if distance[i]<atoms[3][i]:
                    outer=False
            if(outer):
                outerS+=1
            else:
                innerS+=1
    print("Total: ",totalPossibleSA)
    surface_est=(outerS/(N*len(atoms[0])) * totalPossibleSA)
    std= surface_est*np.sqrt(((innerS/len(atoms[0]))-(outerS/len(atoms[0])))/len(atoms[0]))
    return surface_est, std


def main_Volume():
    start=t.time()
    N=10000
    file='test1.txt'
    size, a = readFile(file)
    aV=readFileVector(file)
    c=cube(a)    
    cV=cubeVector(aV)
    print("Non Vectorized Volume")    
    volume_est,std= monte_Volume(a,c,N)
    print("Estimated volume: ", volume_est, "STD: ", std)
    end = t.time()-start
    print("Time to finish non vectorized method: ", end)

    start=t.time()
    volume_estV, stdV= monte_Volume_Fast(aV,cV,N)
    print("Estimated volume: ", volume_estV, "STD: ", stdV)
    end = t.time()-start
    print("Time to finish vectorized method: ", end)
    
def main_SurfaceArea():
    start=t.time()
    N=50
    file='test1.txt'
    atomsV=readFileVector(file)
    sa, std =monte_SA(atomsV, N)
    print("N: ", N)
    print("Estimated surface area of protein: ", sa)
    print("Standard deviation: ", std)
    print("Time to finish: ", (t.time()-start))
    print('\n','\n')
    
#main_Volume()
#main_SurfaceArea()
