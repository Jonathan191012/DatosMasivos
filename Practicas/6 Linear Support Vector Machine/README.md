# Practice 6 Linear Support Vector Machine

1.- We import the "LinearSVC" library, this binary classifier optimizes the hinge loss using the OWLQN optimizer.

```scala
import org.apache.spark.ml.classification.LinearSVC
```

2.- We import and create the session in spark.

```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder.appName("LinearSVCExample").getOrCreate()
```

3.- We load the training data.

```scala
val training = spark.read.format("libsvm").load("/Archivos/sample_libsvm_data.txt")
```

4.- We establish the maximum number of iterations and the regularization parameter.

```scala
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```

5.- We make a fit to adjust the model.

```scala
val lsvcModel = lsvc.fit(training)
```

6.- Print the coefficients and intercept for the Linear SVC.

```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```