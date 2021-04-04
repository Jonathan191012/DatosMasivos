## Practica 2

1. Create a list called "lista" with the elements "rojo", "blanco", "negro"

``` scala
import scala.collection.mutable.ListBuffer
var lista = collection.mutable.ListBuffer("rojo", "blanco", "negro")
```

2. Add 5 more elements to "lista" "verde" ,"amarillo", "azul", "naranja", "perla"

``` scala
lista +="verde"
lista +="amarillo"
lista +="azul"
lista +="naranja"
lista +="perla"

println(lista)
```

3. Bring the elements of "lista" "verde", "amarillo", "azul"

``` scala
lista slice(3,6)
```

4. Create an array of numbers in the range 1-1000 in steps of 5 by 5

``` scala
Array.range(0, 1000, 5)
```

5.- What are the unique elements of the list Lista(1,3,3,4,6,7,3,7) use conversion to sets

``` scala
val lista2 = Set(1,3,3,4,6,7,3,7)
val lista2 = collection.mutable.Set(1,3,4,6,7)
```

6.- Create a mutable map named nombres that contains the following "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

``` scala
val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))
```

6 a.- Print all keys on the map

``` scala
nombres.keys
```

7 b.- Add the following value to the map ("Miguel", 23)
``` scala
nombres += ("Miguel" -> 23)
```
