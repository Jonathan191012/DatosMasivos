//Practica 2

//1.- Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro" Creating a list

var l = List("rojo", "blanco", "negro")

//2.- Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla" Adding elements to list

l :+="verde"
l :+="amarillo"
l :+="azul"
l :+="naranja"
l :+="perla"

//3.- Traer los elementos de "lista" "verde", "amarillo", "azul" Getting elements from list

l(3)
l(4)
l(5)

//4.- Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5 Creating array and adding the number sequence and changing first number to number 1

val x = Array.range(0, 1005, 5)
x(0) = 1
x

//5.- Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos

//Declaring the List lista

val lista = List(1,3,3,4,6,7,3,7)

//Converting list to set of unrepeatable items

lista.toSet
Returns scala.collection.immutable.Set[Int] = Set(1, 6, 7, 3, 4)

//6.- Crea una mapa mutable llamado nombres que contenga los siguiente: "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

//Creating the metable Map with the mentioned elements

val nombres = collection.mutable.Map(("Jose", 20), ("Luis",24), ("Ana", 23), ("Susana", 27))
Returns nombres: scala.collection.mutable.Map[String,Int] = Map(Susana -> 27, Ana -> 23, Luis -> 24, Jose -> 20)

//6.a- Imprime todas la llaves del mapa

//Printing all the keys of the map

nombres.keys
Returns Iterable[String] = Set(Susana, Ana, Luis, Jose)

//6.b- Agrega el siguiente valor al mapa("Miguel", 23)

//Adding the value Miguel <- 23

nombres += ("Miguel" -> 23)
Returns nombres.type = Map(Susana -> 27, Ana -> 23, Miguel -> 23, Luis -> 24, Jose -> 20)