## Instituto Tecnológico de Tijuana

**SUBDIRECCIÓN ACADÉMICA**

**DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN**

**MATERIA**
**BDD-1704SC9A – DATOS MASIVOS** 


DOCENTE
MC JOSE CHRISTIAN ROMERO HERNANDEZ


Final Project

ALUMNOS:                        
JONATHAN GONZALEZ ROSALES 17210570

NAVARRO SANCHEZ GERMAN ANDRES 16210278


# Index

## [1.- Introduction](#Introduction)
## [2.- Develoment](#Develoment)
## [3.- Implementation](#Implementation)
## [4.- Results](#Results)
## [5.- Conclusion](#Conclusion)
## [6.- References](#References)



# 1.- Introduction <a name="Introduction"></a>

Machine learning is an application of artificial intelligence (AI) that provides systems the ability to automatically learn and improve from experience without being explicitly programmed. Machine learning focuses on the development of computer programs that can access data and use it to learn for themselves.
The process of learning begins with observations or data, such as examples, direct experience, or instruction, in order to look for patterns in data and make better decisions in the future based on the examples that we provide. 

There are different types of algorithms and these are applicable depending on the type of machine learning in which the algorithm will work. Among them there are the algorithms of: regression, Bayesian, grouping, neural networks, among others. Below we will see some machine learning algorithms and their implementation in a data set.

# 2.- Develoment <a name="Develoment"></a>

### 2.1 Support Vector Machine

Support Vector Machines constitute a learning-based method for solving classification and regression problems. In both cases, this resolution is based on a first training phase (where they are informed with multiple examples already solved, in the form of pairs {problem, solution}) and a second phase of use for problem solving. In it, SVMs become a "black box" that provides an answer (output) to a given problem (input).

The objective of the SVM (support vector machine) algorithm is to find a hyperplane in an N-dimensional space(N - the number of features) that distinctly classifies the data points.


Hyperplanes are decision boundaries that help classify the data points. Data points falling on either side of the hyperplane can be attributed to different classes. Also, the dimension of the hyperplane depends upon the number of features. If the number of input features is 2, then the hyperplane is just a line. If the number of input features is 3, then the hyperplane becomes a two-dimensional plane. It becomes difficult to imagine when the number of features exceeds 3. 

Support vectors are data points that are closer to the hyperplane and influence the position and orientation of the hyperplane. Using these support vectors, we maximize the margin of the classifier. Deleting the support vectors will change the position of the hyperplane. These are the points that help us build our SVM.

### 2.2 Decision Tree

A decision tree is a prediction model whose main objective is inductive learning from observations and logical constructions. They are very similar to rule-based prediction systems, which serve to represent and categorize a series of conditions that occur successively for the solution of a problem. They are probably the most widely used and popular classification model. The knowledge obtained during the inductive learning process is represented by a tree.

A tree is graphically represented by a set of nodes, leaves, and branches. The main or root node is the attribute from which the classification process starts; the inner nodes correspond to each of the questions about the particular attribute of the problem. Each possible answer to the questions is represented by a child node. The branches that come out of each of these nodes are labeled with the possible values of attribute 2. 

The final nodes or leaf nodes correspond to a decision, which coincides with one of the class variables of the problem to be solved. This model is built from the narrative description of a problem, since it provides a graphic vision of decision making, specifying the variables that are evaluated, the actions that must be taken and the order in which the decision making will be carried out. Each time this type of model is executed, only one path will be followed depending on the current value of the evaluated variable. The values that the variables can take for this type of model can be discrete or continuous.

A decision tree is a prediction model whose main objective is inductive learning from observations and logical constructions. They are very similar to the systems of A decision tree generation algorithm consisting of 2 stages: the first corresponds to the induction of the tree and the second to the classification. In the first stage, the decision tree is built from the training set; Usually each internal node of the tree is made up of a test attribute and the portion of the training set present in the node is divided according to the values that this attribute can take. 

Building the tree begins by generating its root node, choosing a test attribute, and dividing the training set into two or more subsets; a new node is generated for each partition and so on. When there are objects of more than one class in a node, an internal node is generated; when it contains objects of only one class, a sheet is formed that is assigned the label of the class. In the second stage of the algorithm each new object is classified by the built tree; then the tree is traversed from the root node to a leaf, from which the membership of the object to some class is determined. The path to follow in the tree is determined by the decisions made at each internal node, according to the test attribute present in it.

### 2.3 Logistic Regression

Logistic regression is a statistical model that in its basic form uses a logistic function to model a binary dependent variable, although many more complex extensions exist. In regression analysis, logistic regression (or logit regression) is estimating the parameters of a logistic model (a form of binary regression).

Mathematically, a binary logistic model has a dependent variable with two possible values, such as pass/fail which is represented by an indicator variable, where the two values are labeled "0" and "1". In the logistic model, the log-odds (the logarithm of the odds) for the value labeled "1" is a linear combination of one or more independent variables ("predictors"); the independent variables can each be a binary variable (two classes, coded by an indicator variable) or a continuous variable (any real value). The corresponding probability of the value labeled "1" can vary between 0 (certainly the value "0") and 1 (certainly the value "1"), hence the labeling; the function that converts log-odds to probability is the logistic function, hence the name.

