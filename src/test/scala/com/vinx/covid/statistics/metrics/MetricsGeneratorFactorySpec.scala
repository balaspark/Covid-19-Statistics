package com.vinx.covid.statistics.metrics
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{FlatSpec, Matchers}


class MetricsGeneratorFactorySpec extends FlatSpec with Matchers {

  behavior of "MetricsGeneratorFactorySpec"

  private val spark : SparkSession = SparkSession.builder().master("local[*]").getOrCreate()
  private val df = spark.emptyDataFrame

  it should "throw illegal argument exception" in {

    val thrown = intercept[IllegalArgumentException] {
      val fakeMetricsGenerator =  MetricsGeneratorFactory.createMetricsGenerator("not_existing_metric", "not_existing_data_path")
    }
  }


  it should "create a italyDeclaredCasesOverTime object" in {
    val generator = MetricsGeneratorFactory.createMetricsGenerator("italyDeclaredCasesOverTime", "src/main/resources/")
    generator.getClass.getSimpleName shouldBe "ItalyDeclaredCasesOverTime"
  }

  it should "create a ItalyCasesPerCityOverTime object" in {
    val generator = MetricsGeneratorFactory.createMetricsGenerator("italyCasesPerCityOverTime", "src/main/resources/")
    generator.getClass.getSimpleName shouldBe "ItalyCasesPerCityOverTime"
  }
}
