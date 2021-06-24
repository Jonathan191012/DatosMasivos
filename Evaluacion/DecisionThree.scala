// Decision Three algorithm 

def dessiciontre():Unit={

import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}

// We use this code to minimize errors
Logger.getLogger("org").setLevel(Level.ERROR)
// We start a simple spark session
val spark = SparkSession.builder().getOrCreate()
// We load the dataset
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(df)

val labelIndexer0 = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer0.fit(features).transform(features)

// Decision tree algorithm

val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4)

val Array(trainingData, testData) = dataIndexed.randomSplit(Array(0.7, 0.3))

// Train a DecisionTree model.
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Convert indexed labels back to original labels.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Chain indexers and tree in a Pipeline.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

// Train model. This also runs the indexers.
val model = pipeline.fit(trainingData)

// Make predictions.
val predictions = model.transform(testData)

// Select example rows to display.
println("\nAlgoritmo Decision Three\n")

predictions.select("predictedLabel", "label", "features").show(10)

// Select (prediction, true label) and compute test error.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}\n")

val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n\n ${treeModel.toDebugString}")

}

//Run decision tree
dessiciontre()

//Results display
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("ALL RESULTS IN MB")
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)