/*
   First Programming Assignment
   @Author: Rojit Ghimire
*/

import java.util.Scanner; 

// the program begins 
public class Jewel 
{
   // name, weight and value are the global variables in Jewel class.
   private String name;
   private int weight;
   private int value;
   
   public Jewel(String item)
   {
      Scanner in  = new Scanner(item);
      in.useDelimiter(" ");
      
      while (in.hasNext()) 
      {
         String name = in.next();
         int weight = Integer.parseInt(in.next());//To convert the weight and value into integers
         int value = Integer.parseInt(in.next());
         
         
         setName(name);
         setWeight(weight);
         setValue(value);
      }
      }// end of while loop
      
      /*
         String and two integers methods are used to return the name, weight, and value of items.
      */
      public String getName() {
         return name;
      }
      
      public int getWeight() {
         return weight;
      }
   
      public int getValue() {
         return value;
      }
      
      /* Settin the name, weight, value to n,w, and v respectively. */
      public void setName(String n) {
         name = n;
      }
      
      public void setWeight(int w) {
         weight = w;
      }
   
      public void setValue(int v) {
         value = v;
      }
}
/*** THE END ***/ 