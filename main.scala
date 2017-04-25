/**
  * Created by Administrator on 25/04/2017.
  */

  //pattern matching across a collection
val weekDays = List("Mon","Tue","Wed","Thur","Fri");
val jokeDays = for (day <- weekDays) yield{
  day match {
    case "Mon" => ("Monday is a joke");
    case "Tue" => ("Tuesday is a joke");
    case otherday => (otherday + " is not a joke");     //alternate method is to use the _ wildcard
  }
}

println(jokeDays)


//storing a function in a variable
val compareStrings =
  (s1: String, s2: String) => {
    if(s1 == s2) "Strings are equal"
    else if (s1 > s2) "First string greater"
    else "Second string greater"
  } : String

println(compareStrings("wew","lad"))


//functions returning functions (functionception)
def addValues(a:Int,b:Int):Int = {
  a+b
}

def subtractValues(a:Int,b:Int):Int ={
  a-b
}

def chooseOperator(operator:String): (Int,Int) => Int = {
  if(operator == "ADD") addValues
  else subtractValues
}

println(chooseOperator("ADD")(5,6))
println(chooseOperator("NOT ADD")(10,2))

//string interpolation
val name = "Dwayne"
val greeting = "Hello"

println(s"$greeting $name, Nice to see you")

println(s"${greeting*2} $name, Nice to see you")

//example of the nothing type - ()

println(if (1 == 0) {"Something"})

//exmaple of case casting

def typeOfValue(v:Any):String = v match{
  case v:Int => "It's an Integer"
  case v:String => "It's a String"
  case v:Char => "It's a Char"
  case _ => "It's Some other type"
}

println(typeOfValue(1))
println(typeOfValue("<><"))
println(typeOfValue('l'))
println(typeOfValue(2.891))


//converting a method to a function

def addNums(x:Int,y:Int):Int = x+y

val numFunction = addNums(_,_)    //storing the addNums method as a function object with _ placeholders
val numFunction2: (Int,Int) => Int = addNums    //storing the addNums method as a function object by assigning the method to the defined function

println(numFunction(1,2)) //using the stored functions
println(numFunction2(3,4))

//using nested functions

def getCircleStats(r:Double) = {
  val PI = 3.14
  def getCircleArea(r:Double) = PI * r * r
  def getCircleCirc(r:Double) = 2 * PI * r

  (getCircleArea(r),getCircleCirc(r))
}

println(getCircleStats(5))

//using default parameters

def addNums2(a:Int = 5, b:Int) = {
  a+b
}

println(addNums2(b=11))

//Type parameter example
def getTypes[K,V](k:K,v:V) = {
  val kType = k.getClass
  val vType = v.getClass
  (kType,vType)
}

println(getTypes(1,"Words"))


//curried function
def doMaths(numFunc:(Int,Int) => Int)(n1:Int,n2:Int)= //parameter groups
{
  numFunc(n1,n2)
}

def addNumbers(a:Int,b:Int): Int ={
  a+b
}

val defaultFunc = (addNumbers)(_:Int,_:Int)    //we have partially specified an entire group

println(defaultFunc(5,5))


//functions by-name as parameters

def checkName(nm:String): String={
  println("Checking name...")
  nm
}

def sayHello(nm:String): Unit ={
  println(s"Hello $nm")
  println(s"Hello again $nm")
}

def sayHelloTo(nm: => String): Unit ={
  println(s"Hello $nm")
  println(s"Hello again $nm")
}

sayHello(checkName("Dwayne"))         //the function parameter checkName() is executed only once
sayHelloTo(checkName("Dwayne"))       //the function parameter checkName() is executed very time it is referenced

//local scope example

val words = "Global"

def printWords(): Unit = {
  val words = "Local"
  println(words)          //the function uses the local words variable over the globaly defined words variable
}

printWords()

//Using a tuple

val tupi = (1,"Memes", 5, 8.8)      //making a tuple

tupi.productIterator.foreach(i => println(i))     //iterating over a tuple

val tupi2 = ("There","Is","No","Spoon")
def printFourThings(a:Any,b:Any,c:Any,d:Any): Unit = {
  println(a)
  println(b)
  println(c)
  println(d)
}

(printFourThings _).tupled(tupi2)          //unpacking a tuple to use each element inside it as paramters to the function

//using lists

val fishList = "<><" :: "{}{" :: "<^><" :: Nil    //building a list with the cons :: operator
val smileyList = List(":)", ":(", ":D", ";^)")           //building a list with the list() function
val combinedList = fishList ::: smileyList        //building a list from 2 other lists

println(fishList)
println(smileyList)
println(combinedList)

val numList = List(1,2,3,4)
println(numList zip smileyList)           //Create a list of tuples by associating each value in 1 list to the values in another list, forming a list of tuple pairs

println(numList.forall(_ < 5))         //run a comparator against all values in the list
numList.foreach(i => println(i + 2))    //run a function on each individual value in the list


//using maps

val starbucksPrices = Map(("Small","Way too much"),("Medium","an absurdly high amount"),("Bucket of","Your life savings"))

starbucksPrices.foreach((p:(String,String)) => println("A " + p._1 + " coffee costs " + p._2))     //applying a function to each element in the map

val list1 = List(1,2,3,4)
val list2 = List('A','B','C','D')

println((list1 zip list2).toMap)      //build a map from 2 lists


//using an option

def fractionable(num:Double,denom:Double): Option[Double] = {
  if (denom == 0)
    None
  else
    Option(num/denom)
}

println(fractionable(5,0) getOrElse "Cannot divide by zero")      //using option for error checking, the error message will be returned if the function was founf trying to divide by zero

//filtering a list

val someList = List(5,6,4,2,7,8,9,1,10,3)
val lessThanFive = (x:Int) => {x < 5}:Boolean

println(someList.filter(lessThanFive))      //filter the values in the list to only get values less than 5
println(someList.partition(lessThanFive))   //separate the list into 2 seperate lists, one with numbers < 5, and one with numbers > 5
println(someList.sortBy(_ % 2 == 0))        //sort the list into an order, separating odd and even numbers

val thisList = List(10,20,30,40,50,60)
println(thisList.scanRight(0)(_ - _))       //running a scanRight on the list of numbers, which creates a list of numbers from a right to left operation of subtraction
println(thisList.scanLeft(0)(_ - _))        //running a scanLeft on the list of numbers, which creates a list of numbers from a left to right operation of subtraction

println(thisList.foldLeft(0)(_ - _))        //gives the final value of the scan left function
println(thisList.foldRight(0)(_ - _))       //gives the final value of the scan right function

println(thisList.reduceRight(_ - _))        //gives the final value of the scanright function, but with no initial value
println(thisList.reduceLeft(_ - _))         //gives the final value of the scanleft function, but with no initial value

