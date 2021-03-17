1. Desarrollar un algoritmo en scala que calcule el radio de un circulo

```scala
    val P = 17.2788
    val R = P/6.28
```

2. Desarrollar un algoritmo en scala que me diga si un numero es primo

```scala
def isPrime(num:Int): Boolean = {
    for(n <- Range(2, num)){
        if(num%n == 0){
            return false
        }
    }
    return true
}


println(isPrime(10))
```

3. Dada la variable bird = "tweet", utiliza interpolacion de string para imprimir "Estoy ecribiendo un tweet"

```scala
val x = "Estoy escribiendo un tweet"
val bird = "tweet"

if(bird == "tweet"){
    println(s"$x")
}
```

4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la secuencia "Luke"

```scala
val saludo = ("Hola Luke yo soy tu padre!")
saludo slice (5,9)
```

5. Cual es la diferencia entre value y una variable en scala?

- value no se puede modificar por lo tanto es inmutable; en cambio variable puede ser modificado 

6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416 

```scala
val tuplaPi = (2,4,5,1,2,3,3.1416,23)
println(tuplaPi._7)
```

