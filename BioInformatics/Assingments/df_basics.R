install.packages("scatterplot3d")  # Run the first time

setwd("/Users/Pat/Documents/R/href19")

# A data frame is used for storing data tables. It is a list of vectors of equal length
# You can think of the vectors as columns in a database or excel spreadsheet
n <- c(2, 4, 10) 
s <- c("apple", "dog", "cat") 
b <- c(TRUE, FALSE, TRUE) 
df <- data.frame(n, s, b)       # df is a data frame
str(df)

# subset of a dataframe: choose the columns we want by name
df2 <- df[, c("n","s")]

# can take slices and use column names
df3 <- df[1:2,3]
df4 <- df[1:2,2:3]
df5 <- df[1:2,c("s","b")]


#fetches all of the rows of dat for which dat$col=="value", and all of the columns
#dat[dat$col=="value",]


colnames(mtcars)
mtcars_sub <- mtcars[,1:4]
nrow(mtcars_sub) 
ncol(mtcars_sub) 
str(mtcars)
help(mtcars)


summary(mtcars_sub)
plot (mtcars_sub)
typeof(mtcars_sub["mpg"])
typeof(mtcars_sub[1])
typeof(mtcars_sub$mpg)
hist(mtcars_sub$mpg)
