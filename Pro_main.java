import java.util.Scanner;

class Pro_main {

    //create variables and initialization variable
    static int Max_user = 10; //Max user count
    static String[][] userDetails = new String[Max_user][4];
    static int userCount = 0;//when user register successfully then it can increase by 1
    static Scanner Input = new Scanner(System.in); //static Scanner object to use in whole methods

    //main method
    public static void main(String[] args) {
        System.out.print("\n||================================||\n");
        System.out.print("\n--||-- BANK MANAGEMENT SYSTEM --||--\n");
        System.out.print("\n||================================||\n");

        //this is the first login menu to do some tasks
        int choice;
        String name, password, email;
        do {
            System.out.println("\n\n1 => Register \n2 => login \n3 => Exit"); //give numeric choice to user
            System.out.print("\nEnter Your Choice : ");
            choice = Input.nextInt();

            switch (choice) {
                case 1 -> {
                    Input.nextLine();
                    System.out.print("\n\nEnter your user name: ");
                    name = Input.nextLine();
                    System.out.print("\n\nEnter Email: ");
                    email = Input.nextLine();
                    System.out.print("\nEnter your password: ");
                    password = Input.nextLine();
                    registerUser(name.trim(), password.trim(), email.trim());
                }
                case 2 -> {
                    Input.nextLine();
                    System.out.print("\n\nEnter your user name: ");
                    name = Input.nextLine();
                    System.out.print("\nEnter your password: ");
                    password = Input.nextLine();
                    loginUser(name.trim(), password.trim());
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Please enter valid input");
            }
        } while (true);
    }

    //methods for user registration
    public static void registerUser(String name, String password, String email) {
        //chek user count is grater then Max_user or not
        if (userCount >= Max_user) {
            System.out.print("\n---There are not space for another users---\n");
            return;
        }

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("\n---Please fill all the fields---\n");
            return;
        }

        //chek username is already taken or not
        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(name)) {
                System.out.println("\n\n--This username is already taker please tyr other one!--\n\n");
                return;
            }
        }

        if (!(email.contains("@") && email.contains("."))) {
            System.out.println("\n\n--Please Enter valid Email--");
            return;
        }

        if (password.length() < 6) {
            System.out.println("\n\n--Password Too Short--");
            return;
        }

        if (!(password.contains("@") || password.contains("#") || password.contains("$"))) {
            System.out.println("\n\n--Please use Strong Password--");
            return;
        }

        userDetails[userCount][0] = name;
        userDetails[userCount][1] = password;
        userDetails[userCount][2] = "0.0";
        userDetails[userCount][3] = email;
        userCount++;

        System.out.println("\n---Registration Successfully And now you can login---");
    }

    //method for login user , check balance, withdraw money, deposit money, and logout
    public static void loginUser(String name, String password) {
//        int userNo = validateUser(name, password);
        int userNo = -1;
        int i;

        for (i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(name) && userDetails[i][1].equals(password)) {
                userNo = i;
                break;
            }
        }


        if (userNo < 0) {
            System.out.println("--Invalid username or password--");
            return;
        }

        System.out.println("\n\n---Login Successfully---\n\n---Welcome " + userDetails[userNo][0] + "---\n");


        do {
            System.out.println("\n---Which operation you want to perform with your Account---");
            System.out.println("\n\n4 => Check balance\n5 => withdraw money\n6 => Deposit money\n7 => View Account Details\n8 => Logout");
            System.out.print("\nEnter Your Choice : ");
            int choice = Input.nextInt();

            switch (choice) {
                case 4 -> displayBalance(userNo);

                case 5 -> {
                    System.out.print("\nEnter withdrawal amount: ");
                    double withdraw = Input.nextDouble();
                    withdrawMoney(userNo, withdraw);
                }
                case 6 -> {
                    System.out.print("\nEnter amount to be added: ");
                    double deposit = Input.nextDouble();
                    depositMoney(userNo, deposit);
                }
                case 7 -> displayAccountDetails(userNo);

                case 8 -> {
                    System.out.println("---Logout Successfully---");
                    return;
                }
                default -> System.out.println("--Please enter valid number--");
            }
        } while (true);
    }


    public static void displayBalance(int userNo) {
        System.out.println("\nBalance: " + userDetails[userNo][2]);
    }

    public static void displayAccountDetails(int userNo) {
        System.out.println("\nName: " + userDetails[userNo][0] + "\n");
        System.out.println("\nPassword: " + userDetails[userNo][1] + "\n");
        System.out.println("\nBalance: " + userDetails[userNo][2]);
        System.out.println("\nEmail: " + userDetails[userNo][3]);
    }

    public static void depositMoney(int userNo, double deposit) {
        if (deposit <= 0) {
            System.out.println("\n--Amount must be greater than zero--");
            return;
        }

        double curAmount = Double.parseDouble(userDetails[userNo][2]);
        curAmount += deposit;
        userDetails[userNo][2] = String.valueOf(curAmount);

        System.out.println("\n---Deposit successfully---");
    }

    public static void withdrawMoney(int userNo, double withdraw) {

        if (withdraw <= 0) {
            System.out.println("\n--Amount must be grater then Zero--");
            return;
        }

        double curAmount = Double.parseDouble(userDetails[userNo][2]);

        if (!(curAmount - withdraw >= 0)) {
            System.out.println("\n--Insufficient balance--");
            return;
        }

        curAmount -= withdraw;
        userDetails[userNo][2] = String.valueOf(curAmount);
        System.out.println("\n---Withdraw Successfully---");
    }
}
