package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Employee_Book1 {

   //declare the hashmap that will hold the address book

    static HashMap<String, List<Double>> employee = new HashMap<String, List<Double>>();


    public static void main(String[] args) throws IOException {

        //variables for employee number, annual salary and employee name.
        double empno=0.0, annsal=0.0;
        boolean flag=true;
        String name="";
        Scanner key = new Scanner(System.in);



        transferData();






        displaysort();




        while(!(name.equals("Ex"))) {

            System.out.print("Enter the employee name. If you want to quit please enter Ex ");
            name = key.nextLine();

            if(name.equals("Ex")){

                continue;
            }
            System.out.print("Enter the employee number ");
            empno = key.nextDouble();

            flag=true;
            while(flag) {
                try {
                    System.out.print(("Enter the employee annual salary "));
                    annsal = key.nextDouble();

                    if(annsal==0 || annsal < 0){

                         throw new Myexception("Salary cannot be 0 or below 0");
                    }

                    //put the data in hashmap
                    employee.put(name, new ArrayList<Double>(Arrays.asList(empno,annsal)));
                    flag = false;
                    key.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("You must enter a numeric number for the salary ");
                    key.nextLine();

                } catch(Myexception e){
                    System.out.println(e.getMessage());
                }
            }
        }




         displayhashmap();

         saveFile();

         System.out.println("Data is saved in the text file ");





        }



  public static void saveFile() throws IOException {

     Path file = Paths.get("myemployeelist.txt");

      OutputStream output = new BufferedOutputStream(Files.newOutputStream(file,APPEND, CREATE));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
      int cnt = employee.size();
      String data;

      if(cnt > 0) {

          try {

              for(Map.Entry<String, List<Double>> entry:employee.entrySet()){

                  data =  entry.getKey() + " " +  employee.get(entry.getKey()).get(0) + " " +  employee.get(entry.getKey()).get(1) + "\n";

                  writer.write(data);

              }
          }

          catch(Exception e) {

              System.out.println(e.getMessage());

          }

          writer.close();


      }

  }

  public static void displaysort(){




      Map<String, List<Double>> result2 = employee.entrySet().stream()
              .sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
              (oldValue, newValue) -> oldValue, LinkedHashMap::new));


      for(Map.Entry<String, List<Double>> entry:result2.entrySet()){

          //display the key
          System.out.print("Employee name" +  " " + entry.getKey());

          //display the lists data
          System.out.println("\nEmployee number " + employee.get(entry.getKey()).get(0));
          System.out.println("Employee Annual Salary " + employee.get(entry.getKey()).get(1));





      }







  }

   public static void displayhashmap(){

       int cnt = employee.size();

       System.out.println("There are currently " + cnt + " employee/s in your records\n");

       //additional validation to ensure the dictionary is not empty
       if(cnt > 0){

           //loop in the dictionary
           for(Map.Entry<String, List<Double>> entry:employee.entrySet()){

               //display the key
               System.out.print("Employee name" +  " " + entry.getKey());

               //display the lists data
               System.out.println("\nEmployee number " + employee.get(entry.getKey()).get(0));
               System.out.println("Employee Annual Salary " + employee.get(entry.getKey()).get(1));


           }

       }

   }


    //method to transfer data from the text file to the hashmap
    public static void transferData() throws FileNotFoundException {

        //first check if file exists
        File tempFile = new File("myemployeelist.txt");
        boolean exists = tempFile.exists();

        //String list to hold the data retrieved
        String input[];

        try {

            //if the file exists transfer the data to the hash map
            if (exists) {
                Scanner myreader = new Scanner(tempFile);

                while(myreader.hasNextLine()){
                    //read the first line of the text
                    String data = myreader.nextLine();
                    //split the text into tokens
                    input = data.split("\\s");

                    //convert the text to double
                    double un = Double.parseDouble(input[1]);
                    double gr = Double.parseDouble(input[2]);

                    //put the data in hashmap
                    employee.put(input[0], new ArrayList<Double>(Arrays.asList(un,gr)));

                }

            }

        }
        catch(FileNotFoundException e)
        {
           System.out.println(("The file does not exists"));

        }



    }

    //method to check if a student name is already existing in the textfile
    public static void checkifnameexists(){


        Path file = Paths.get("mystudentlist.txt") ;
        String s;



    }



    public Double getEquiv(String g){

        //utilize

        String[] letgrade = new String[] { "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" };
        double[] numequiv = new double[] { 4.0, 3.7, 3.3, 3, 2.7, 2.3, 2, 1.7, 1.3, 1, 0.7, 0 };


        boolean test
                = Arrays.asList(letgrade)
                .contains(g);

        if (test)
        {

            int getIndex = Arrays.asList(letgrade).indexOf(g);

            return numequiv[getIndex];
        }

         return 0.0;

    }


}
