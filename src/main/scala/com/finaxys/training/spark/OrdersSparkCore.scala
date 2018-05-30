package com.finaxys.training.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object OrdersSparkCore {


  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .config("spark.master", "yarn-master")
      .appName("Orders Spark Core")
      .getOrCreate()

    val orders: RDD[String] = spark.sparkContext.textFile("C:\\Users\\Finaxys\\IdeaProjects\\amf-training\\src\\main\\resources\\orders.csv")

   orders.map(order => order.split(";"))
      .map(order => (order(1), order(6).toInt))
      .mapValues(p => (p, 1))
      .reduceByKey((p1, p2) => (p1._1 + p2._1, p1._2 + p2._2))
      .mapValues(p => p._1 / p._2)
      .collect().foreach(println)
  }
}
