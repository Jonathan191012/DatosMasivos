# Practice 5 Multilayer Perceptron classifier

1.- We import the necessary libraries to obtain the perception of the evaluation

```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

2.- Load the data stored in LIBSVM format as a DataFrame

```scala
val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
```

3.- Divide the data into training and testing

```scala
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)
```

4.- Specify layers for the neural network: input layer of size 4 (characteristics), two intermediate layers of size 5 and 4, output of size 3 (classes)

```scala
val layers = Array[Int](4, 5, 4, 3)
```

5.- Create the trainer and establish its parameters

```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

6.- Train the model

```scala
val model = trainer.fit(train)
```

7.- Calculate the precision in the test equipment

```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

8.- Print the corresponding test already evaluated

```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```