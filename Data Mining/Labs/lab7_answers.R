# Name: John LaGue
# Lab 6: SVM
# 
# Exercise 1:
x1 = -10:10
x2 = 1 + 3 * x1
plot(x1, x2, type = "l", col = "red")
text(c(0), c(-20), "greater than 0", col = "red")
text(c(0), c(20), "less than 0", col = "red")
lines(x1, 1 - x1/2)
text(c(0), c(-15), "less than 0")
text(c(0), c(15), "greater than 0")

# Exercise 3:
# a)
x1 = c(3, 2, 4, 1, 2, 4, 4)
x2 = c(4, 2, 4, 4, 1, 3, 1)
colors = c("red", "red", "red", "red", "blue", "blue", "blue")
plot(x1, x2, col = colors, xlim = c(0, 5), ylim = c(0, 5))

#  b)
abline(-0.5, 1)

#   c)
#Classify to Red if .5−X1+X2>0 and Blue otherwise


#   d)
abline(-1, 1, lty = 2)
abline(0, 1, lty = 2)

#   e)

arrows(2, 1, 2, 1.5)
arrows(2, 2, 2, 1.5)
arrows(4, 4, 4, 3.5)
arrows(4, 3, 4, 3.5)

#   f)
#A slight movement of observation (4,1) blue would not have 
#an effect on the maximal margin hyperplane since its movement would be outside of the margin.

#   g)

abline(-0.8, 1)
#  −0.8−X1+X2>0

#   h)
points(c(3), c(1.5), col = c("red"))
#
# Exercise 8:
library(ISLR)

set.seed(1)
train = sample(dim(OJ)[1], 800)
OJ.train = OJ[train, ]
OJ.test = OJ[-train, ]

#   b)
library(e1071)
?svm
svm.model<-svm(Purchase~., kernel="linear", data=OJ.train, cost=.01)
summary(svm.model)
# gamma is .0555 and there are 432 support vectors

#    c)
train.pred = predict(svm.model, OJ.train)
table(OJ.train$Purchase, train.pred)
(78+55)/(489+228+55+78)
# training error is: .156
test.pred = predict(svm.model, OJ.test)
table(OJ.test$Purchase, test.pred)
(18+31)/(18+31+141+80)
# training error is: .1814

#   d)

set.seed(1)
tune.out = tune(svm, Purchase ~ ., data = OJ.train, kernel = "linear", ranges = list(cost = 10^seq(-2, 1, by = 0.25)))
summary(tune.out)
# cost= .01777

#   e)

svm.model.C<-svm(Purchase~., kernel="linear", data=OJ.train, cost=.01777)
train.pred.C = predict(svm.model.C, OJ.train)
table(OJ.train$Purchase, train.pred.C)
(72+56)/(438+234+56+72)
# training error is .16
test.pred.C = predict(svm.model.C, OJ.test)
table(OJ.test$Purchase, test.pred.C)
(31+19)/(31+19+140+80)
#  test error was .1815

#   f)
svm.radial = svm(Purchase ~ ., data = OJ.train, kernel = "radial")
summary(svm.radial)
#cost= 1, gamma = .0555, and there are 379 support vectors
train.pred = predict(svm.radial, OJ.train)
table(OJ.train$Purchase, train.pred)
(38+77)/(39+77+455+229)
# training error for radial is .14375
test.pred = predict(svm.radial, OJ.test)
table(OJ.test$Purchase, test.pred)
(28+18)/(18+28+141+83)
#  test error rate for radial is .1703

tune.out = tune(svm, Purchase ~ ., data = OJ.train, kernel = "radial", ranges = list(cost = 10^seq(-2, 1, by = 0.25)))
summary(tune.out)
# best cost= .316227
svm.radial = svm(Purchase ~ ., data = OJ.train, kernel = "radial", cost=.3162277)
train.pred = predict(svm.radial, OJ.train)
table(OJ.train$Purchase, train.pred)
(46+78)/(78+46+448+228)
#train error rate with new C is :  .155
test.pred = predict(svm.radial, OJ.test)
table(OJ.test$Purchase, test.pred)
(29+15)/(144+82+29+15)
# test error rate with new c is .162963

#    g)  

svm.poly = svm(Purchase ~ ., data = OJ.train, kernel = "polynomial", degree=2)
summary(svm.poly)
#cost= 1, gamma = .0555, and there are 454 support vectors
train.pred = predict(svm.poly, OJ.train)
table(OJ.train$Purchase, train.pred)
(33+105)/(33+105+461+201)
# training error for poly is .1725
test.pred = predict(svm.poly, OJ.test)
table(OJ.test$Purchase, test.pred)
(41+10)/(41+10+149+70)
#  test error rate for poly is .18888

tune.out = tune(svm, Purchase ~ ., data = OJ.train, kernel = "polynomial", degree=2,ranges = list(cost = 10^seq(-2, 1, by = 0.25)))
summary(tune.out)
# best cost= 5.6234
svm.poly2 = svm(Purchase ~ ., data = OJ.train, kernel = "polynomial", cost=5.62341325)
train.pred = predict(svm.poly2, OJ.train)
table(OJ.train$Purchase, train.pred)
(38+70)/(38+70+456+236)
#train error rate with new C is :  .135
test.pred = predict(svm.poly2, OJ.test)
table(OJ.test$Purchase, test.pred)
(36+18)/(36+18+141+75)
# test error rate with new c is .2

#   h)

# Overall the best approach appears to be the radial svm because it produces the lowest test error
x<-c(3,5,7,8)
y<-c(3,4,2,3)
plot(x,y)
abline(-.25, 4, col="blue", untf=FALSE)
abline(2,1)
?abline
