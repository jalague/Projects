# Name: John LaGue
# Lab 5: Trees
est<-c(.1,.15, .2, .2, .25, .55, .6, .6, .65, .7, .75)
#    Classification on majority vote method:
#      The classification could either be green or red since the majority vote leads to a tie between probabilities .2 and .6. Both of which result in different classifications.
#    Classification by average probability 
mean(est)
#.4318
#      Classification would be green because the average probability of it being red is only .4318 based on the estimates

# Exercise 8:
library(MASS)

# a)

# 506 x 14
length(Boston[,1])

train<-Boston[1:350,]
test<-Boston[-(1:350) ,]
test
#  b)

require(tree)
tree<- tree(medv~.,data=train)
summary(tree)
plot(tree)
text(tree, pretty=0)
?tree
#interpret
# c)

cv_tree<-cv.tree(tree)
plot(cv_tree)
plot(cv_tree$size,cv_tree$dev, type='b')
# Pruning is not necessairy as the most complex tree is the best accodring to the cross validation
library(randomForest)
#d)
set.seed(1)
rf.bag=randomForest(medv~.,data=train, mrty=13, importance=TRUE)
rf.bag
#MSE o6.52
f 
#e)

rf.boston=randomForest(medv~.,data=train, mrty=7, ntree=1000)
rf.boston
importance(rf.boston)
#as m increases the MSE decreases.

# Exercise 9:
#  a)
library(ISLR)
head(OJ)
trainOJ<- sample(1:nrow(OJ), 800)
testOJ<-OJ[trainOJ,]
trainOJ<-OJ[trainOJ,]
head(trainOJ)
head(testOJ)
#  b)
OjTree<- tree(Purchase~., data=trainOJ)
summary(OjTree)

#Variables actually used in tree construction:
#  [1] "LoyalCH"       "PriceDiff"     "SpecialCH"    
#[4] "ListPriceDiff"
#Number of terminal nodes:  8 
#Residual mean deviance:  0.7112 = 563.3 / 792 
#Misclassification error rate: 0.155 = 124 / 800 

#   c)
OjTree
# terminal node 20) the split value is .5 and there are 79 points in the subtree below this one. The deviance is 51.8. The star denotes that it is in fact a terminal node

#   d)
plot(OjTree)
text(OjTree,pretty = 0)
# LoyalCH appears to be the most important variable since it is at the top. And even the next split is also based on LoyalCH

#   e)
oj.pred = predict(OjTree, testOJ, type = "class")
table(testOJ$Purchase, oj.pred)
(399+277)/(399+277+41+33)
# test error rate is (399+277)/(399+277+41+33)  = 1- .901

#   f)
cv.oj<- cv.tree(OjTree)
cv.oj

#   g)
plot(cv.oj$size, cv.oj$dev, type='b')
#   h)
    #sizes 6, 7,8 all seem to have about the same error rate

#   i)
oj.pruned<- prune.tree(OjTree, best=5)

#   j)
summary(oj.pruned)
# pruned error rate =.16  and non pruned =  .099. the pruned version is higher

#   k)
pred.unpruned = predict(OjTree, testOJ, type = "class")
misclass.unpruned = sum(testOJ$Purchase != pred.unpruned)
misclass.unpruned/length(pred.unpruned)
#.155  non pruned
pred.pruned = predict(oj.pruned, testOJ, type = "class")
misclass.pruned = sum(testOJ$Purchase != pred.pruned)
misclass.pruned/length(pred.pruned)
#.16  prune     so th test error rate is better with the pruned tree