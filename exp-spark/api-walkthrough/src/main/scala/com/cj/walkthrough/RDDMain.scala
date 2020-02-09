package com.cj.walkthrough

import org.apache.spark.rdd.RDD

object RDDMain extends SparkMain("RDD Api Walkthrough") with App with SampleData {

  /**
    * The classic example for distributed data systems
    * is the Word Count.
    *
    * Let's take a look at the Word Count example while
    * we learn some useful parts of the RDD API, and at
    * the seame time, let's see if we can figure out some
    * things about the classic novel -
    *
    * The Count of Monte Cristo... see what I did there? 😏
    *
    * First things first, we need to read the file
    */
  val bookRDD: RDD[String] = sc.textFile(args(0))     // each RDD is one line of text
  println(s"Total Lines in Book: ${bookRDD.count()}.")

  /**
    * We now have an RDD containing the lines of the book.
    * Let's turn these lines into words using flatMap
    *
    * `flatMap` is a mapping of 1 input element A to 0 or more output elements B
    */

  val wordRDD: RDD[String] = bookRDD
    .flatMap(sentence => sentence.split("\\s+"))
  println(s"Total Words in Book: ${wordRDD.count()}.")

  /**
    * Now that we've got some words, let's clean them up by removing
    * some of the punctuation using `map`
    *
    * `map` is a mapping of 1 input element A to 1 output element B
    *
    * We also want to exclude some words that don't convey much meaning using `filter`.
    * We'll remove some "stop" words, and anything less than 3 characters
    *
    * `filter` is a mapping of 1 input element A to 0 or 1 output elements A
    */
  val goodWordsRDD: RDD[String] = wordRDD
    .map(word => word.toLowerCase.replaceAll("\\W", ""))
    .filter(word => !stopWords.contains(word))
    .filter(word => word.length > 2)
  println(s"Words Worth Counting: ${goodWordsRDD.count()}.")

  /**
    * With a list of words, we need a way to count them, so we convert each word
    * into a Tuple containing the word, and a number 1.
    *
    * This yields a special type of RDD, known as PairRDD which has access to
    * some special methods. You can think of the first element of the tuple as
    * a Key, and the second element as a Value, similar to a Map[K, V]
    */
  val goodWordsPair: RDD[(String, Int)] = goodWordsRDD.map(word => (word, 1))

  /**
    * Now we have an RDD with Words and Digits, where the Word is the Key
    * and the Digit is the Value we want to sum. All the Digits are 1
    * right now, but we need to add them up... Time to `reduce`
    *
    * `reduce` combines multiple values into a single value using a binary function
    */
  val goodWordCounts: RDD[(String, Int)] = goodWordsPair.reduceByKey(_ + _)
  println(s"Unique Words Worth Counting: ${goodWordCounts.count()}")

  /**
    * Now, to get the most popular words in our set, we can sort by the
    * word's descending count
    */
  val goodWordsOrderedCount: RDD[(String, Int)] = goodWordCounts.sortBy { case (_, count) => count * -1 }
//  val goodWordsOrderedCount: RDD[(String, Int)] = goodWordCounts.sortBy { pair => -pair._2 }

  println("Top 10 good words")
  goodWordsOrderedCount.take(10).foreach { case (word, count) => println("%12s: %d".format(word, count)) }


  val otherWordsOrderedCount: RDD[(String, Int)] =
    wordRDD
      .filter(word => stopWords.contains(word))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .sortBy { case (_, count) => count * -1 }

  println("Top 10 other words")

  otherWordsOrderedCount.take(10).foreach { case (word, count) => println("%12s: %d".format(word, count)) }

  sc.stop()
}
