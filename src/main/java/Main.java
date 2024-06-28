// Uncomment this block to pass the first stage
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        System.out.print("$ ");
        
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        do{
            System.out.println(input+": command not found");

            System.out.print("$ ");

            input = scanner.nextLine();

            if(input.equals("exit 0")){
                break;
            }

            if(input.startsWith("echo")){
                System.out.println(input.substring(5));
            }else{
                System.out.println(input + ": command not found");
            }
        
        }while(!input.matches(""));

        scanner.close();
    }
}

