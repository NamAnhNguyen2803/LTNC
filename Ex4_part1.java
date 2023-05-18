import java.util.Scanner;
import java.lang.Math;


public class Ex4_part1 {
    public static void main(String[] args) {
    System.out.println("1.Option 1");
    System.out.println("2.Option 2");
    System.out.println("3.Option 3");
    System.out.println("Nhap 1,2 hoac 3 de lua chon:");
    Scanner sc =new Scanner(System.in);
    int n=sc.nextInt();

    if(n ==1){
        System.out.println("Ban da chon option 1.");
    }else if(n==2){
        System.out.println("Ban da chon option 2.");
    }else if(n==3){
        System.out.println("Ban da chon option 3.");
    }else{
            System.out.println("Ban da nhap sai lua chon, vui long khoi dong lai chuong trinh de chon lai");
        }
    }
    sc.close;

}

