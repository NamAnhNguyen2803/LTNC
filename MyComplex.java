import java.util.Scanner;
import java.lang.Math;


class MyComplex{
    private double real=0.0;
    private double imag=0.0;

    public MyComplex(){

    }
    public MyComplex(double real,double imag){
        this.real=real;
        this.imag=imag;
    }
    public double getReal(){
        return real;
    }
    public void setReal(double real){
        this.real=real;

    }
    public double getImag(){
        return imag;
    }
    public void setImag(double imag){
        this.imag=imag;
    }
    public void setValue(double real,double imag){
        this.real=real;
        this.imag=imag;
    }
    public String toString(){
        return "("+this.real+"+"+this.imag+"i)";
    }
    public boolean isReal(){
        return imag==0;
    }
    public boolean isImaginary(){
       return real==0;
    }
    public boolean equals(double real,double imag){
        return this.real==this.imag;
    }
    public boolean equals(MyComplex another){
        return this.real==another.real && this.imag==another.imag;
    }
    public double magnitude(){
        return  Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imag, 2));
    }
    public double argument(){
        return Math.toRadians(Math.atan2(imag,real));
    }
    public MyComplex add(MyComplex right){
        this.real+=right.real;
        this.imag+=right.imag;
        return this;
    }
    public MyComplex addNew(MyComplex right){
        double realAdd=this.real+right.real;
        double imagAdd=this.imag+right.imag;
        return new MyComplex(realAdd,imagAdd);
    }
    public MyComplex subtract(MyComplex right){
        this.real-=right.real;
        this.imag-=right.imag;
        return this;
    }
    public MyComplex subtractNew(MyComplex right){
        double realSub=this.real-right.real;
        double imagSub=this.imag-right.imag;
        return new MyComplex(realSub,imagSub);
    }

public MyComplex multiply(MyComplex right){
        double realMul=this.real*right.real- this.imag*right.imag;
        double imagMul=this.real*right.imag + this.imag*right.real;
        this.real=realMul;
        this.imag=imagMul;
        return this;
   }
   public MyComplex divide(MyComplex right){
    double quotient= right.real*right.real+right.imag*right.imag;
    double realDiv=(this.real*right.real+this.imag*right.imag)/quotient;
    double imagDiv=(this.imag*right.real-right.imag*this.real)/quotient;
    this.real=realDiv;
    this.imag=imagDiv;
    return this;
   }
   public MyComplex conjugate(){
    this.imag=-this.imag;
    return this;
   }
   public static void main(String[] args) {

    MyComplex c1 = new MyComplex(1, 2);
    MyComplex c2 = new MyComplex(3, 4);


    System.out.println("c1 = " + c1.toString());
    System.out.println("c2 = " + c2.toString());

    System.out.println("Gia tri thuc c1 = " + c1.getReal());
    System.out.println("Gia tri ao c1 = " + c1.getImag());
    System.out.println("Gia tri thuc  c2 = " + c2.getReal());
    System.out.println("Gia tri ao cua c2 = " + c2.getImag());

    c1.setValue(5, 6);
    System.out.println("c1 sau khi gan gia tri moi= " + c1.toString());

    MyComplex sum = c1.addNew(c2);
    System.out.println("c1 + c2 = " + sum.toString());

    MyComplex sub = c1.subtractNew(c2);
    System.out.println("c1 - c2 = " + sub.toString());

    MyComplex mul = c1.multiply(c2);
    System.out.println("c1 * c2 = " + mul.toString());

    MyComplex div = c1.divide(c2);
    System.out.println("c1 / c2 = " + div.toString());

    MyComplex conj = c1.conjugate();
    System.out.println("So lien ke cua c1 = " + conj.toString());


    System.out.println("Do lon cua c1 = " + c1.magnitude());
    System.out.println("Do lon cua c2 = " + c2.magnitude());
    System.out.println("Do lech cua c1 = " + c1.argument());
    System.out.println("Do lech cua c2 = " + c2.argument());
}

}
