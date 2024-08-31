import java.io.File;
import java.util.*;
// import java.nio.file.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        // boolean flag=true;
        String cwd=Path.of("").toAbsolutePath().toString();
        while(true)
        {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("exit 0"))
            {
                break;
            }
            else if(input.startsWith("echo"))
            {
                System.out.println(input.substring(5));
            }
            else if(input.startsWith("pwd"))
            {
                System.out.println(System.getProperty("user.dir"));
                
            }
            else if(input.equals("pwd"))
            {
                System.out.println(cwd);
            }
            else if(input.startsWith("cd "))
            {
                String dir=input.substring(3);
                if(Files.isDirectory(Path.of(dir)))
                {
                    cwd=dir; 
                }
                else
                {
                    System.out.println("cd: "+dir+": No such file or directory");
                }
            }
            else if(input.startsWith("type"))
            {
                if(input.substring(5).equals("echo") || input.substring(5).equals("type") || input.substring(5).equals("exit")|| input.substring(5).equals("pwd") ||input.substring(5).equals("cd"))
                {
                    System.out.println(input.substring(5)+" is a shell builtin");
                }
                else if(input.substring(5).equals("cat"))
                {
                    System.out.println(input.substring(5)+" is /bin/cat");
                }
                else
                {
                    String pathEnv=System.getenv("PATH");
                    String []paths=pathEnv.split(":");
                    boolean flag=false;
                    for(String path: paths)
                    {
                        File file=new File(path, input.substring(5));
                        if(file.exists() && file.canExecute())
                        {
                            System.out.println(input.substring(5)+ " is "+file.getAbsolutePath());
                            flag=true;
                            break;
                        }
                    }
                    if(!flag)
                    {
                        System.out.println(input.substring(5)+ ": not found");
                    }
                }
            }
            else if(!input.equals(""))
            {
                String command = input.split(" ")[0];
                String path = getPath(command);
                if (path == null) 
                {
                    System.out.println(command+": command not found");
                } 
                else 
                {
                    String fullPath = path + input.substring(command.length());
                    Process p = Runtime.getRuntime().exec(fullPath.split(" "));
                    p.getInputStream().transferTo(System.out);
                }
            }
            else
            {
                System.out.println(input+ ": command not found");
            }
        }
    }
    private static String getPath(String command) 
    {
        for (String path : System.getenv("PATH").split(":")) 
        {
          Path fullPath = Path.of(path, command);
          if (Files.isRegularFile(fullPath)) 
          {
            return fullPath.toString();
          }
        }
        return null;
  }
}
