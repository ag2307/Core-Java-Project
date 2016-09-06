# Core-Java-Project (HMS)
Just started to work on Hospital Management System (HMS) using awt ,swings, JDBC and mysql. Happy to get some valuable suggestions regarding the project. :) 
**Jar file added.**
**Driver class added**

1. To run the project you should have a java compiler, java path set in your system, jdbc drivers (driver class) and mysql.

2. Images folder should be in the same folder in which all java files are kept.

3. To compile all java files in the same folder.

    a. Open cmd in that folder in which all java files are kept.
    
    b. type "javac -d . *.java" (Put space between all four parameters)
    
          (      javac - compiles all java files
                  -d   - compiled files are send into a folder
                  .    - current directory
                *.java - compiling all files together  )

8. Done. 

9. To run the project run FrmSplash.java.

10. If you don't want to run the project starting again with splash screen then uncomment the following in 
    
     FrmMainFrame.java and replace "kb" with your username.
               
						public static void main(String [] args)
                	{
		                  new FrmMainFrame("kb"); //kb is your username so replace it accordingly
	                }
   
   Then start running the project from FrmMainFrame.java.
