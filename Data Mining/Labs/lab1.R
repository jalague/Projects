# Name: John LaGue
# Lab 1: R
# 

# Exercise 4:
# a)  1. An example of a classifcation problem would be lets say you have a large population of highscool students but only a few teachers to watch them. The teachers want to know which students they should pay more attention
#    to and which ones they can leave alone. So they create a classification algorithim that takes a set of characteristics of each student and then classifies them as either high risk or low risk.
#     -Response would be the risk leave and the predictors could be something like age, gender, GPA, or past discpline actions
#    - This would be an application of prediction because the teachers are trying to predict who will be a high risk student 
#     2. Another example of classification problem could be a large company hiring a new employee, but they have to sift through thousands of applications. They could use a classification system to sort out any applicants that immediately ineligible
#     - The response here would be eligibility of individuals and the predictors could be years of previous experience, education level,required skills.
#     - This would be a prediciton problem because the company is predicting which applicants are either eligible or ineligible
#     3. A third example could be a company that is about to release a new products and they want to predict whether the products will be a success or failure.
#        -This is clearly a prediction problem, since the company does not yet know whether the products will succeed. 
#        - So the response variable would be either success or failure and the the predictors could be anything the company knows will affect the success. For example, they may use focus group ratings, product price, and product market size to determine whether it will be a success or failure or break even 
# b)  1. An example of regression could be an investment company taking several variables and exploring their effect on stock values for various companies.
#       -This would be an inference problem because the company is infering which variables have an affect on the stock value.
#       -For this problem the stock value would be the response and the predictors could be any variables the company believes will affect the price (i.e company size, new product release, etc)
#     2. Another regression problem would be if a teacher wnats to see if a students age is related to how well they will preform in her course.
#       -The response in this problem would be the grade the sudents get in the course and the predictor would be the age of the students.
#       -This would be an inference problem, in which the professor would look back at previous years and analyze the grades and ages of her past students.
#     3. A final example of regression could an insurance company that is looking at the accident records of it's clients and wants to find out what characteristics of drivers correlate to more accidents.
#       -So here the predictors would be characteristics such as age, gender, accidnet redcord, etc. The response would be liklihood of getting into an accident.
#       -This would mean that this problem is an inference problem because the company is infering relationships from past accidents, not trying to predict something.
#
# c)   1. An example of a clustering problem could be if Netlix wanted a new way to sort movies and shows into different genres. They would use clustering to group movies that are most simialr together into one genre.
#      2. Another example could be if Amazon wanted to group customers into different groups soley based on their previous buying habits. So people who all bought outdoor hiking gear would be grouped into one category so that Amazon could target them with certain advertisements.
#      3. Finally, clustering could be used to group patients at a hospital into categories based on their activity level. So there could be very active, somewhat active, and inactive patients as groups. This would help simplify the data so that new inferernces or discoveries could be made further.
#


# Exercise 8:

#getwd()
#setwd("C:/Users/John/Documents/5 Semester/Data Mining/Homework")

#a)
college = read.csv("College.csv")
fix(college)
#b)
rownames(college)=college[,1]
fix(college)
college=college[,-1]
fix(college)
#c)
summary(college)
pairs(college[,1:10])
plot(college$Outstate~ college$Private)

Elite= rep("No", nrow(college))
Elite[college$Top10perc>50]="Yes"
Elite=as.factor(Elite)
college=data.frame(college,Elite)
summary(college)
plot(college$Outstate~Elite)
hist(college$Top10perc)
par(mfrow=c(2,2))
hist(college$Top10perc, breaks = 5)
hist(college$Top10perc, breaks=2)
hist(college$Top10perc, breakse=10)

#Summary