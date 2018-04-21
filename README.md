# Core-Java-Project (HMS)
Just started to work on Hospital Management System (HMS) using awt ,swings, JDBC and mysql. Happy to get some valuable suggestions regarding the project. :) 

**Jar file added.**

**Driver class added**

1. To run the project you should have a java compiler, java path set in your system, jdbc drivers (driver class) and mysql.

2. Firstly create an empty database in your mysql with name **hms**. If you want some other name for the database then you have to change name of the database in file **DConnection.java"**.

3. Images folder should be in the same folder in which all java files are kept.

4. To compile all java files in the same folder.

    a. Open cmd in that folder in which all java files are kept.
    
    b. type `javac -d . *.java` (Put space between all four parameters)
    
                `javac - compiles all java files`
                 `-d   - compiled files are send into a folder`
                 `.    - current directory`
               `*.java - compiling all files together.` 
         
        
5. Done. 

6. **To run the project**:
Suppose your directory name is **hms** which consists of all `.java` files, so after compilation a new directory inside this hms directory will get formed with same name i.e. "hms" and that will contain all your `.class` files. 
Now to run the java program, be in the parent "hms" directory and run command **java hms.FrmSplash** and that will run the project from the Splash screen.

7. If you don't want to run the project starting again with splash screen then uncomment the following in the file **FrmMainFrame.java**  and in place of **abhinav** type your login username.
	```             

			public static void main(String [] args)
                	{
		                  new FrmMainFrame("abhinav"); 
	                }
   	```
   Then start running the project by typing **java hms.FrmMainFrame**
