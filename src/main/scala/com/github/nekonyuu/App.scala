package com.github.nekonyuu

import com.databricks.spark.corenlp.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object App {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.
      master("local")
      .appName("example")
      .getOrCreate()

    import spark.implicits._

    val letterContent: Seq[String] = List(scala.io.Source.fromURL("https://static.nyuu.eu/datasets/nlp/lettres-poilus.txt").mkString)

    val data = spark.sparkContext.parallelize(letterContent).toDS
      .map(_.trim())
      .filter(_.nonEmpty)
      .withColumnRenamed("value", "text")
      .withColumn("filename", lit("lettres-poilus.txt"))

    val globalData = data.groupBy("filename")
      .agg(
        concat_ws(" ", collect_list(data("text"))).as("text")
      )
      .select(explode(ssplit(col("text"))).as("sentences"))
      .withColumn("id", monotonically_increasing_id())
      .orderBy("id")

    globalData.sample(0.1)
  }

}
