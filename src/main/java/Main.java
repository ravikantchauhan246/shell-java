import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {
  private static File currentDirectory =
      new File(System.getProperty("user.dir"));
  public static void main(String[] args) throws Exception {
    while (true) {
      System.out.print("$ ");
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      if (input.equals("exit 0")) {
        scanner.close();
        break;
      } else if (input.startsWith("echo ")) {
        System.out.println(input.substring(5));
      } else if (input.startsWith("type ")) {
        List<String> builtInCommands =
            Arrays.asList("echo", "type", "exit", "pwd", "cd");
        if (builtInCommands.contains(input.substring(5))) {
          System.out.println(input.substring(5) + " is a shell builtin");
        } else if (System.getenv("PATH") != null) {
          String pathEnv = System.getenv("PATH");
          String[] paths = pathEnv.split(":");
          boolean found = false;
          for (String path : paths) {
            File file = new File(path + "/" + input.substring(5));
            if (file.exists() && file.canExecute()) {
              System.out.println(input.substring(5) + " is " +
                                 file.getAbsolutePath());
              found = true;
              break;
            }
          }
          if (!found) {
            System.out.println(input.substring(5) + ": not found");
          }
        } else {
          System.out.println(input.substring(5) + ": not found");
        }
      } else if (input.equals("pwd")) {
        System.out.println(currentDirectory.getAbsolutePath());
      } else if (input.startsWith("cd")) {
        String[] words = input.split(" ");
        String path = words[1];
        File newDirectory;
        if (path.startsWith("/")) {
          // Absolute path
          newDirectory = new File(path);
        } else if (path.equals("~")) {
          
          newDirectory = new File(System.getenv("HOME"));
        } else {
          // Relative path
          newDirectory =
              currentDirectory.toPath().resolve(path).normalize().toFile();
        }
        if (newDirectory.exists() && newDirectory.isDirectory()) {
          currentDirectory = newDirectory;
        } else {
          System.out.println("cd: " + path + ": No such file or directory");
        }
      } else {
        String command = input.split(" ")[0];
        String path = getPath(command);
        if (path == null) {
          System.out.printf("%s: command not found%n", command);
        } else {
          String fullPath = path + input.substring(command.length());
          Process p = Runtime.getRuntime().exec(fullPath.split(" "));
          p.getInputStream().transferTo(System.out);
        }
      }
    }
  }
  private static String getPath(String input) {
    for (String path : System.getenv("PATH").split(":")) {
      Path file = Path.of(path, input);
      if (Files.isReadable(file)) {
        return file.toString();
      }
    }
    return null;
  }
}