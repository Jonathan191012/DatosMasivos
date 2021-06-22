# Practice 1 Basic Statistics

**Correlation**

Calculating the correlation between two data series is a common operation in statistics. In Spark they offer the flexibility of calculating piecewise correlations between many series. Currently supported correlation methods are Pearson and Spearman correlation.

1.- Importing the library for matrices and vectors

```scala
import org.apache.spark.ml.linalg.{Matrix, Vectors}
```

2.- Importing the correlation library

```scala
import org.apache.spark.ml.stat.Correlation
```

3.- It allows access to a value in a row through generic access by ordinal, as well as primitive access.

```scala
import org.apache.spark.sql.Row
```

4.- Session import and creation

```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder.appName("CorrelationExample").getOrCreate()
```

5.- Importing the Implicit Conversion to Convert RDDs to DataFrames

```scala
import spark.implicits._
```


6.- Creation of dense and sparse vectors, within a matrix

```scala
val data = Seq(
   Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
   Vectors.dense(4.0, 5.0, 0.0, 3.0),
   Vectors.dense(6.0, 7.0, 0.0, 8.0),
   Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
)
```

7.- The data is extracted from the matrix and a dataframe is created

```scala
val df = data.map(Tuple1.apply).toDF("features")
```

8.- Creation of the Pearson correlation matrix using the created dataframe

```scala
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
```

9.- Print result

```scala
println(s"Pearson correlation matrix:\n $coeff1")
```

10.- Creation of the Spearman correlation matrix using the created dataframe

```scala
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
```

11.- Print result

```scala
println(s"Spearman correlation matrix:\n $coeff2")
```