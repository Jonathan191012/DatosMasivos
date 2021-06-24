# Practice 3 Random Forest Classifier

1.- Import libraries

```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession
```

2.- Create a SparkSession

```scala
val spark = SparkSession.builder().getOrCreate()
```

3.- Load data and convert to DataFrame

```scala
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

data.printSchema()
data.show()
```

4.- Creation of the label index. Adjust index to the dataframe

```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```

5.- Identify and index the features. Features values > 4 are continuous.

```scala
val featureIndexer = new VectorIndexer()
.setInputCol("features")
.setOutputCol("indexedFeatures")
.setMaxCategories(4)
.fit(data)
```

6.- Split the data into training and test sets (70% training and 30% for tests)

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

7.- Create a RandomForest model.

```scala
val rf = new RandomForestClassifier()
.setLabelCol("indexedLabel")
.setFeaturesCol("indexedFeatures")
.setNumTrees(10)
```

8.- Convert indexed labels back to original labels.

```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.setLabels)
```

9.- Pipeline with chain indexers and forest.

```scala
val pipeline = new Pipeline()
.setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
```

10.- Fit Model.

```scala
val model = pipeline.fit(trainingData)
```

11.- Make predictions.

```scala
val predictions = model.transform(testData)
```

12.- Show test rows.

```scala
predictions.select("predictedLabel", "label", "features").show(5)
```

13.- Select (prediction, true label) and compute test error.

```scala
val evaluator = new MulticlassClassificationEvaluator()
.setLabelCol("indexedLabel")
.setPredictionCol("prediction")
.setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```

14.- Print RandomForestModel

```scala
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
```