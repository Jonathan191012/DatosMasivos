//1.-Start a simple Spark session.
spark-shell

import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//2.- Upload Netflix Stock CSV file, have Spark infer the data types.
val df = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

//3.- What are the column names?
df.columns

//4.- How is the scheme?
df.printSchema()

//5.- Print the first 5 columns.
df.select($"Date", $"Open",$"High", $"Low",$"Close").show()

//6.- Use describe () to learn about the DataFrame.
df.describe().show()

//7.- Create a new dataframe with a new column called "HV Ratio" which is the
//relationship between the price of the column "High" versus the column "Volume" of
//shares traded for one day. (Hint: It is a column operation).
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))

//8.- Which day had the highest peak in the “Close” column?
df.select("Date", "Close").sort(desc("Close")).show(1)

//9.- Escribe con tus propias palabras en un comentario de tu codigo. ¿Cuál es el
//significado de la columna Cerrar “Close”? 

//se refiere a lo que cerro las acciones del dia

//10.- ¿Cuál es el máximo y mínimo de la columna “Volume”?
df.select(max("Volume")).show()
df.select(min("Volume")).show()

//11.-Con Sintaxis Scala/Spark $ conteste los siguiente:
//◦ Hint: Basicamente muy parecido a la session de dates, tendran que crear otro
// dataframe para contestar algunos de los incisos
//a.-  ¿Cuántos días fue la columna “Close” inferior a $ 600?
df.filter($"Close"<600).count()

//b.- ¿Qué porcentaje del tiempo fue la columna “High” mayor que $ 500?
(df.filter($"High">500).count()*1.0/df.count())*100

//c.- ¿Cuál es la correlación de Pearson entre columna “High” y la columna “Volumen”?
df.select(corr("High","Volume")).show()

//d.- ¿Cuál es el máximo de la columna “High” por año?
//creas los datos
val yeardf = df.withColumn("Year",year(df("Date")))
val yearmaxs = yeardf.select($"Year",$"High").groupBy("Year").max()
yearmaxs.select($"Year",$"max(High)").show()

//e.- ¿Cuál es el promedio de columna “Close” para cada mes del calendario?
val monthdf = df.withColumn("Month",month(df("Date")))
val monthavgs = monthdf.select($"Month",$"Close").groupBy("Month").mean()
monthavgs.select($"Month",$"avg(Close)").show()