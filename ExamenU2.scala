// Agregamos las librerías necesarias para trabajar con el algoritmo multilayer perceptron
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier

// 1.- Cargar en un dataframe Iris.csv que se encuentra en
//https://github.com/jcromerohdz/iris, elaborar la liempieza de datos necesaria para ser
//procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por
//medio de un script de Scala en Spark) .

import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors

// Creamos una variable data y asignamos como dataframe los datos del archivo iris.csv
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")

// 2. ¿Cuáles son los nombres de las columnas?
data.columns

// 3. ¿Cómo es el esquema?
data.printSchema()

// 4. Imprime las primeras 5 columnas.
data.show(5)

//5. Usa el metodo describe () para aprender mas sobre los datos del DataFrame.
data.describe().show()

// Eliminamos las campos nulos
val dataClean = data.na.drop()

//Se declara un vector que transforma los datos a la variable "características" n 
//vector que se transforma los datos a la variable "características"
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width", "petal_length","petal_width")).setOutputCol("features"))

//6. Haga la transformación pertinente para los datos categoricos los cuales serán 
//nuestras etiquetas a clasificar.
val features = vectorFeatures.transform(dataClean)

// Declaramos un "StringIndexer" que transformará los datos en valores numéricos de "especie"
val speciesIndexer = new StringIndexer().setInputCol("species").setOutputCol("label")

// Ajustamos las especies indexadas con las características del vector.
val dataIndexed = speciesIndexer.fit(features).transform(features)

// Con la variable "splits" hacemos un corte aleatorio
val splits = dataIndexed.randomSplit(Array(0.6, 0.4), seed = 1234L)

// Se declara la variable "train" que tendrá el 60% de los datos
val train = splits(0)

// Se declara la variable "test" que tendrá el 40% de los datos
val test = splits(1)

// 7.- Construya el modelo de clasificación y explique su arquitectura.
val layers = Array[Int](4, 2, 2, 3)

// El entrenador de algoritmos multicapa se configura con sus respectivos parámetros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// El modelo se entrena con los datos de trainer.
val model = trainer.fit(train)

// El modelo está probado y ya entrenado.
val result = model.transform(test)

// Se seleccionan la predicción y la etiqueta que se guardará en la variable
val predictionAndLabels = result.select("prediction", "label")

// Se muestran algunos datos
predictionAndLabels.show()

// Se ejecuta la estimación de precisión del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictionAndLabels)

// 8.- Imprima los resultados del modelo
println(s"Test Error = ${(1.0 - accuracy)}")