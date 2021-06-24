# Practice 2 Decision Tree Classifier

1.- Import the necessary libraries

```scala
import  org.apache.spark.ml.Pipeline 
import  org.apache.spark.ml.classification.DecisionTreeClassificationModel 
import  org.apache.spark.ml.classification.DecisionTreeClassifier 
import  org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator 
import  org.apache .spark.ml.feature. { IndexToString ,  StringIndexer ,  VectorIndexer }
import  org.apache.spark.sql.SparkSession
```

2.- Load the data stored in LIBSVM format as a DataFrame. It is implemented for optimization, it supports classification and regression.

```scala
val data = spark.read.format("libsvm").load("/home/eduardo/Escritorio/expo/sample_libsvm_data.txt")
```

3.- Index tags, adding metadata to the tag column. Fit in the entire dataset to include all labels in the index.

```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```

4.- Automatically identify categorical characteristics and index them.

```scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```

5.- Divide the data into test and training sets (30% reserved for tests).

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

6.- Train a DecisionTree model.

```scala
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
```

7.- Convert indexed tags back to original tags.

```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```

8.- Chain of indexers and tree in a Pipeline.

```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
```

9.- Train model. This also runs the indexers.

```scala
val model = pipeline.fit(trainingData)
```

10.- Make predictions.

```scala
val predictions = model.transform(testData)
```

11.- Select sample rows to display. In this case there will only be 5.

```scala
predictions.select("predictedLabel", "label", "features").show(10)
```

12.- Select (prediction, true label).

```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
```

13.- Calculate the test error.

```scala
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```

14.- Show by stages the classification of the tree model

```scala
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
```