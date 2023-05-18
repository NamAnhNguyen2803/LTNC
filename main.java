import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;


public class Student {
   private String name;
   private int age;
   private double grade;

   public Student(String name, int age, double grade) {
       this.name = name;
       this.age = age;
       this.grade = grade;
   }
   public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}

public double getGrade() {
    return grade;
}

public void setGrade(double grade) {
    this.grade = grade;
}
}

public class Main {
   public static void main(String[] args) {
       // Read the student information from the file
       try {
           FileReader reader = new FileReader("students.txt");
           BufferedReader bufferedReader = new BufferedReader(reader);

           String line;
           while ((line = bufferedReader.readLine()) != null) {
               String[] tokens = line.split(",");
               String name = tokens[0];
               int age = Integer.parseInt(tokens[1]);
               double grade = Double.parseDouble(tokens[2]);

               Student student = new Student(name, age, grade);
               System.out.println(student.getName() + " - " + student.getAge() + " - " + student.getGrade());
           }

           bufferedReader.close();
           reader.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}

public class Main {
   public static void main(String[] args) {
       // Create an array of students
       Student[] students = new Student[] {
           new Student("John", 18, 3.0),
           new Student("Sarah", 19, 3.5),
           new Student("David", 17, 4.0);
       };

       // Write the student information to a file
       try {
           FileWriter writer = new FileWriter("students.txt");
           for (Student student : students) {
               writer.write(student.getName() + "," + student.getAge() + "," + student.getGrade() + "\n");
           }
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
