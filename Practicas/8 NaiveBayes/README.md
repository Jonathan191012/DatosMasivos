# Practice 8 Naive Bayes

1.- We import the libraries we occupy

```scala
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
```

2.- Load data in LIBSVM storage format as a DataFrame.

```scala
val data = spark.read.format("libsvm").load("C:/Users/brise/Documents/GitHub/NaiveBayes/sample_libsvm_data.txt")

println ("Numero de lineas en el archivo de datos:" + data.count ())
```

3.- Show 20 lines by default

```scala
data.show()
```

4.- Randomly divide the data set into training set and test set according to the weights provided. You can also specify a seed

```scala
val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), 100L) // El resultado es el tipo de matriz, y la matriz almacena los datos del tipo DataSet
```

5.- Incorporate into the training set (adjustment operation) to train a Bayesian model

```scala
val naiveBayesModel = new NaiveBayes().fit(trainingData)
```

6.- The model calls transform () to make predictions and generate a new DataFrame

```scala
val predictions = naiveBayesModel.transform(testData)
```

7.- Data output of prediction results

```scala
predictions.show()
```

8.- Evaluation of the precision of the model

```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

val precision = evaluator.evaluate (predictions) // Precisión

println ("tasa de error =" + (1-precision))
```