import java.util.Scanner;

class Pro_main {

    //create variables and initialization variable
    static String[][] userDetails = new String[10][2];
    static double[] balance = new double[10];
    static int userCount = 0;
    static Scanner Input = new Scanner(System.in);

    //main method
    public static void main(String[] args) {
        mainMenu();
    }

    //methods for user registration
    public static void registerUser() {
        Input.nextLine();

        System.out.println("\nEnter your user name");
        userDetails[userCount][0] = Input.nextLine();
        System.out.println("\nEnter Password");
        userDetails[userCount][1] = Input.nextLine();
        System.out.println("\nEnter Balance");
        balance[userCount] = Input.nextInt();

        for (int i = 0; i < userCount; i++) {
            if (userDetails[userCount][0].equals(userDetails[i][0])) {
                System.out.println("\n\nThis username is already taker please tyr other one!\n\n");
                mainMenu();
                return;
            }
        }
        userCount++;

        System.out.println("\nRegistration Successfully And now you can login");
        mainMenu();
    }

    //method for login user , check balance, withdraw money, deposit money, and logout
    public static void loginUser() {
        Input.nextLine();
        int userNo = validateUser();
        char ch;
        if (userNo == -1) {
            System.out.println("Invalid username or password");
        }

        System.out.println("Login Successfully");


        do {
            System.out.println("\n\n1 for Check balance\n2 for withdraw money\n3 for Deposit money\n4 for Main manu\n5 Logout");
            int choice = Input.nextInt();

            switch (choice) {
                case 1:
                    displayBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    mainMenu();
                    break;
                case 5:
                    System.out.println("Logout Successfully");
                    mainMenu();
                    break;
                default:
                    System.out.println("Please enter valid number");

            }
            System.out.println("\nDo you want to do any other things (Y) for continue or (N) for logout and go to main menu");
            ch = Input.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
        System.out.println("Logout Successfully");
        mainMenu();
    }

    public static void displayBalance() {
        Input.nextLine();
        int userNo = validateUser();
        if (userNo == -1) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.println("Balance: " + balance[userNo]);
    }

    public static void displayAccountDetails() {
        Input.nextLine();
        int userNo = validateUser();
        if (userNo == -1) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.println("Name: " + userDetails[userNo][0] + "\n");
        System.out.println("Password: " + userDetails[userNo][1] + "\n");
        System.out.println("Balance: " + balance[userNo]);
    }

    public static void depositMoney() {
        Input.nextLine();
        int userNo = validateUser();
        if (userNo == -1) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.print("\nEnter amount to be added: ");
        balance[userNo] += Input.nextInt();
    }

    public static void withdrawMoney() {
        Input.nextLine();
        int userNo = validateUser();
        if (userNo == -1) {
            System.out.println("Username or password is incorrect");
            return;
        }

        System.out.print("\nEnter withdrawal amount: ");
        float withdraw = Input.nextInt();

        if (!(balance[userNo] - withdraw >= 0)) {
            System.out.println("\nInsufficient balance");
            return;
        }

        balance[userNo] -= withdraw;
        System.out.println("\nWithdraw Successfully");
    }

    //when we use this method it will validate the user and return user number if user is valid it gives the that user number
    public static int validateUser() {
        String name, password;

        System.out.println("\nEnter your user name");
        name = Input.nextLine();
        System.out.println("\nEnter Password");
        password = Input.nextLine();

        for (int i = 0; i < userDetails.length; i++) {
            if (userDetails[i][0].equals(name) && userDetails[i][1].equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public static void mainMenu() {
        int choice;
        char c;

        do {
            System.out.println("Menu: \n1 for Registration \n2 for Login\n3 for Deposit Money\n4 for Withdrew Money\n5 for Show balance\n6 for View account details \n7 for Exit");
            System.out.println("Enter your choice");
            choice = Input.nextInt();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    displayBalance();
                    break;
                case 6:
                    displayAccountDetails();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Please enter valid input");
                    break;
            }

            System.out.print("Do you want to see the menu again? (Y/N): ");
            c = Input.next().charAt(0);
        } while (c == 'Y' || c == 'y');
    }
}
