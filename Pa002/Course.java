//Name: Rojit Ghimire

//Programming Assignment 002

//Due Date: April 10 2020, Dr. Cordova


import java.util.*;

public class Course
{

/**
   Use of Global Variables
   to represent the department,courseNumber of the course
   building, room where the class will take place
   and also the name of instructor and number of slots. 
**/
   String department;
   int courseNumber;
   String building;
   String room;
   String instructorName;
   int slot = 1;
   
   /**
      Initializing the variables
   **/
   public Course()
   {
      department = "";
      courseNumber = 0;
      building = "";
      room = "";
      instructorName = "";
   }
   
   /*
      * The text files that were provided to us had the department,coursenumber, building, room, instructor in a single line.
      * This method checks if the Scanner has another token in its input.
      * Scanner method breaks its input into tokens using a delimiter pattern, which matches whitespace by default. 
   */
   public Course(String line)
   {
      Scanner in = new Scanner(line);
      in.useDelimiter(",");
      
      while(in.hasNext())
      {
         department = in.next();
         courseNumber = Integer.parseInt(in.next());
         building = in.next();
         room = in.next();
         instructorName = in.next();
      }//marks the end of while loop
   }
   
   /*
      @return the department
   */
   public String getDepartment()
   {
      return department;
   }
   /*
      @return the course number
   */
   public int getCourseNumber()
   {
      return courseNumber;
   }
   /*
      @return the building in which the course will take place
   */
   
   public String getBuilding()
   {
      return building;
   }
   /*
      return the room in that building
   */
   public String getRoom()
   {
      return room;
   }
   /*
      @return the name of the instructor
   */
   public String getInstructorName()
   {
      return instructorName;
   }
   /* 
      This method sets the slot
      @param s A variable of type Integer
      @return Nothing
   */
   public void setSlot(int s)
   {
      slot = s;
   }
   /*
      @return the slot
   */
   public int getSlot()
   {
      return slot;
   }
}
//END of the class Course