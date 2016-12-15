# Name: John LaGue
# Lab 5: Resampling Methods
# 
# 
# Exercise 2:
  #a) 1-1/(n)
  #b) 1-1/(n)
  
  #c) Since bootstraping does use replacement the probability that the jth observ. is not in the sample could be (1-1/n)^n because each 'observation' is replaced so the prob would be raised to the n

  #d)  1-(1-1/n)^n = 1-(4/5)^5 = .67232
  #e)  1-(1-1/n)^n = 1-(99/100)^100 = .6339677
  #f)  1-(1-1/n)^n = 1-(9999/10000)^10000 = .632139
  #g)  
      x=c(1:100000)
      plot(x,1-(1-1/x)^x)
  # the plot rapidly converges to just above .6 or more likely .63
  #h)
      store=rep(NA,10000)
      for(i in 1:10000){
        store[i]=sum(sample(1:100, rep=TRUE)==4)>0
      }
      mean(store)
  #this is almost exactly what we expected, with a mean of .6344. Meaning the 4th observation was in the sample about 63% of the time jsut as we calculated.
      
# Exercise 5:
# a)
  library(ISLR)
  log.fit=glm(default~balance+income, data=Default, family = 'binomial')
  summary(log.fit)
#b)
  nrow(Default)
  train<-Default[1:8000,]
  validation<-Default[-(1:8000),]
 
  log2.fit=glm(train$default~train$balance+train$income, family = 'binomial')
  summary(log2.fit)
  ?predict
  log.probs= predict(log2.fit, test=validation, type="response")
  pred.log=ifelse(log.probs>0.5,"Yes","No")

  mean(pred.log!=validation$default)
  
#c)
  #1)
    nrow(Default)
    train2<-Default[1:7000,]
    validation2<-Default[-(1:7000),]
    log3.fit=glm(train2$default~train2$balance+train2$income, family = 'binomial')
    #summary(log3.fit)
    log.probs1= predict(log3.fit, test=validation2, type="response")
    pred.log1=ifelse(log.probs1>0.5,"Yes","No")
    mean(pred.log1!=validation$default)
  #2)
    train<-Default[2000:10000,]
    validation3<-Default[-(2000:10000),]
    log4.fit=glm(train$default~train$balance+train$income, family = 'binomial')
  #  summary(log4.fit)
    log.probs2= predict(log4.fit, test=validation3, type="response")
    pred.log2=ifelse(log.probs2>0.5,"Yes","No")
    
    mean(pred.log2!=validation$default)
  #3)
    train<-Default[3000:10000,]
    validation4<-Default[-(3000:10000),]
    log5.fit=glm(train$default~train$balance+train$income, family = 'binomial')
  #  summary(log5.fit)
    log.probs3= predict(log5.fit, test=validation4, type="response")
    pred.log3=ifelse(log.probs3>0.5,"Yes","No")
    mean(pred.log3!=validation$default)
#d)
    
  train5<-Default[3000:10000,]
  validation5<-Default[-(3000:10000),]
  log6.fit=glm(train5$default~train5$balance+train5$income+factor(train5$student), family = 'binomial')
  #  summary(log6.fit)
  log.probs5= predict(log6.fit, test=validation5, type="response")
  pred.log5=ifelse(log.probs5>0.5,"Yes","No")
  mean(pred.log5!=validation5$default)
  # adding the dummy variables did not change much 
  
# Exercise 6:
# ...
set.seed(1)
  #a) 
    log.fit=glm(default~balance+income, data=Default, family = 'binomial')
    summary(log.fit)
    coef(summary(log.fit))

  #b)
    boot.fn=function(data, index){
      log.fit=glm(default~income+balance, data=data, family = 'binomial', subset=index)
      return (coef(summary(log.fit)))
      
    }
  #c)
    library(boot)
    ?boot
    boot(Default, boot.fn, 50)
    #the standard errors using bootsrap and boot.fn are extremely small

# Exercise 8:
    #a)
    set.seed(1)
    y=rnorm(100)
    x=rnorm(100)
    y=x-2*x^2 +rnorm(100)
    
    # Here n is 100 and there is only one predictor variable but it is used twice, once in linear form and another in quadratic
    
    #b)
      plot(x,y)
    #the points on the scatter plot appear to be making an upside down parabola or a hump shape, centered around 0.
      
    #c)
      ?cv.glm
      data=data.frame(x,y)
      set.seed(1)
      m1<-glm(y~x)
      cv.glm(data, m1)
      m2<- glm(y~x+I(x^2))
      cv.glm(data, m2)$delta
      m3<- glm(y~x+I(x^2)+I(x^3))
      cv.glm(data, m3)$delta
      m4<-glm(y~x+I(x^2)+I(x^3)+I(x^4))
      cv.glm(data, m4)
      abline(m1)
      
      #d)
      set.seed(5)
      m1<-glm(y~x)
      cv.glm(data, m1)$delta
      m2<- glm(y~x+I(x^2))
      cv.glm(data, m2)$delta
      m3<- glm(y~x+I(x^2)+I(x^3))
      cv.glm(data, m3)$delta
      m4<-glm(y~x+I(x^2)+I(x^3)+I(x^4))
      cv.glm(data, m4)
      abline(m1)
      
      #the results do not change when the seed changes because the error will not change even if the random number start place does.
    #e)
     # The quadratic model had the lowet error. This is what we expected because the initial scatter plot looked as if it followed an 'upside down' quadratic
    #f)
      summary(m1)
      summary(m2)
      summary(m4)
      #the least squares results show that the x^2 is very significant, which agrees with out CV results
      