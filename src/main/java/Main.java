import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean flag=true;
        while(flag==true)
        {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("exit 0"))
                break;
            if(input.matches(""))
                flag=false;
            if(input.startsWith("echo"))
                System.out.println(input.substring(5));
            if(input.startsWith("type"))
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
                    System.out.println(input.substring(5)+" not found");
                }
            }
        }
    }
}
