//Name: Rojit Ghimire

//Programming Assignment 002

//Due Date: April 10 2020, Dr. Cordovaimport java.util.*;
import java.io.*;
import java.util.*;

public class CourseScheduler
{
   //Global variables number and index
   private int number;
   private int index;
   
   //Creating an arrayList namely courses for the courses and solutionArray to list the solution in.
   List<Course> courses = new ArrayList<Course>();
   List<List<Course>> solutionArray = new ArrayList<List<Course>>();
  
   /*
      A constructor to read in the data from a text file which contains 
      department, number, building, room instructor
      separated by commas.
   */
   public CourseScheduler(String fileName) 
   {
      try
      {  
         File infile = new File(fileName);
         Scanner in = new Scanner(infile);
         number = Integer.parseInt(in.nextLine());
         while(in.hasNextLine())
         {
            Course course = new Course(in.nextLine());
            courses.add(course);
         }
         Slotting();
      }
      catch(FileNotFoundException exception)
      {
         System.out.println("The file was not found.");
      }
   }

   /*
      @param m as a type Integer
      @return true if it is likely to develop a solution with m slots
   */
   boolean solutionExists(int m)
   {
      if(m  % solutionArray.size() != 0)
      {
         return false;
      }
      return true;
   }
   
   /*
      A getSchedule method that results a sample schedule using the backtracking algorithm 
      utilizing proper number of time slots with all the necessary information required
      for a student to schedule a course without any conflicts
      or returns no solution with m slots
      @param m A type integer
   */
   String getSchedule(int m)
   {
      String result = "";
      if(!solutionExists(m))
      {
         result = "No solution with " + m + " slots.";
      }
      else
      {
         for(int i = 0; i < solutionArray.size(); i++)
         {
            List<Course> l = solutionArray.get(i);
            result = result + "\nTime " + (i + 1) +  ":";
            for(int j = 0; j < l.size(); j++)
            { 
               Course b = l.get(j);
               result = result + "\n" + (b.getDepartment() + " " + b.getCourseNumber() + " " + b.getBuilding() + " " + b.getRoom().substring(1) + " " + b.getInstructorName());
            }//inner for loop ends
            
         }//outer for loop ends
      }
      return result;
   }
   
   /*
      @return the number and list of courses reachable from the course given as argument 
      in the form of Department Number as produced by conducting a depth first search
      of the courses graph based on two text files provided.
   */
   String reachable(String from)
   {
      String result = "";
      String building = from.substring(0, 4);
      int courseNumber = Integer.parseInt(from.substring(5));
      
      boolean flag = false; 
      for(int i = 0; i<courses.size();i++)
      {
         if((courses.get(i).getDepartment()+" "+courses.get(i).getCourseNumber()).equals(from))
         {
            flag = true;
         }
      }
      if(!flag)
      {
         return "1 course "+ from;
      }
      
      result = solutionArray.size() + " courses:";
      for(int i = 0; i < solutionArray.size(); i++)
      {
         result = result + solutionArray.get(i).get(0).getDepartment() + " " + solutionArray.get(i).get(0).getCourseNumber() + ", ";
      }
      return result.substring(0, result.length() - 2);
   }
   
   /*
      Adding the course into the solution array on the basis of slot 
   */
   public void Slotting()
   {
      for(int i = 0; i < courses.size(); i++)
      {
         if(promising(i))
         {
            solutionArray.get(index).add(courses.get(i));
         }
         else
         {
            List<Course> l = new ArrayList<Course>();
            l.add(courses.get(i));
            solutionArray.add(l);
         }
      }
   }
      
   /*
      This method prunes the tree and checks if the following nodes can lead to a solution or not
   */
   public boolean promising(int i)
   {
      boolean promise = false;
      if(solutionArray.size() == 0)
      {
         promise = false;;
      }
      
      for(int j = 0; j < solutionArray.size(); j++)
      {
         Course a = courses.get(i);
         if(!contains(solutionArray.get(j), a))
         {
            index = j;
            promise = true;
            break;
         }
      }
      return promise;
   }
   /*
      This method checks if two courses can't be held together.
    */
   public boolean contains(List<Course> l, Course a)
   {
      for(int i = 0; i < l.size(); i++)
      {
         Course b = l.get(i);
         if(Similar(a, b))
         {
            return true;
         }
      }
      return false;
   }
   
   /*
      This method does  a comparison if the two objects can be in the same list or not.
   */
   public boolean Similar(Course a, Course b)
   {
      if(a.getInstructorName().equals(b.getInstructorName()))
      {
         return true;
      }
      if((a.getBuilding().equals(b.getBuilding())) && (a.getRoom().equals(b.getRoom())))
      {
         return true;
      }
      if((a.getDepartment().equals(b.getDepartment())) && (a.getCourseNumber() / 1000 + b.getCourseNumber() / 1000) >= 6)
      {
         return true;
      }
      return false;
   }
}
//END of the program