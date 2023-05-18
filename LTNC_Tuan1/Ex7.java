import java.util.Scanner;
import java.lang.Math;

public class Ex7 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Nhập giá trị của n: ");
        int n;
        n=sc.nextInt();
        int start = (int) Math.pow(10, n - 1);
        int end = (int) Math.pow(10, n) - 1;


        for(int i = start; i <= end; i++) {
            System.out.println(i);
        }
    }
}
