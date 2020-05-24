/*
   First programming Assignment 
   author: Rojit Ghimire
   
   DPKnapsack class has the necessary methods which help in the implementing dynamic
   program solution to the KnapSack problem.

*/
import java.io.*; 
import java.util.Scanner; 
import java.util.*;

public class DPKnapsack 
{
   /* 
      use of Global Variables */
   private int capacity;
   private String itemFile;
   
   // an integer array and a string set named solutionJewels.
   int[][] array;
   Set<String> solutionJewels;
   
   /*   Optimised the overall weight of items and the number of items in 
   the optimal solution subset to be zero.   */
   int optimalWeight = 0;
   int optimalNumber = 0;
   
   // Creation of objects and also initializing the integer counter variable to be zero //
   Jewel[] jewels = new Jewel[50];
   int[] weight = new int[50];
   int[] value = new int[50];
   String[] name = new String[50];
   int counter = 0;

   /*
      DPKnapsack is a constructor to accept the default capacity and the name of data files
      as arguments. The file that was provided had several lines in it with name, weight &
      value of the items respectively.
   */
   public DPKnapsack(int c, String itemFile) 
   {
      capacity = c;
      itemFile = itemFile;
      
      //Use of try and catch, catch throws an exception if file is not found
      try{
         File file = new File(itemFile); 
         Scanner in  = new Scanner(file);
      
         while(in.hasNextLine()){
            counter += 1;
            String item = in.nextLine();
            Jewel jewel = new Jewel(item);
            jewels[counter] = jewel;
            name[counter] = jewels[counter].getName();
            weight[counter] = jewels[counter].getWeight();
            value[counter] = jewels[counter].getValue();
         }
         
         createTable(counter, capacity);
      }
      
      catch(FileNotFoundException s) 
      {
         System.out.println("File Not Found!");
      }
      
   }  
   
   public void createTable(int counter, int capacity) {
      /*
         Use of array and solutionJewels that was in the beginning of program 
         the variables counter and capacity are the parameters in this method
         The major method of the program. 
      */ 
      array = new int[counter + 1][capacity+1];
      solutionJewels = new HashSet<>();
   
      for (int i = 0; i <= counter; i++) {
         for (int j = 0; j <= capacity; j++) {
            if (i == 0 || j == 0) {
               array[i][j] = 0;
            } else if (weight[i] <= j ) {
               array[i][j] = Math.max(value[i] + array[i-1][j-weight[i]], array[i-1][j]);
            } else {
               array[i][j] = array[i-1][j];
            }
         }
      }
      
      for (int i = 0; i <= counter; i++)
      {
         for (int j = 0; j <= capacity; j++) {
         
            System.out.print(array[i][j]);
            System.out.print("   ");
         }
         
         System.out.println();
         
      }
     
      int optimalValue = array[counter][capacity];
      int opt = optimalValue;
      
      int totalWeight = capacity;
      
      // for loop begins
      for (int i = counter; i > 0 && opt > 0; i--) 
      {
         if (opt != array[i-1][totalWeight]) {
            solutionJewels.add(name[i]);
            opt = opt - value[i];
            totalWeight = totalWeight - weight[i];
            optimalWeight = optimalWeight + weight[i];
            optimalNumber = optimalNumber + 1;
         }
      }
   }
   /*
      @return the overall weight of items in optimal solution subset
   */
   public int optimalWeight()
   {
      return optimalWeight;
   }
   
   // @ return the overall number of items in the optimal solution subset.
   public int optimalNumber () {
      return optimalNumber;
   }
   /*
      @return true if item is in the solution subset; false otherwise
   */
   public boolean contains(String item) {
      if (solutionJewels.contains(item)) {
         return true;
      }
      return false;
   }
   
   public String solution() {
      String result = "";
      for (String s : solutionJewels) {
         result = result + s + " ";
      }
      return result;
   }
   /* 
      Methods below are just like the methods that were used earlier in the program.
      The only difference is they solve the problem using same items but with different KnapSack capacity.
      @optimalWeight AND @optimalNumber return overall weight of items and number of items
      in the solution subset with a different KnapSack capacity.
   
   */
   public int optimalWeight(int maxWeight) {
      optimalWeight = 0;
      optimalNumber = 0;
      solutionJewels.clear();
      createTable(counter, maxWeight);
      return optimalWeight;
   }
  
   public int optimalNumber(int maxWeight) {
      optimalWeight = 0;
      optimalNumber = 0;
      solutionJewels.clear();
      createTable(counter, maxWeight);
      return optimalNumber;
   }
   /*
      boolean function which returns true if the item is in optimal solution
      subset for a knapsack with maxWeight, returns false otherwise
   */
   public boolean contains(String item, int maxWeight) {
      optimalWeight = 0;
      optimalNumber = 0;
      solutionJewels.clear();
      createTable(counter, maxWeight);
      if (solutionJewels.contains(item)) {
         return true;
      }
      
      return false;
   }
   /*
      @return string representing the optimal solution subset for a knapsack with maxWeight
   */
   public String solution(int maxWeight) {
      String result = "";
      for (String s : solutionJewels) {
         result = result + s + " ";
      }
      return result; 
   }
   
}
/*** The END ***/