The unit of measurement for the log-odds scale is called a logit, from logistic unit, hence the alternative names. Analogous models with a different sigmoid function instead of the logistic function can also be used, such as the probit model; the defining characteristic of the logistic model is that increasing one of the independent variables multiplicatively scales the odds of the given outcome at a constant rate, with each independent variable having its own parameter; for a binary dependent variable this generalizes the odds ratio.

### 2.4 Multilayer Perceptron

The perceptron is very useful for classifying data sets that are linearly separable. They run into serious limitations with data sets that do not fit this pattern as discovered with the XOR problem. The XOR problem shows that for any four-point classification there is a set that is not linearly separable.

MultiLayer Perceptron (MLP) breaks this restriction and classifies data sets that are not linearly separable. They do this by using a more robust and complex architecture to learn classification and regression models for difficult data sets.

The multilayer perceptron classifier (MLPC) is a classifier based on the feedforward artificial neural network, which means that data is transmitted from the input layer to the output layer in the forward direction. MLPC consists of multiple layers of nodes. Each layer is fully connected to the next layer in the network. The nodes in the input layer represent the input data. All other nodes map inputs to outputs by linearly combining the inputs with the node weights w and bias b and applying an activation function.

The multilayer perceptron (MLP) is a complement to the forward neural network. It consists of three types of layers: the input layer, the output layer, and the hidden layer. The input layer receives the input signal to be processed. The required task, such as prediction and classification, is performed by the output layer. An arbitrary number of hidden layers that are placed between the input layer and the output layer are the true computational engine of MLP. Similar to a feed-forward network in an MLP, data flows in the forward direction from the input layer to the output layer. Neurons in the MLP are trained with the backpropagation learning algorithm. MLPs are designed to approximate any continuous function and can solve problems that are not linearly separable. The main MLP use cases are pattern classification, recognition, prediction, and approximation.

# 3.- Implementation <a name="Implementation"></a>
 
#### What is Spark?

Spark is an open source platform widely used in the industry for the processing of large volumes of data and execution of intensive computation on them. A framework that offers great value transforming and analyzing relevant data that helps large companies make better business decisions.

This platform contains modules and libraries to better work with the data to be processed. In addition, Spark is multilanguage so it can be programmed in Scala, Python, Java or R.

#### Why use Spark?

Apache Spark is a distributed data processing framework designed to be fast, and is classified as general purpose. It consists of different APIs and modules that allow it to be used by a wide variety of professionals at all stages of the data life cycle.

#### Why code in Spark with Scala?

Scalable language (Scala), is a hybrid language between object-oriented programming and functional programming. Therefore, having the advantages of one and the other, it is a quite functional and practical language.

It has less code to perform some functions compared to other languages. This is useful because you can reduce your code to a minimum and thus read it faster to correct potential problems.

In addition, it is compatible with the Java virtual machine, this means that you can reuse Java libraries in your Scala applications, you will have compatibility with Java code and you can benefit from a consolidated community in the programming scene.


# 4.- Results <a name="Results"></a>

![alt text](Evaluacion/svm.PNG)


![alt text](Evaluacion/DecisionTree.PNG)

**Logistic Regression**

When performing the interactions of this model, it gave us the same results, the only thing that changes is depending on the equipment used to run the model.

```scala
Coefficients:[2.1953717210865443E-5, -0.0039087241148942735,0.0020207318126466336,0.0013950274211932889,0.04274086623441127]
Interceptions: -2.706584067945768
With an efficiency of:
Degree of accuracy = 0.8849252856724289 `88.49%`
```

```scala
Coefficients:[2.1295060967543102E-5, -0.0032248638711286446,0.002004245563577638,0.0014137466827612378,0.0371939132948556]
Interceptions: -2.695580402935833
With an efficiency of:
Degree of accuracy = 0.8886545454545455 `88.87%`
```

**Multilayer Perceptron**

This only gave us the percentage of precision, during the first tests the answer was

```scala
0.8835474819081377 `` (88.35%) ``
while in the other tests it was
0.8829225352112676 `` (88.29%) `
```

# 5.- Conclusion <a name="Conclusion"></a>

When visualizing the comparisons that were made to the algorithms, we think that the results are very similar and that it is better to use the algorithm with which you are most familiar. In the same way, each algorithm can work better according to the situation that arises. But this practice served to see which is more accurate, what is its margin of error, among other things.

Based on the results obtained with the runs of each of the algorithms, it can be seen that the one that consumed the least memory was the Decision Tree algorithm, however it was the longest to execute, since it took almost twice as long as the others algorithms.

# 6.- References <a name="References"></a>

Marketing KeepCoding. (2020, 15 julio). ¿Cómo usar Spark con Scala para Big Data? KeepCoding. https://keepcoding.io/blog/usar-spark-con-scala/

Multilayer Neural Networks. Multilayer Perceptron. Retrieved from https://www.inf.utfsm.cl/~hallende/bajadas/Redes/redes%20de%20Multicapa.pdf

S. Abirami, P. Chitra. (2020). Multilayer Perceptron. junio 23, 2020, de sciencedirect Sitio web: https://www.sciencedirect.com/topics/computer-science/multilayer-perceptron

What is Logistic Regression? - Statistics Solutions. (2021). Retrieved 23 June 2021, from https://www.statisticssolutions.com/free-resources/directory-of-statistical-analyses/what-is-logistic-regression/