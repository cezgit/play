package com.cj.walkthrough

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext, sql}

class SparkMain(appName: String) {

  Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

  val conf = new SparkConf()

  conf.setMaster("local[*]")
  conf.setAppName(appName)
  conf.set("spark.sql.shuffle.partitions", "8")

  lazy val spark = SparkSession.builder().config(conf).getOrCreate()
  lazy val sc = spark.sparkContext

}
