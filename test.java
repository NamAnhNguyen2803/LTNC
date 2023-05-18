

public class test {
    public static void main(String[] args) {
    Person Real_G=new Student("Nam Anh","X2A","Software Enginner",2020,250.00);
    System.out.println(Real_G.toString());
}
}
 class Person{
    private String name;
    private String address;

    public Person(String name, String address){
        this.name=name;
        this.address=address;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
 }
 class Student extends Person{
    private String program;
    private int year;
    private double fee;

    public Student(String name,String address, String program, int year, double fee){
        super(name, address);
        this.program=program;
        this.year=year;
        this.fee=fee;
    }
    public String getProgram(){
        return program;
    }
    public void setProgram(String program){
        this.program=program;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
    public double getFree(){
        return fee;
    }
    public void setFee(double fee){
        this.fee=fee;
    }
    public String toString(){
        return "Student [Person[name="+getName()+",address= "+getAddress()+"], program= "+getProgram()+", year= "+year+", fee= "+ fee+"]";
    }
 }

 class Staff extends Person{
    private String school;
    private double pay;

    public Staff(String name, String address,String school, double pay){
        super(name, address);
        this.school=school;
        this.pay=pay;
    }
    public String getSchool(){
        return school;
    }
    public void setSchool(String school){
        this.school=school;
    }
    public double getPay(){
        return pay;
    }
    public void setPay(double pay){
        this.pay=pay;

    }
    public String toString(){
        return "Staff[Person[name= "+getName()+",address= "+getAddress()+"], school= "+school+",pay= "+pay+"]";
    }

 }
