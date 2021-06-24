# Practice 4 Gradient boosted tree classifier

1.- We import the libraries we occupy

```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```

2.- We load the txt file of the established path

```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```

3.- We will create a column using stringIndexer so that the data has its categorization

```scala
val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(data)
 ```

4.- We create a vector that will have a maximum of 4 categories

```scala
val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4)
  .fit(data)
```

5.- We separate the data into two parts, a so-called training with 70% and the other mushroom test with 30%

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

6.- Enter the GPT model

```scala
val gbt = new GBTClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
  .setMaxIter(10)
  .setFeatureSubsetStrategy("auto")
```

7.- We convert the indexed tags to original tags

```scala
val labelConverter = new IndexToString()
  .setInputCol("prediction")
  .setOutputCol("predictedLabel")
  .setLabels(labelIndexer.labels)
```

8.- The chain of indenters and GPT EN Pipeline

```scala
val pipeline = new Pipeline()
  .setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
```

9.- The model is trained. This also runs the indexers

```scala
val model = pipeline.fit(trainingData)
```

10.- We create the predictions.

```scala
val predictions = model.transform(testData)
```

11.- We select the first 5 rows to display them

```scala
predictions.select("predictedLabel", "label", "features").show(5)
```

12.- We select prediction and calculation of the test error.

```scala
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("indexedLabel")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1.0 - accuracy}")

val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")
```