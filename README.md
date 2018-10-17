# Core-Java-Project (HMS)
Core Java Project on Hospital Management System (HMS) using awt ,swings, JDBC and mysql. Happy to get some valuable suggestions regarding the project. :smile:

**Jar file added.**

**Driver class added**

:point_right: To run the project you should have a java compiler, java path set in your system, jdbc driver (driver class) and mysql.
JDBC driver and Javacreator setup is provided in the repository.
Ensure that `classpath` is set for the jdbc-driver-class jar file. Like in my case `classpath` variable is
`C:\Program Files (x86)\MySQL\mysql-connector-java-5.1.12-bin.jar`

:point_right: Firstly create an empty database in your mysql with name **hms**. If you want some other name for the database then you have to change name of the database in file **DConnection.java** also.

:point_right: Images folder should be in the same folder in which all java files are kept.

:point_right: To compile all java files in the same folder.

- Open cmd in that folder in which all java files are kept.
    
- Type **`javac -d . *.java`** (Put space between all four parameters)

>                javac - compiles all java files
>                 -d   - compiled files are send into a folder
>                  .   - current directory
>               *.java - compiling all files together

Done :beers: 

:point_right: **To run the project**:
Suppose your directory name is **hms** which consists of all `.java` files, so after compilation a new directory inside this hms directory will get formed with same name i.e. "hms" and that will contain all your `.class` files. 
Now to run the java program, be in the parent "hms" directory and run command **java hms.FrmSplash** and that will run the project from the Splash screen.

:point_right: If you don't want to run the project starting again with splash screen then uncomment the following in the file **FrmMainFrame.java**  and in place of **kb** type your login username. \
(If **hms.sql** is used to store database in mySQL, then Username is **kb** and Password is **12345678**)

```java           
public static void main(String [] args)
{
	  new FrmMainFrame("kb"); 
}
```
	
Then start running the project by typing **java hms.FrmMainFrame**
