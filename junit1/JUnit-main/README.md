# CS 371 Assignment: Junit

========================

This assignment involves using JUnit to perform automated
unit testing on some classes.

JUnit is available at www.junit.org.

This assignment is set up to use JUnit 5, so follow the instructions to 
get the latest JUnit 5 standalone jar file named 

   junit-platform-console-standalone-1.11.4.jar

Choose the "Platform" download button under the "Latest Release" block
title on the right hand side of the JUnit home page. Then scroll down to 
find the "console-standalone" version, and click through, then select the 
Versions tab, and then "browse". You only need the compiled Jar file, you
do not need the sources or the javadoc Jar files.

You can find many resources and tutorials on the web that describe JUnit,
but many might be for JUnit4 or for JUnit5 packaged into IDEs or other
testing frameworks. So be careful. 

It would be a good learning exercise to install JUnit 5 into your 
favorite IDE, and use it there, but you MUST submit (check in to the
repo) a folder that properly uses the standalone version. 

This assignment directory includes a build.xml file for using Ant. 
If you don't have Ant installed on your system, install it.

DO NOT submit the JUnit jar files as part of your assignment! I do not 
want 40 copies of JUnit jarfiles in the repository! Place them elsewhere 
and then set the path in the build.xml file. You can see in the build
file an example path to my JUnit jarfile.

The build file is set up so that the command "ant" will only compile the
code. To do the other actions (run, junit, clean), you have to do 
"ant <action>". If you have set up everything correctly, the command
"ant junit" will show JUnit output and show that the two existing 
tests were executed and passed (green checkmarks).

As part of this assignment you must also generate the Javadoc files in a 
subdirectory named "apidoc" and it must include documentation for all class 
members, including private members (you need to figure out how to do these 
things; it's easy). You should commit the entire "apidoc" directory into
your assignment repo; normally this would be a bad idea since these files
can be re-generated, but for this assignment we are making an exception.
You SHOULD DO THIS BEFORE working on the testing part, because the API
documentation will help you!

Your main task for this assignment is to create unit tests which accomplish 
certain goals, mainly ensuring good testing coverage of the classes under 
test, for one particular type of testing.

You have three classes to test: an abstract class Circle and two 
subclasses, Circle1 and Circle2. The requirements for all three are in 
Javadoc-able comments in the Circle class. All three classes may have 
errors; as you encounter errors, you MUST document the error, including
which test found the error, and then you must fix the error. There is also
a "CircleRun" class which demonstrates some example execution, but your 
unit tests using JUnit will exercise an object (or more) of the class(es) 
directly, and so will not use the CircleRun class. You do not have to test 
this class, it is there just so that you can run the classes in a 
standalone matter to see what they do; the "ant run" build target runs
this class.

When creating test cases you should not have "redundant" test cases which 
do not exercise anything new. Having more tests than you need will REDUCE 
your grade, not enhance it! 

You must write a comment header for each test case that succinctly describes 
that test case. Just write a short description -- a long verbose description 
is no good! You must also name your JUnit test case methods with a name 
that reflects the purpose of the test -- do not be afraid of long test
method names!

This assignment: Black box testing
==================================

Black box testing aims to test the code according to its requirements, 
without looking into the code and seeing how things are implemented.
The code is treated as a "black box". In the case of testing an individual 
class, only its public API is considered for black box testing.

Suggested reading:

textbook (Sethi), section 9.4 (pp238-241)
http://en.wikipedia.org/wiki/Black_box_testing
http://en.wikipedia.org/wiki/Equivalence_Partitioning
http://en.wikipedia.org/wiki/Boundary-value_analysis

Use the ideas from equivalence partitioning and boundary value analysis to 
create JUnit test cases which fully test the three classes under black box 
testing. The JUnit test classes must be named "Circle1Test" and 
"Circle2Test". Note that since this is black box testing and both concrete 
classes under test should implement the same requirements, your two test 
classes should be exactly the same except for which class they reference 
(Circle1 and Circle2).

Submitting the assignment
=========================

To submit this assignment, commit into your personal GitHub repository, 
under a directory named "junit1", the following material:

1. Your java source files for Circle1Test and Circle2Test including all the
   test cases you created (they should contain the same exact tests).
2. The possibly updated Java source files for Circle, Circle1, and Circle2,
   and your build.xml file.
3. The documentation generated by Javadoc (as described above; normally it
   is not appropriate to commit auto-generated files into a repository, but
   this assignment is an exception.)
4. Your plain text file that documents any errors you found, which test cases
   found them, and how you fixed them.

DO NOT commit any .class files, nor any libraries, and not the JUnit jarfile.
