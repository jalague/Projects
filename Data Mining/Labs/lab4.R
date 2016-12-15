#John LaGue
# Lab 4: Classification
# 
# 
# Exercise 1:
# Attached photo of Algebra

# Exercise 6:
-6+.05*40+1*3.5
# = -.5    
-6+.05*60+1*3.5
# the student would need to study for 60 hours to have 50% chance

# Exercise 10:
require(ISLR)
#a)    
  summary((Weekly))
  summary(Weekly$Year)
  plot(Weekly$Year, Weekly$Volume)
#b)
  model1<-glm(Direction~Lag1+Lag2+Lag3+Lag4+Lag5+Volume, data=Weekly, family=binomial)
  summary(model1)
  # only Lag2 looks to be signifigant in this model
#c)
  probs<- predict(model1,type="response" )
  pred=ifelse(probs>0.5,"Up","Down")
  table(pred, Weekly$Direction)
  #this tells us the logisitic regression seems to fail to classify positively as well as we could.
  ?"vector-class"
  
#d)
  train<-Weekly[(Weekly$Year!="2009" & Weekly$Year!="2010"),]
  data1
  model2<-glm(Direction~Lag2, data=train, family=binomial)
  summary(model2)
  
  test<- Weekly[(Weekly$Year=="2009" & Weekly$Year=="2010"),]
  test<-Weekly[Weekly$Year>2008,]
  test
 ?predict
   probs1<- predict(model1,test, type="response" )
  pred1=ifelse(probs1>0.5,"Up","Down")
  table(pred1, test$Direction)
  mean(pred1==Weekly$Direction)
  #.5142 ratio
  
#e)
  
  
#-------------------------------------------  
# Exercise 11:
mpg01<-ifelse(Auto$mpg>median(Auto$mpg), 1, 0)
mpg01

require(ggplot2)
Auto["mpg01"]<-mpg01
View(Auto)
#b) 
  plot(Auto$horsepower, mpg01)
  plot(Auto$weight, mpg01)
  plot(Auto$cylinders, mpg01)
  plot(Auto$acceleration, mpg01)
#c)

train<- Auto[1:300,]
test<-Auto[-(1:300),]  
#d)

#e)


#f)
  log.fitt<-glm(mpg01~weight, data=train, family="binomial")
  summary(log.fitt)
  
# Exercise 12:
#a)
Power=function(){
  
  print(2**3)
}

#b)
Power2=function(x,a){
  
  print(x**a)
}

#c)
Power2(10,3)
Power2(8,17)
Power2(131,3)

#d)

Power3=function(x,a){
  
  val<-x**a
  return(val)
}
#e)
x<-c(1:10)
plot(x, Power3(x,2))

#f)

PlotPower=function(x,a){
  
  plot(x, Power3(x,a))
  
}

PlotPower(c(1:10),3)
#--------------------------------------------------------------
# Exercise 13:

  
require(MASS)
?Boston
log.fit=glm(crim~age+tax+ptratio+medv+lstat, data=Boston)
summary(log.fit)
log.fit2=glm(crim~tax+lstat, data=Boston)
summary(log.fit2)

lda.fit<-lda(crim~lstat+tax, data=Boston)
summary(lda.fit)
lda.pred<-predict(lda.fit, Boston$crim)
table(lda.pred$class, Boston$crim)
knn.pred=knn()
