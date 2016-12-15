# Name: First Last
# Lab 8: Unsupervised Learning
# 
# Exercise 10:
#a)

x= matrix(rnorm(20*3*50, mean=0, sd=.001), ncol=50)
x[1:20, 2] =1
x[21:40, 1]=2
x[21:40,2] = 2
x[41:60, 1] =1

#   b)
pca.out<- prcomp(x)
summary(pca.out)
pca.out$x[,1:2]
plot(pca.out$x[,1:2], col=2:4, xlab="Z2", pch=19)

#   c)

km.out = kmeans( x, 3, nstart=20)
table(km.out$cluster, c(rep(1,20), rep(2,20), rep(3,20)))

#   d)
km.out= kmeans(x,2, nstart=20)

km.out$cluster


#  e)

km.out = kmeans(x, 4, nstart=20)
km.out$cluster

#  f)
km.out = kmeans(x, 4, nstart=20)
table(km.out$cluster, c(rep(1,20), rep(2,20), rep(3,20)))

# g)

km.out = kmeans(scale(x), 3, nstart=20)
km.out$cluster


# Exercise 11:
# a)
library(ISLR)
data = read.csv("5 Semester/Data Mining/Homework/Ch10Ex11.csv" ,header=F)
dim(data)

#b)

dd= as.dist(1-cor(data))
plot(hclust(dd, method="complete"))
plot(hclust(dd, method="single"))
plot(hclust(dd, method="average"))


#c)

pr.out = prcomp(t(data))
summary(pr.out)

total_load= apply(pr.out$rotation,1,sum)
indices= order(abs(total_load), decreasing=T)
indices[1:10]

total_load[indices[1:10]]
