# Request Log Analysis

## Description

This exercise is intended to prepare you to create a log parsing
library for one (or more) of your applications.

The basic pattern is to read a text log, where one line is a single
record, parse it into a case class representing one log Record, and
to perform some analysis upon the resulting Dataset or RDD.

You'll be parsing Apache access logs today. The result of this exercise
will be a simple application for analysing the use of our fake website,
and easily answering questions about it.

The application should be test driven; there are some example tests
to get you started, but you may not want to test drive every line of
code from the start. Frequently, I will tinker at the REPL in order
to understand better what I'm trying to accomplish, only to come in 
with a test once my direction is more clear.

## The problem we're solving

In an effort to make this website better for our users, we want to
understand how they use it, and where there may be issues we don't
see but should be addressed.

The answers to the following questions will help us get the 
understanding we seek:

1. Which URL is most popular?
1. Which URL refers the most traffic?
1. Which URL is most costly to serve?
1. What is the most common device type accessing the site?
1. Should we optimize for phones & tablets?
   1. If so, which pages should we optimize first?
1. What is the busiest time of day?
1. Are any errors occurring frequently?
1. Are there any pages referencing broken links?

## Where to start?
There are two sub-modules; one called "solution" and one called
"exercise". Start with the "exercise" module.

The "solution" module is not "the" solution, by any stretch, but
it is "a" solution

The logs are plain text, and have a regular format. An easy way
to parse them is using a Regular Expression.

Now we have two problems...

Rather than spend a lot of time working out /just/ the right Regex
I'll just give you the one I came up with:

```scala
lazy val requestLogExtractor = 
"""(.+) - - \[(.+)\] "([A-Z]+) (.+) HTTP/..." (\d{3,}) (-|\d+) "(.+?)" "(.+)".*""".r
```

Extractors in Scala help make dealing with this regex much easier: 
[Extracting Parts from Regular Expression Matches](https://alvinalexander.com/scala/how-to-extract-parts-strings-match-regular-expression-regex-scala)

In addition, the log has a timestamp. The format is like so:
```scala
lazy val df: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
```


## References

The API walkthrough we did today probably won't be enough to make you
100% ready to tackle this exercise un-aided. You'll find these links
useful as you get into it.

Feel free to help each other, and of course I'll be available for questions.

### RDD
* [Spark Context](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.SparkContext)
* [RDD](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.rdd.RDD)
* [PairRDDFunctions](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.rdd.PairRDDFunctions)

### Dataset
* [SparkSession](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.sql.SparkSession)
* [DataFrameReader](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.sql.DataFrameReader)
* [Dataset](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.sql.Dataset)
* [functions](https://spark.apache.org/docs/2.1.1/api/scala/index.html#org.apache.spark.sql.functions$)

