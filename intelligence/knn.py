##knn-model


##read from file of dataplans
##compute file stores the dataplans
import csv 
import math
import random
import operator
import numpy as np

def planNames(i) :
    dict = {
        1 : "Extravagant Plan:31: Local+Std:50p/min", 
        2 : "Specially For You:86: Local + Std:30p/min", 
        3 : "Exclusive For You:19: All STD calls:1p/s", 
        4 : "Airtel Misses You:48: All Std calls:35p/min", 
        5 : "Infinity Plan:56: All Std calls:35p/min", 
        6 : "Step-Up Plan:69: All Std calls:25p/min", 
        7 : "SnackDown Plan:28: Local + Std calls:1.2p/s", 
        8 : "Best Deal:46: All Local calls:35p/min", 
        9 : "Brought for You:66: Local + Std calls:35p/min",
        10 : "Night Offer:90: All night calls free:",
        11 : "Busy Day Offer:55: All day calls:30p/min", 
        12 : "Early Birds:44: All morning calls free:", 
        13 : "Regular Plan:33: All evening calls:35p/min", 

    }
    return dict[i]
def mainfunc(filename):
    with open("datapacks.csv", 'rb') as csvfile:
    	lines = csv.reader(csvfile)
    	dataset =list(lines)
    	print "size of dataset",len(dataset)

    with open("datapacks.csv", 'rb') as csvfile:	
        for line in csvfile.readlines():
        	array=line.split(',')

        #no of columns
        # print(dataset)
        num_cols=len(array)
        for y in range(len(dataset)-1) :
    		for x in range(num_cols-1):
    			dataset[y][x] = float(dataset[y][x])

        # The names of all the columns in the data.
        m=len(dataset)
        n=len(dataset[0])
        x= np.array(dataset)
        y=x[:,n-1:] # y contains the labels for the training set
        print y.shape
        x=x[:,:n-1]
        print(x.shape)

        from sklearn.neighbors import NearestNeighbors
        neigh2 = NearestNeighbors(n_neighbors = 2)
        neigh2.fit(x,y)
        #KNeighborsClassifier(...)
    with open(filename, 'rb') as csvfile:
        lines = csv.reader(csvfile)
        dataset =list(lines)
        print "size of dataset",len(dataset)
    print(dataset)
    result=[]
    with open(filename, 'rb') as csvfile:    
        for line in csvfile.readlines():
            array=line.split(',')

        #no of columns  
        num_cols=len(array)
        print("@@@@@@")
        print(array)
        for y in range(len(dataset)) :
            for x in range(num_cols):
                dataset[y][x] = float(dataset[y][x])    
        m=-1
        pos=0
        for x in range(len(dataset)) :
            if(m<dataset[x][3]):
               m=dataset[x][3]
               pos=x
        
        #print type(dataset[pos][0])
        print("$$$$$$$")
        print(pos)
        numbs=int(dataset[pos][0])
        st="Special Friend Offer:Free Calls to : "+str(numbs)+":0p/min"
        result.append(st)

        val=np.sum(dataset,axis=0)
        #print type(val)
        v=np.array(val)
        x=v[5:]
        summ=np.sum(x)
        userarr=x/summ
        
        userarr=np.append(userarr,0)
        userarr=np.append(userarr,0)
        print("##########")
        print("Neigh2")
        print(neigh2.kneighbors(userarr.reshape(1,-1), return_distance = False))
        v1 = neigh2.kneighbors(userarr.reshape(1,-1), return_distance = False)[0][0]
        print(v1)
        v2 = neigh2.kneighbors(userarr.reshape(1,-1), return_distance = False)[0][1]
        print(v2)

        result.append(planNames(v1))
        result.append(planNames(v2))

        print("user array")
        print userarr
        dummy=userarr
        dummy[0]=0
        dummy[1]=0
        dummy[2]=0
        dummy[3]=0
        dummy[4]=v[3]
        dummy[5]=v[4]
        
        
        #userarr=(userarr).astype(int)
        
        print dummy
        
        
        print(neigh2.kneighbors(dummy.reshape(1,-1), return_distance = False))
        v3 = neigh2.kneighbors(dummy.reshape(1,-1), return_distance = False)[0][0]
        print(v3)
        v4 = neigh2.kneighbors(dummy.reshape(1,-1), return_distance = False)[0][1]
        print(v4)
        result.append(planNames(v3))
        result.append(planNames(v4))
        print result
        return result
        
# mainfunc("calldata.csv")

