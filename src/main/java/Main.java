import java.util.Scanner;
public class Main {
  public static void main(String[] args) throws Exception {
    // System.out.println("Logs from your program will appear here!");
    while (true) {
      System.out.print("$ ");
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      if (input.equals("exit 0")) {
        break;
      }
    //   if (input.startsWith("echo")) {
    //     System.out.println(input.substring(5));
    //   } else {
    //     System.out.println(input + ": command not found");
    //   }
      
      if(input.startsWith("type")){
        if(input.substring(5)=="echo" || input.substring(5)=="exit"){
            System.out.println(input.substring(5)+ " is a shell builtin");
        }else{
            System.out.println(input+": not found");
        }
      }
  }
}}