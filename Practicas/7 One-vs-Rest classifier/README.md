# Practice 7 One vs Rest classifier

1.- Import Libraries

```scala
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

2.- Upload the file

```scala
val inputData = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
```

3.- Generate the division of the train and test set.

```scala
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))
```

4.- Instantiate the base classifier

```scala
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)
```

5.- An instance of the One Vs Rest classifier is created.

```scala
val ovr = new OneVsRest().setClassifier(classifier)
```

6.- The multiclass model is trained (train).

```scala
val ovrModel = ovr.fit(train)
```

7.- The model is scored in the test data (test).

```scala
val predictions = ovrModel.transform(test)
```

8.- The evaluator is obtained

```scala
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

9.- The classification error in the test data is calculated.

```scala
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")
```