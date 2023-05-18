import java.util.Scanner;
import java.lang.Math;


public class Ex4_part2 {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String[] options={"Option_1","Option_2","Option_3"};
    
        System.out.println("Menu:");

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("Nhap lua chon cua ban: ");
        int choice = sc.nextInt();
        
        if (choice >= 1 && choice <= options.length) {
            System.out.println("Ban da chon " + options[choice - 1]);
        } else {
            System.out.println("Lua chon khong nam trong du tinh, xin moi chon lai");
   
        sc.close();
    }
}
}

