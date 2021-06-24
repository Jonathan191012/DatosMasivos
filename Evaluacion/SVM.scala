// Algorithm Linear Support Vector Machine
def svm():Unit={
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
// We use this code to minimize errors
Logger.getLogger("org").setLevel(Level.ERROR)
// We start a simple spark session
val spark = SparkSession.builder().getOrCreate()
// We load the dataset
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")


val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(df)

val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer.fit(features).transform(features)

// SVM algorithm
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
// Fit the model
val lsvcModel = lsvc.fit(dataIndexed)
println("\nAlgoritmo Linear Support Vector Machine\n")

// Print the coefficients and intercept for linear svc
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")

}
// Run SVM
svm()

// Results display
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("ALL RESULTS IN MB")
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)