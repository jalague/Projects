# Name: John LaGue
# Lab 2: R, Intro to Statistical Learning
# 
# 
library(MASS)
library(ggplot2)
# Exercise 2:

# a) This would be a regression problem interested in inference. n=500, p= 3. The salary is the continuous target variable and the predictors are profit num of employees, and industry.
# b) This would be a classification problem interested in prediction. n=20, p=14. The prediction or target variable would be whether or not the product will be a success and then there are 14 predictor variables.
# c) This would be a regression problem interested in prediction. n= 52, p= 3. Target variable is %change in US dollar, and there are three predictors.

# Exercise 9:------------------------------------------------------------------------------------
auto<-read.csv("C:/Users/John/Documents/5 Semester/Data Mining/Homework/Auto.csv")
View(auto)
cleaned<-auto[!is.na(auto)]

#   a) The quantitative variables are mpg, weight, accl, displacement horsepower, year. The qualitative variables are cyclinders and origin and name.
range(auto$mpg)
range(auto$weight)
range(auto$acceleration)
range(auto$displacement)

horse<-levels(auto$horsepower)

horse<- ifelse(horse== "?", NA, horse)
horse
as.numeric(horse)
range(as.numeric(auto$horsepower))
range(auto$year)
#   b) mpg[9:46.6], weight[1613:5140], accl[8:24.8], displacement[68:455], horsepower[46:230], year[70:82]
summary(auto)
sd(auto$mpg); sd(auto$weight); sd(auto$acceleration); sd(auto$displacement); sd(auto$year)
#   c) mpg: mean=23.52 sd= 7.83, weight: mean=2970 sd=847.9, accl: mean=15.56 sd=2.75, disp: mean=193.5 sd=104.38 , year: mean=75.99 sd=3.69
auto2<- auto[-(10:85),]
summary(auto2)
sd(auto2$mpg); sd(auto2$weight); sd(auto2$acceleration); sd(auto2$displacement); sd(auto2$year)
#   d) mpg: mean=24.44 sd=7.9 , weight: mean=2934 sd=810.6, accl: mean=15.72 sd=2.68, disp: mean=187.5 sd=99.6 , year: mean=77.15 sd=3.11

plot(auto$mpg, auto$weight)
plot(auto$acceleration, auto$weight)
plot(auto$year, auto$mpg)
plot(auto$displacement, auto$mpg)
plot(auto$acceleration, auto$horsepower)
#   e) It looks like there is a clear relationship between weight and mpg, a relationship between acceleration and weight, possible relationship between year made and mpg, and displacement and mpg.

#   f) as e) shows it looks like weight, displacement, and year made could all be used to predict mpg.
# Exercise 10:----------------------------------------------------------------------------


?Boston
View(Boston)
#a) 506rows and 14 columns.Each row is a home and the columns are variables associated to that home.
pairs(Boston)

#b)From the pairwise scatterplots there seem to be many possible relationships in the variables.


#c)age, dist, and medv value all look like there may be a relationship.As age increases crime increases, as dist increases crim decreases, and as medv increases crime decreases.
summary(Boston)
#d) yes, one neighborhood has a crim rate of 88.9 while the mean is 3.6. Some also seem to have much higher taxes, almsot double. Pupil ratio also does seem have a wide range from 12 to 22.

count<-Boston$chas[Boston$chas==1]
length(count)
#e) 35
#f)19.05
sub<- Boston[Boston$medv==5,]
summary(sub)
#g) Crime range: 38-67, much higher than average, age: all at 100, tax: 666, pupil ratio: 20.2, back is 385-396. The predictors seem to all show a low income neighborhood
room7<- Boston$rm[Boston$rm>7]
length(room7)
room8<- Boston$rm[Boston$rm>8]
length(room8)
#h) 64 homes average more than 7 rooms and 13 average more than 8
