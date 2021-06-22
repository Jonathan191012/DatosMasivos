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

4.- Creation of dense and sparse vectors, within a matrix

```scala
val data = Seq(
   Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
   Vectors.dense(4.0, 5.0, 0.0, 3.0),
   Vectors.dense(6.0, 7.0, 0.0, 8.0),
   Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
)
```

5.- The data is extracted from the matrix and a dataframe is created

```scala
val df = data.map(Tuple1.apply).toDF("features")
```

6.- Creation of the Pearson correlation matrix using the created dataframe

```scala
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
```

7.- Print result

```scala
println(s"Pearson correlation matrix:\n $coeff1")
```

8.- Creation of the Spearman correlation matrix using the created dataframe

```scala
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
```

9.- Print result

```scala
println(s"Spearman correlation matrix:\n $coeff2")
```

Hypothesis testing

10.- The following library is used to apply methods to vectors

```scala
val df = data.toDF("label", "features")
```

11.- The first values of the previously created dataframe are taken

```scala
val chi = ChiSquareTest.test(df, "features", "label").head
```

12.- Initially with the parts of the test, the values of p will be searched

```scala
println(s"pValues = ${chi.getAs[Vector](0)}")
```

13.- Then the degrees of freedom of the model will be searched

```scala
println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
```

14.- Finally, certain values will be extracted from a final vector, all based on the chi-square function

```scala
println(s"statistics ${chi.getAs[Vector](2)}")
```

//Summarizer

15.- Import of necessary libraries, in this use of vectors and the summarizer itself

```scala
import spark.implicits._    
import Summarizer._
```

16.- A set of vectors or sequence is created

```scala
val data = Seq(
  (Vectors.dense(2.0, 3.0, 5.0), 1.0),
  (Vectors.dense(4.0, 6.0, 7.0), 2.0)
)
```

17.- Creation of the dataframe from the vectors

```scala
val df = data.toDF("features", "weight")
```

18.- The summarizer library is used to obtain the mean and variance of some data in the requested dataframe

```scala
val (meanVal, varianceVal) = df.select(metrics("mean", "variance").summary($"features", $"weight").as("summary")).select("summary.mean", "summary.variance").as[(Vector, Vector)].first()
```

19.- The variables previously worked on are printed

```scala
println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")
```

20.- The process is repeated with 2 new variables

```
val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
  .as[(Vector, Vector)].first()
```

21.- Variable printing

```scala
println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")
```