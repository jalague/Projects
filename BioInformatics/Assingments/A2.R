getwd()
data<- read.csv("5 Semester/BioInformatics/Assingments/2/mom_baby_PKU2.csv")
summary(data)
library(ggplot2)

str(data)
class(data$wt_gain)
View(data)

#cleaning up
data$age<-ifelse(data$age<0, NA, data$age)
data$mVIQ<-ifelse(data$mVIQ<0, NA, data$mVIQ)
data$mPIQ<-ifelse(data$mPIQ<0, NA, data$mPIQ)
data$mIQ<-ifelse(data$mIQ<0, NA, data$mIQ)
data$wt_gain<-ifelse(data$wt_gain=='?', NA, data$wt_gain)
data$phe_sd<-ifelse(data$phe_sd<0, NA, data$phe_sd)
data$phe_avg<-ifelse(data$phe_avg<0, NA, data$phe_avg)
data$gestation<-ifelse(data$gestation<0, NA, data$gestation)
data$birth_len<-ifelse(data$birth_len<0, NA, data$birth_len)
data$birth_wt<-ifelse(data$birth_wt<0, NA, data$birth_wt)
data$head_circum<-ifelse(data$head_circum<0, NA, data$head_circum)
data$MDI<-ifelse(data$MDI<0, NA, data$MDI)
data$PDI<-ifelse(data$PDI<0, NA, data$PDI)


hist(data$age, breaks=15)
hist(data$mVIQ, breaks=15)
hist(data$mPIQ, breaks=15)
hist(data$mIQ, breaks=15)
hist(data$wt_gain, breaks=15)
hist(data$phe_avg, breaks=15)
hist(data$gestation, breaks=15)
hist(data$birth_wt, breaks=15)
hist(data$birth_len)
hist(data$head_circum)
hist(data$MDI)
hist(data$PDI)

cor(data)

fit1<-lm(MDI~phe_avg, data=data)
summary(fit1) #4-----------------
plot(fit1)
plot(data$phe_avg, data$MDI, main="MDI as function of phe_avg")
abline(fit1)

#5--------
fit2<- lm(PDI~phe_avg,data=data)
summary(fit2)

fit3<- lm(MDI~gestation,data = data)
summary(fit3)

fit4<- lm(PDI~gestation,data=data)
summary(fit4)

fit5<-lm(MDI~age, data=data)
summary(fit5)
fit5<-lm(PDI~age, data=data)
summary(fit5)
#Best models
fit6<-lm(PDI~phe_avg+gestation,data=data)
summary(fit6)
fit7<-lm(MDI~phe_avg+gestation,data=data)
summary(fit7)

fit8<- lm(MDI~phe_avg+gestation+phe_sd+mIQ+mPIQ+age+birth_wt+birth_len+head_circum, data=data)
summary(fit8)
fit9<-lm(PDI~phe_avg+gestation+phe_sd+mIQ+mPIQ+age+birth_wt+birth_len+head_circum, data=data)
summary(fit9)

fit10<-lm(MDI~phe_avg+gestation+head_circum, data=data)
summary(fit10)
plot(data$MDI)
abline(fit10)
fit11<-lm(PDI~phe_avg+gestation+head_circum, data=data)
summary(fit11)
