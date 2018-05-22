package com.finaxys.training.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

object OrdersSparkSql {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .config("spark.master", "local[*]")
      .appName("Orders Spark Sql")
      .getOrCreate()

    import spark.implicits._

    val orders: DataFrame = spark.read.option("inferSchema", "true").csv(args(0))

  }
}
