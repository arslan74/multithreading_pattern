# Multithreading Pattern


Design and implement a Java application with producer/consumer pattern. The producer(s) and consumer(s) can run in the same JVM. You can keep data objects in-memory (no persistence layer is needed). You can define the data types and structures yourself. No UI or GUI is needed.

This project demonstrate my knowlege and undersatnding with java programming language and java eco system. Due to limited time there could be some logical or semantic error that can be improved.

## How to run the project?

I have the Intellij as IDE, due to it's simplisty and suppoting different programming and build automation tools.
I would be best to import the project using Intellij and add build configuration for the  project.

There are other ways to use makefile for the automation of the build. But due to limited time, I used IntelliJ IDEA build configuration for setting up the project because main focus was to solve the problem at hand.

## How to start the project?

**MainDrive** file has main function of project that start the application.

## Structure of project

There are five packages for the structuring  of code.

* controller
  * This package have  main class **MainDriver**, which handles different class from other pakages and create the final logic of the application.
  * Also this package have class, which is the starting point of the program.
  * But it is not just limited to one class, it can connect data_models, design_pattern class.
  * **MainDriver** class creates the producer and cosumer Threads. It intialize the buffer for the application. Data structure used for buffer is Queue.

* data_models
  * Main purpose of this package is solely for the purpose of data classes or classed that represent or view holder for the data, that can be read from file and Apis.
  * In the project there is Topic class in the package that represnt a Topic, which holds the id, keyVale and timestand fields.
* design_pattern
  * Purpose of this package is to hold the classes **Consumer** and **Producer**.
  * These classes hold the mian logic of the project.
  * Both of the classes are Thread and These classes are begin called from another thread which is the main thread of application in *MainDriver* class.  
  * Producer publish the new toipc in to the buffer(Queue) and also checks the limit to create new topics. Consumer can process/consume the topic in the buffer and then that topc can be removed from the buffer and new topic can be added.
  * Both producer and consumer are using a synchronized buffer so prevent the any threads commuincation problem or data leakage(loss of data) problem.
  * Time Redentation also helps to keep the data in buffer for the limited time.
  * Buffering helps to make use of concurrent programming here. So one thread can't just use all the resources.
* exceptions
  * This package have two classes, these classes are user level exception. Purpose of these classes is to share more detailed information when exception is thrown.  
* utils
  * This package is for the helper funtions and constant values for for configuration or things that can be used by any class in the project.  

## Test case

There are total four unit test case included in the project. Almost all part of the project are testing. Testing is used to for simple use cases for the data strcture used in project, for the consumer and producer part of the code.
To run the test case, best way is to run them using the intellij Idea


### Java version and Maven version

Java  >= 8

Mave >= 3

JavaFx framework is used for project
