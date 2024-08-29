import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true)
        {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("exit 0"))
                break;
            if(input.startsWith("echo"))
                System.out.println(input.substring(5));
            if(input.startsWith("type"))
            {
                if(input.substring(5)=="echo" || input.substring(5)=="type" || input.substring(5)=="exit")
                {
                    System.out.println(input.substring(5)+" is a shell builtin");
                }
                else if(input.substring(5)=="cat")
                {
                    System.out.println(input.substring(5)+" is /bin/cat");
                }
                else
                {
                    System.out.println(input.substring(5)+" not found");
                }
            }
        }
        
    }
}
