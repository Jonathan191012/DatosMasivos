//We import the libraries that we will occupy
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.log4j._

//The library is loaded to minimize errors within the code run We create our spark variable to start a normal session We read the csv file, the delimiter will be ; since this is the one that divides the data.
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()
val data  = spark.read.option("header","true").option("inferSchema","true").option("delimiter", ";").format("csv").load("bank-full.csv")

//The y column will be our label, but it has string values so we use StringIndexer to transform this data to numeric. With Labeltransform we make a fit and transform data
val label = new StringIndexer().setInputCol("y").setOutputCol("label")
val labeltransform = label.fit(data).transform(data)

//The Assembler vector is used to join several columns in an arrangement, this was used to have features, for this we grab the columns with numerical values, we transform labeltransform because it already has a label. We show how our new dataset looks
val assembler = new VectorAssembler().setInputCols (Array ("balance", "day", "duration", "pdays", "previous")).setOutputCol("features")
val data2 = assembler.transform(labeltransform)
data2.show(1)

//We create a training variable that only contains features and label. We show the new clean data set.
val training = data2.select("features", "label")
training.show(1)

//We create a split to divide 70% of the data in train and 30% in test We print the account of our values for training and test
val splits = training.randomSplit(Array(0.7, 0.3), seed = 12345)
val train = splits(0)
val test = splits(1)
println("training set = ", train.count())
println("test set = ", test.count())

//I create an lr object which is the LogisticRegression argument for our model We create a variable which the model uses, it performs the fit with the train dataset after loading with the train, the model is used now transforming the data from the test data. To evaluate the effectiveness of our model we create an object which will give us this value
val lr = new  LogisticRegression().setMaxIter(10).setRegParam(0.1)
val model = lr.fit(train)
val resultados = model.transform(test)
val evaluador = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//We print our coefficients and interceptions based on the degree of accuracy of our model that we carry out.
println(s"Coeficientes: ${model.coefficients}")
println(s"Intercepciones: ${model.intercept}")
println(s"Grado de exactitud = ${evaluador.evaluate(resultados)}")
