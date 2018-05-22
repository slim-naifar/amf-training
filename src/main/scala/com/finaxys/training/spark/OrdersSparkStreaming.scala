package com.finaxys.training.spark

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object OrdersSparkStreaming {

  def main(args: Array[String]) {
    // Create the context with a 2 second batch size
    val sparkConf = new SparkConf().setAppName("Orders Spark Streaming").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val hostname: String = args(0)
    val port: Int = args(1).toInt

    val orders : DStream[String]  = ssc.socketTextStream(hostname, port, StorageLevel.MEMORY_AND_DISK_SER)
  }

}
