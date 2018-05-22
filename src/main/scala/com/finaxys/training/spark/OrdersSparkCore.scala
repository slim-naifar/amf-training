package com.finaxys.training.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object OrdersSparkCore {


  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .config("spark.master", "local[*]")
      .appName("Orders Spark Core")
      .getOrCreate()

    val orders: RDD[String] = spark.sparkContext.textFile(args(0))

  }
}
