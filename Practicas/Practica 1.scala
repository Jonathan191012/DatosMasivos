//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo

//Declaring the variable for the diameter

var diameter = 6

//Making a function to calculate radium

def CalculateRad(diameter:Int) = diameter/2

//Calling the function to calculate

CalculateRad(diameter)
Returns 3

//2. Desarrollar un algoritmo en scala que me diga si un numero es primo Declaring the variable for test

var x = 7

//Making a until-loop to verify that the mod is 0 otherwise we will have false value

def isPrime(x: Int) = (2 until x) forall (x % _ != 0)

//Executing the function

isPrime(x)

//3. Dada la variable bird = "tweet", utiliza interpolacion de string para imprimir "Estoy ecribiendo un tweet"

//Declaring the variable tweet

var bird = "tweet"

//Declaring the variable sentence

var sentence = "Estoy escribiendo un"

//Interpolating strings

println(f"$sentence%s $bird")
Returns Estoy escribiendo un tweet

//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la secuencia "Luke"

//Declaring the variable mensaje

var mensaje = "Hola Luke yo soy tu padre!"

//Slicing the message

mensaje.slice(5,9)
Returns Luke

//5. Cual es la diferencia entre value y una variable en scala?

//The value is immutable, it'll never change his value

val value = 3
value = 2
Returns :25: error: reassignment to val

//The variable by the other hand, it actually can change his value

var variable = 3
variable = 2
Returns variable: Int = 2

//6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416

//Declaring the value of tupla

val tupla = (2,4,5,1,2,3,3.1416,23)

//Retrieving the value 3.1416

tupla._7

Returns res1: Double = 3.1416