getwd()
load("5 Semester/BioInformatics/Labs/9-19/df.rdata")
head(df)

# For each model:
# a.  execute a summary of the fit 

# b.  plot Residuals vs Fitted


#1. y2 model: fit y2 as a function of main effect x1

y2.model1<- lm(y2~x1, data=df)
summary(y2.model1)
plot(y2.model1,1)

#

#2. y2 model: fit y2 as a function of main effect x2
# What do you conclude from #1 and #2?

y2.model2<-lm(y2~x2,data=df)
summary(y2.model2)
plot(y2.model2,1)

#I conclude that x1 and x2 are not signifigant predictors of y2 and x2 may be just a conversion of y2.

#3. y1 model: fit y1 as a function of main effect x1

y1.model<-lm(y1~x1,data=df)
summary(y1.model)
plot(y1.model,1)

#4. y1 model: fit y1 as a function of main effect x2
y1.model1<-lm(y1~x2,data=df)
summary(y1.model1)
plot(y1.model1,1)



#5. y1 main effects model: fit y1 as a function of both main effects
y.model<-lm(y1~x1+x2,data=df)
summary(y.model)
plot(y.model,1)


#   Describe your summary results for #1,2,3
#  Found that y2 is not signifigantly predicted by x1 or x2, but y1 is signifigantly predicted by x1.

#   Do you see a pattern in two of the three plots of Residuals vs Fitted

# Yes, I saw a parttern in residual plots 1 and 3, there is a non-linear relationship

# Questions 6=10 will be displayed on the classroom projector
##########################################################################


#6 
y.modelq<-lm(y1~I(x1^2),data=df)
summary(y.modelq)
plot(y.modelq,1)

#7
y.modelq1<-lm(y1~I(x1^2)+x1,data=df)
summary(y.modelq1)
plot(y.modelq1,1)

#8
y.modelI<-lm(y1~x1:x2,data=df)
summary(y.modelI)
plot(y.modelI,1)

#9
y.modelIF<-lm(y1~x1*x2,data=df)
summary(y.modelIF)
plot(y.modelIF,1)

#10
y.models<-lm(y1~x1:x2+x1+I(x1^2),data=df)   #better fit to exclude main effect x2
summary(y.models)
plot(y.models,1)

# y1= 2.058x1^2+ 1.62x1+ -2.96x1x2

