import java.util.*;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) throws Exception {
    // Uncomment this block to pass the first stage
    while (true) {
      System.out.print("$ ");
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      String[] strings = input.split(" ");
      if ("exit".equals(strings[0])) {
        if ("0".equals(strings[1])) {
          return;
          
        }
      } else if ("echo".equals(strings[0])) {
        System.out.println(input.substring(5));
      } else if ("type".equals(strings[0])) {
        Set<String> st = Set.of("type", "exit", "echo");
        if (st.contains(strings[1])) {
          System.out.println(strings[1] + " is a shell builtin");
        } else {
          System.out.println(strings[1] + ": not found");
        }
      } else {
        System.out.println(input + ": command not found");
      }

      scanner.close();
    }
    
  }
}