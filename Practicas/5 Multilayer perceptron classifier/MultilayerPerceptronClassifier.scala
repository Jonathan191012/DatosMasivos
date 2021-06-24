//We import the necessary libraries to obtain the perception of the evaluation

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Load the data stored in LIBSVM format as a DataFrame

val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")

//Divide the data into training and testing

val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

//Specify layers for the neural network: input layer of size 4 (characteristics), two intermediate layers of size 5 and 4, output of size 3 (classes)

val layers = Array[Int](4, 5, 4, 3)

//Create the trainer and establish its parameters

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//Train the model

val model = trainer.fit(train)

//Calculate the precision in the test equipment

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//Print the corresponding test already evaluated

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")