////We import the libraries that we will occupy
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.log4j._

Logger.getLogger("org").setLevel(Level.ERROR)

//We read the csv file, the delimiter will be ; since this is the one that divides the data. The column will be our label, but it has string values so we use StringIndexer to transform this data to numeric. With Labeltransform we make a fit and transform data The Assembler vector is used to join several columns in an arrangement, this was used to have features, for this we grab the columns with numerical values, we transform labeltransform because it already has a label. We show how our new dataset looks
val data  = spark.read.option("header","true").option("inferSchema","true").option("delimiter", ";").format("csv").load("bank-full.csv")

val label = new StringIndexer().setInputCol("y").setOutputCol("label")
val labeltransform = label.fit(data).transform(data)

val assembler = new VectorAssembler().setInputCols (Array ("balance", "day", "duration", "pdays", "previous")).setOutputCol("features")
val data2 = assembler.transform(labeltransform)

val training = data2.select("features", "label")

//We create a split to divide 70% of the data in train and 30% in test
val splits = training.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)
println("training set =",train.count())
println("test set =",test.count())

//To define the layers it must be taken into account that the first number is the number of features, during all the examples 5 columns were used, the next two values are the hidden layers and the last one defines the number of classes, which in this case it is 2, if you don't know how many classes there are, when you run the line of code you get an error message saying how many classes it actually has.
val layers = Array [Int] (5, 3, 3, 2)
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//We create the model with the train fit, the result with the test transformation and create the prediction where prediction and label are selected for the result, evaluator will have the accuracy and finally the accuracy of the prediction is printed
val modelML = trainer.fit(train)
val result = modelML.transform (test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")