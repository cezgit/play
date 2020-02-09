package com.cj.walkthrough

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

import scala.language.postfixOps

object DatasetMain extends SparkMain("Dataset API Walkthrough") with App with SampleData {

  /**
    * The SparkSession instance has the ability to optimize encoding of
    * most Scala primitive types and Product types (case classes) as
    * implicit conversions, but the implicit conversion need to be
    * in scope
    */

  import spark.implicits._

  /**
    *
    */
  val bookDS: DataFrame = spark.read.text(args(0)).withColumnRenamed("value", "sentence")
  println(s"Total Lines in Book: ${bookDS.count()}.")

  /**
    * The sentences can be broken up into words using the `split` and `explode`
    * functions
    */
  val wordDS: DataFrame =
    bookDS
      .select(split($"sentence", "\\s+").as("word_list"))
      .select(explode($"word_list").as("word"))

  println(s"Total Words: ${wordDS.count()}.")

  /**
    * Let's clean up our words, by making them lowercase, and removing the punctuation
    * using the `lower` and `regexp_replace` functions
    */
  val normalWordsDS: DataFrame =
    wordDS
      .select(lower($"word").as("lowercase_word"))
      .select(regexp_replace($"lowercase_word", "\\W", "").as("word"))

  /**
    * Now we can remove the stop-words by anti-joining them with
    * our meaningful words
    */
  val stopWordsDS: DataFrame = spark.createDataset(stopWords.toSeq).withColumnRenamed("value", "stop_word")
  val goodWordsDS: DataFrame =
    normalWordsDS
      .join(stopWordsDS, normalWordsDS("word") === stopWordsDS("stop_word"), "leftanti")
      .drop($"stop_word")
      .where(length($"word") > 2)

  println(s"Words Worth Counting: ${goodWordsDS.count()}.")

  /**
    * We can count the words pretty easily using `groupBy` and `count`
    */
  val goodWordCountsDS: DataFrame = goodWordsDS.groupBy($"word").count()

  /**
    * And to get the most popular, we can `sort` descending
    */
  val goodWordsOrderedCountDS: DataFrame = goodWordCountsDS.orderBy($"count" desc)

  println("Top 10 good words")
  goodWordsOrderedCountDS.show(10)

  println("Top 10 other words")
  normalWordsDS
    .join(stopWordsDS, normalWordsDS("word") === stopWordsDS("stop_word"))
    .groupBy($"word").count()
    .orderBy($"count" desc)
    .show(10)

}
