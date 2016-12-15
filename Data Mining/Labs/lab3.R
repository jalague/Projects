# Name: First Last
# Lab 3: Linear Regression
# 
# 
# Exercise 3:
# a) answer number ii. is correct because our model is saying that females earn 35 more than males, thus female earns more decpite the -10.
  salary<-50+4*20+.07*110+35+.01*110*4+(-10)*4
  salary
 #b) 137.1
 #c) False, just becuase the coefficeinet is small that does not mean it is unimportant. For example IQ is large number so it will compensate for the small coefficient
  
# Exercise 8:
  auto<-read.csv("C:/Users/John/Documents/5 Semester/Data Mining/Homework/Auto.csv")
  #a) 
      model1<-lm(auto$mpg~auto$horsepower)
      summary(model1)
    #Horsepower seems to have a tiny affect on mpg, and most of the horsepower values do not have a statistically signifigant correlation.
  #b)
      plot(model1)
      plot(auto$horsepower, auto$mpg)
      abline(model1)
  #c) the fit has too many predictors to be visualized on a 2D plot.
# Exercise 10:
  library(ISLR)
  #a) 
      model2<- lm(Sales~Price+Urban+US, data = Carseats)
      summary(model2)
      ?Carseats
  #b)From our model we can say that Sales are most likely related to the price of the vehicle and whether the store was in the United States.
      # Price: -.054 meaning that for every dollar increase in price, sales  go down 54 dollars. Urban: -.02 meaning that if the store is urban the sales go down by $20. US: 1.2, meaning if the store is in the US the sales will increase by $1200
  #c) sales= 13.04+ -.054*(Price)+ (-.02)*(Urban)+1.20*(US)
  #d) We can reject the null hypothesis for Urban variable as the p value is .93, far above .05
  #e) 
      model3<- lm(Sales~Price+US, data=Carseats)
      summary(model3)
  #f) These models fit the data very poorly as the R^2 and Adjusted R^2 are about .23 for both indicating that the predict the data worse than random.
  #g) Price[-.064,-.044], US[.691, 1.70]
      confint(model3) 
  #h)
      var(model3)
      summary(Carseats)
      plot(model3)
      #looking at the Residuals vs Leverage plot and using Cook's distance it seems that there are no significant outliers in the model.
      
#13
      set.seed(1)
      #a)  
      ?rnorm
        X<-rnorm(100, 0, 1)
      #b)  
      eps<-rnorm(100, 0, .25)
      
      #c) 
      Y<- -1+.5*X+eps
      length(Y)
      #length is 100, Bo is -1, B1 is .5
      #d)
        plot(X, Y)
        #It looks like the data points follow a line with intercept near -2.5 and a slope of .5. The data looks evenly distributed around this line.
      #e)
        model<-lm(Y~X)
        summary(model)
        abline(model)  # the model fits the data quite well with a .7448 adjusted R squared
        #Bo estimate is -1.01 and the B1 estimate is .507, which are both very close to the original model.
        
      #f) 
        abline(model, col='red')
        legend(1, -2, "Model", col="red", lty=c(1,1))
      #g)
        model2<-lm(Y~X+I(X^2))
        summary(model2)
        #There is NO evidence that this improves the model fit because the quadratic value has a high p-value and the adjusted R^2 only increases by .004. Thus it barely improves the model, if at all.
      #h)
        
        X1<-rnorm(100, 0, 1)
        eps1<-rnorm(100, 0, .05)
        Y1<- -1+.5*X1+eps1
        plot(X1, Y1)
        model1<-lm(Y1~X1)
        summary(model1)  # b0= -.9994, b1= .4983
        abline(model1, col="red")
        legend(1, -2, "Model1", col="red", lty=c(1,1))
        #with .o5 variance in the irreducible error the fit of the model improved greatly with an adjusted R^2 .9901
        
      #i)
        
        X2<-rnorm(100, 0, 1)
        eps2<-rnorm(100, 0, .5)
        Y2<- -1+.5*X2+eps2
        plot(X2, Y2)
        model3<-lm(Y2~X2)
        summary(model3)  # b0= -.9994, b1= .4983
        abline(model3, col="red")
        legend(1, -2, "Model1", col="red", lty=c(1,1))
        #with .5 variance in the irreducible error the fit of the model decreased greatly with an adjusted R^2 .5223. Highlighting how important the irreducible error is.
    #j)
        confint(model)
        confint(model1)
        confint(model3)
     
    #   Original model:  Bo=[-1.068, -.9517], B1= [.44, .566],  Noisier Model: Bo =[-1.1078, -.902], B1 =[.417, .6128], Less NOisey: Bo=[-1.01,-.988], B1=[.488, .508]
    # these confidence intervals highlight what the R^2 values already told us, that the model with the least variance in the error has the best fit and smallest confidence interval.
