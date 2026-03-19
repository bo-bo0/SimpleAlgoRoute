package simplealgoroute.tools;

import java.util.Scanner;

public class SafeKeyboardScanner
{
    private final Scanner scan = new Scanner(System.in);

    public int nextInt()
    {
        int x;

        while (true)
        {
            if (scan.hasNextInt())
            {
                x = scan.nextInt();
                break;
            }

            else
            {
                System.out.print("Invalid input, please insert an integer value: ");
                scan.next();
            }
        }

        scan.nextLine();
        return x;
    }
}
