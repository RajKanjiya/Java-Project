import java.util.Scanner;

class Pro_main {

    //create variables and initialization variable

    static String[][] userDetails = new String[10][2];
    static String[] username = new String[10];
    static String[] passwords = new String[10];
    static double[] balance = new double[10];
    static int userCount = 0;
    static Scanner Input = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void registerUser() {
        Input.nextLine();

        System.out.println("\nEnter your user name");
        userDetails[userCount][0] = Input.nextLine();
        System.out.println("\nEnter Password");
        userDetails[userCount][1] = Input.nextLine();
        System.out.println("\nEnter Balance");
        balance[userCount] = Input.nextInt();

        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(userDetails[i][0])) {
                System.out.println("\n\nThis username is already taker please tyr other one!\n\n");
                mainMenu();
                return;
            }
        }
        userCount++;

        System.out.println("\nRegistration Successfully And now you can login");
    }

    public static void loginUser() {
        Input.nextLine();
        int userNo = validateUser();
        char ch;
        if (userNo == 0) {
            System.out.println("Invalid username or password");
        }

        System.out.println("Login Successfully");


        do {
            System.out.println("\n\n1 for Check balance\n2 for withdraw money\n3 for Deposit money\n4 for Main manu");
            char c = Input.next().charAt(0);

            switch (c) {
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
                default:
                    System.out.println("Please enter valid number");

            }
            System.out.println("\nDo you want to do any other things(Y/N)");
            ch = Input.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    public static void displayBalance() {
        int userNo = validateUser();
        if (userNo == 0) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.println("Balance: " + balance[userNo]);
    }

    public static void displayAccountDetails() {
        int userNo = validateUser();
        if (userNo == 0) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.println("Name: " + userDetails[userNo][0] + "\n");
        System.out.println("Password: " + userDetails[userNo][1] + "\n");
        System.out.println("Balance: " + balance[userNo]);
    }

    public static void depositMoney() {
        int userNo = validateUser();
        if (userNo == 0) {
            System.out.println("Username or password is incorrect");
            return;
        }
        System.out.print("\nEnter amount to be added: ");
        balance[userNo] += Input.nextInt();
    }

//    public static void depositMoney(int user) {
//        System.out.print("\nEnter amount to be added: ");
//        balance[user] += Input.nextInt();
//    }

    public static void withdrawMoney() {
        int userNo = validateUser();
        if (userNo == 0) {
            System.out.println("Username or password is incorrect");
            return;
        }

        System.out.print("\nEnter withdrawal amount: ");
        float withdraw = Input.nextInt();

        if (!(balance[userNo] - withdraw >= 0)) {
            return;
        }

        balance[userNo] -= withdraw;
        System.out.println("\nWithdraw Successfully");
    }

    public static int validateUser() {
        String name, password;

        System.out.println("\nEnter your user name");
        name = Input.nextLine();
        System.out.println("\nEnter Password");
        password = Input.nextLine();

        for (int i = 0; i < username.length; i++) {
            if (userDetails[i][0].equals(name) && userDetails[i][1].equals(password)) {
                return i - 1;
            }
        }
        return 0;
    }

    /*public static boolean checkRegistration(String name, String password, String msg) {
        int i;
        for (i = 0; i < username.length; i++) {
            if (!(username[i].equals(name) && passwords[i].equals(password))) {
                System.out.println(msg);
                return false;
            }
        }
        return true;
    }
    */

    public static void mainMenu() {
        int choice;
        char c = ' ';

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
                    break;
                default:
                    System.out.println("Please enter valid input");
                    break;
            }

            System.out.print("Do you want to see the menu again? (Y/N): ");
            c = Input.next().charAt(0);
        } while (c == 'Y' || c == 'y');
    }
}