#15)
        library(MASS)
        View(Boston)
        ?Boston
        #a)
          crime1<-lm(crim~zn, data=Boston)    #stat sig. horrible fit
          summary(crime1)
          crime2<-lm(crim~indus, data=Boston) #sig., but horrible fit
          summary(crime2)
          crime3<-lm(crim~chas, data=Boston)
          summary(crime3)
          crime4<-lm(crim~nox, data=Boston) #sig. but horrible fit
          summary(crime4)
          crime5<-lm(crim~rm, data=Boston)  #sig, but horrible fit
          summary(crime5)
          crime6<-lm(crim~age, data=Boston)  #sig. , but horible fit
          summary(crime6)
          crime7<-lm(crim~dis, data=Boston)  #sig, bad fit
          summary(crime7)
          crime8<-lm(crim~rad, data=Boston) # sig, bad fit but better
          summary(crime8)
          crime9<-lm(crim~tax, data=Boston) #sig, bad fit bu better
          summary(crime9)
          crime10<-lm(crim~ptratio, data=Boston) #sig, but horrible fit
          summary(crime10)
          crime11<-lm(crim~black, data=Boston) #sig, but bad fit
          summary(crime11)
          crime12<-lm(crim~lstat, data=Boston)# sig, still bad fit
          summary(crime12)
          crime13<-lm(crim~medv, data=Boston) #sig, and bad fit
          summary(crime13)
          
          plot(Boston$zn,Boston$crim)
          plot(Boston$age,Boston$crim)
          plot(Boston$dis,Boston$crim)
          plot(Boston$rad,Boston$crim)
          plot(Boston$tax,Boston$crim)
          
          plot(Boston$pratio,Boston$crim)
          plot(Boston$black,Boston$crim)
          plot(Boston$lstat,Boston$crim)
          plot(Boston$medv,Boston$crim)
          
          # some of the plots look like there could be some possible correlations between the predictors and Crime, but some look like they have little to no correlation.
          # However almost all the models had statistical signifigance, except for the chas predictor, but they all had horrible fit lines
        #b)
          crimeALL<- lm(crim~zn+indus+chas+nox+rm+age+dis+rad+tax+ptratio+black+lstat+medv,data=Boston)
          summary(crimeALL)
          # we can reject the null hypothesis for predictors dis, rad, medv, and probably zn and black beacuse its p-value is less than .05.
          
        #c)
          full<-summary(crimeALL)$coefficients[2:14,1]
          indv<-c(summary(crime1)$coefficients[2,1],summary(crime2)$coefficients[2,1],summary(crime3)$coefficients[2,1],summary(crime4)$coefficients[2,1],summary(crime5)$coefficients[2,1],summary(crime6)$coefficients[2,1],summary(crime7)$coefficients[2,1],summary(crime8)$coefficients[2,1],summary(crime9)$coefficients[2,1],summary(crime10)$coefficients[2,1],summary(crime11)$coefficients[2,1],summary(crime12)$coefficients[2,1],summary(crime13)$coefficients[2,1])
          plot(indv, full)
          #the results are very different, as the coeffiecients go from mostly positive and signifigant to negative and mostly not signifigant
        #d)
          crimex1<-lm(crim~zn+I(zn^2)+I(zn^3), data=Boston)    #polynomial elements not significant
          summary(crimex1)
          crimex2<-lm(crim~indus+I(indus^2)+I(indus^3), data=Boston) #polynomial elements very sign.
          summary(crimex2)
          crimex3<-lm(crim~chas+I(chas^2)+I(chas^3), data=Boston) #NA
          summary(crimex3)
          crimex4<-lm(crim~nox+I(nox^2)+I(nox^3), data=Boston) # all stats sign.
          summary(crimex4)
          crimex5<-lm(crim~rm+I(rm^2)+I(rm^3), data=Boston)  #none signifincant
          summary(crimex5)
          crimex6<-lm(crim~age+I(age^2)+I(age^3), data=Boston)  # the higher the polynomial the more stats significant
          summary(crimex6)
          crimex7<-lm(crim~dis+I(dis^2)+I(dis^3), data=Boston)  #all stat sig.
          summary(crimex7)
          crimex8<-lm(crim~rad+I(rad^2)+I(rad^3), data=Boston) # none stat sig
          summary(crimex8)
          crimex9<-lm(crim~tax+I(tax^2)+I(tax^3), data=Boston) # none stat signficant
          summary(crimex9)
          crimex10<-lm(crim~ptratio+I(ptratio^2)+I(ptratio^3), data=Boston) #all moderatley sig.
          summary(crimex10)
          crimex11<-lm(crim~black+I(black^2)+I(black^3), data=Boston) #none sig
          summary(crimex11)
          crimex12<-lm(crim~lstat+I(lstat^2)+I(lstat^3), data=Boston)# none sig.
          summary(crimex12)
          crimex13<-lm(crim~medv+I(medv^2)+I(medv^3), data=Boston) # all stat sign
          summary(crimex13)
          
          #its difficult to determine whether higher order polynomials improve the fit of the mdoel by looking at only each predictor individually, but it looks like the higher order polynomial variables are only statistically significant for values that are already significant in the simple regression model. Menaing that adding the ploynomials will probably NOt improve the fit.