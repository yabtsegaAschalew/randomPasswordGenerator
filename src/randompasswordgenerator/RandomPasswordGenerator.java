
package randompasswordgenerator;

import java.util.*;
public class RandomPasswordGenerator {

    //Declaring possible characters to be combined for password generation.
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*_=+-/:;<>?|";
    static Scanner scanner = new Scanner(System.in);
    
    // HashMap to store passwords with a unique ID
    private static final Map<Integer, String> passwordStorage = new HashMap<>();

   public static void main(String[] args)
    {
       //Using while loop to display the options again and again.
       while (true) 
       {
                try 
                {
                    System.out.println("******************************************************************************");
                    System.out.println("*\t\t\t WELCOME TO HEAL PASSWORD GENERATOR                    *");
                    System.out.println("*\t\t\t    Press 1 To Generate Password.                      *");
                    System.out.println("*\t\t\t    Press 2 To Display Password.                       *");
                    System.out.println("*\t\t\t    Press 3 To Search Your password.                   *");
                    System.out.println("*\t\t\t    Press 4 To Delete Password                         *");
                    System.out.println("*\t\t\t    Press 0 To exit.                                   *");
                    System.out.println("*******************************************************************************");

                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) 
                    {
                        case 1:
                            generatePassword();
                            break;
                        case 2:
                            displayPassword();
                            break;
                        case 3:
                            searchPassword();
                            break;
                        case 4: 
                              deletePasswordByHash();
                       break;

                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (InputMismatchException e) 
                {
                    System.out.println("Invalid input. Please enter again!");
                    scanner.nextLine();
                } catch (Exception e) 
                {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
    }
   
}
   public static void generatePassword() throws Exception
   {
       int passLength=0;
       int s;
            //Asking for the amount of password needed.
            System.out.println("How many password do you want generate? ");
            s=scanner.nextInt();
         while(passLength<4)
         {
        try 
           {
                for(int i=1; i<=s; i++)
                {
                    System.out.print("Enter the length of the password: ");
                    passLength = scanner.nextInt();
                    if(passLength<4)
                    {
                    throw new IllegalArgumentException("Please password length should be atleast 4.");
                    }
                    String generatedPassword = generatePassword(passLength);
                    System.out.println("Generated Password: " + generatedPassword);
                    // Store the password with a unique ID
                    passwordStorage.put(generatedPassword.hashCode(), generatedPassword);
              }
          } catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
       }
   }
    public  static String generatePassword(int length) throws IllegalArgumentException, Exception 
    {
        return generatePassword(length, LOWER + UPPER + DIGITS + SYMBOLS);
    }

    // Method Overloading: Generate a password with custom characters
    public static String generatePassword(int length, String validChars) throws IllegalArgumentException
        {
            //Checking if the password length is eight or not.
          
            if (length < 4) 
                {
                    throw new IllegalArgumentException("Password length must be at least 4 characters.");
                }
            Random random = new Random();
            
            StringBuilder password = new StringBuilder(length);
            //Creating a random password with respect to the dessired length
            for (int i = 0; i < length; i++) 
                {
                    password.append(validChars.charAt(random.nextInt(validChars.length())));
                }
            return password.toString();
        }

    public static void displayPassword()
    {
        if (passwordStorage.isEmpty()) 
        {
            System.out.println("No passwords stored.");
        } 
        else 
        {
            //Enhanced forloop for password displaying
            for (Map.Entry<Integer, String> entry : passwordStorage.entrySet()) 
            {
                System.out.println("Hash: " + entry.getKey() + " | Password: " + entry.getValue());
            }
        }
    }
   public static void searchPassword() 
   {
        int searchHash;
        do 
        {
          System.out.print("Enter the password hash to search: ");
          try 
            {
              searchHash = scanner.nextInt();
              break; // Exit the loop if input is valid (integer)
            } 
          catch (InputMismatchException e) 
          {
            System.out.println("Invalid input. Please enter an integer hash value.");
            scanner.nextLine(); // Consuming the remaining character if there is any.
          }
        } 
        while (true); // Loop continues until a valid integer is entered

        String foundPassword = passwordStorage.get(searchHash);
        if (foundPassword != null) 
            {
              System.out.println("Password found: " + foundPassword);
            } 
        else 
            {
              System.out.println("Password not found.");
            }
   }
    // To delete a password
    public static void deletePasswordByHash() 
    {
        if (passwordStorage.isEmpty()) 
        {
          System.out.println("No passwords stored.");
        } 
        else 
        {
          System.out.print("Enter the password hash to delete: ");
          try 
          {
            int deleteHash = scanner.nextInt();
            // Trying to remove the password with the given hash
            if (passwordStorage.remove(deleteHash) != null) 
            {
              System.out.println("Password deleted.");
            } else 
            {
              System.out.println("Hash not found. Please enter the correct Hash Number.");
            }
          } catch (InputMismatchException e) 
          {
            System.out.println("Invalid input. Please enter an integer hash value.");
            scanner.nextLine();
            deletePasswordByHash();  // Recursive function
          }
        }
    }
}
