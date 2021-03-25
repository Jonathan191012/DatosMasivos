# Practice 3 Fibonacci serie

1.- Downstream Recursive Version

Declaring the variable n

```scala

var n: int = 7

Fibonacci Version 1:

def fib1(n:int): int = {
  if(n<2){
    return n
    } 
    
    else{
      return fib1(n-1) + fib1(n-2)
    }
  }
```

As it is a function we need to call it and pass the variable to it (this will happen in odd algorithms; the same for return):

```scala

fib1(number)
Returns: int = 13

```

2.- Explicit Formula Version
This algorithm uses the explicit formula for the fibonacci series, this formula is not as accurate as when using the conventional methods like cycles or recursion.

```scala

def fib2(n:Double): Double = 
	var p: Double = ((1 + scala.math.sqrt(5)) / 2)
	var j: Double = ((scala.math.pow(p, n) - scala.math.pow((1 - p), n)) / scala.math.sqrt(5))

	if(n < 2)
	{
		n
	}
	else
	{
		j
	}
}

```

3.- Iterative Version
A for loop is created, the loop will do 3 things: first it will add (b + a) to the variable c, then it will give the value of b to the variable a and finally it will assign the value of c to the variable b. In the end it will return the result.

```scala

Fibonacci Version 3:

def fib3(n:int): int = {
  var a = 0
  var b = 1

  for(k <- 0 to n-1){
    var c = b + a
    a = b
    b = c
  }

return a

}

```

4.- Iterative Version: 2 variables
We create a while loop, the loop will do if the value of k is less than n then the variable b will be assigned the value of the sum of b + a, the variable will be assigned the subtraction of ba and the variable k will increase + 1. In the end it will return the result.

```scala

def fib4(n:int) : int = {
	var a = 0
	var b = 1
	var k = 0

	while(k < n)
	{
		b = b + a
		a = b - a

		k = k + 1 
	}

	return a
}

```

5.- Iterative Version: Vector
The cycle will be assigned to the vector in the current position (k), the vector will be assigned to the position k, the resulting sum from the last and penultimate position, will finally return the calculated result.

```scala

Fibonacci Version 5:

def fib5(n:int): int = {
  if(n<2) {
    return n
    } 
    
    else {
      var vec = new Array[Int](n+1)
      vec(1) = 1
      for(k <- 2 to n){
        var aux = vec(k-1) + vec(k-2)
        vec(k) = aux
      }

      return vec(n)
    }
  }

```

6.- Divide and Overcome Version

```scala

def fib6(n :int):Double={
    if(n<=0){
        return 0
    }

    var i = n-1
    var aux1 =0.0
    var aux2=1.0

    var ab=(aux2,aux1)
    var cd=(aux1,aux2)

    while(i>0){
        if((i%2)!=0){
            aux1=(cd._2*ab._2)+(cd._1*ab._1)
            aux2=(cd._2*(ab._2+ab._1)+cd._1*ab._2)
            ab=(aux1,aux2)
        }

        aux1=pow(cd._1,2)+pow(cd._2,2)
        aux2=(cd._2*(2*cd._1+cd._2))
        cd=(aux1,aux2)
        i=i/2
    }
    return (ab._1+ab._2)

}
fib6(10)
```
