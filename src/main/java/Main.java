import java.io.File;
import java.util.Scanner;
import java.nio.file.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // boolean flag=true;
        while(true)
        {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String inputsplit[]=input.split(" ");
            String command=inputsplit[0];
            if(input.equals("exit 0"))
                break;
            else if(input.startsWith("echo"))
                System.out.println(input.substring(5));
            else if(input.startsWith("type"))
            {
                if(input.substring(5).equals("echo") || input.substring(5).equals("type") || input.substring(5).equals("exit"))
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
                            ProcessBuilder processBuilder = new ProcessBuilder();
                            processBuilder.command(file.getAbsolutePath(), commandArgs);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            while ((line = reader.readLine()) != null) 
                            {
                                System.out.println(line);
                            }
                            break;
                        }
                    }
                    if(!flag)
                    {
                        System.out.println(input.substring(5)+ ": not found");
                    }
                }
            }
            // else if()
            // {
                
            // }
            else
            {
                System.out.println(input+ ": command not found");
            }
        }
    }
}
