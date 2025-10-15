public class BankAccount {

    // Instance variables
    AccountType acctType;
    String acctID;

    int numWithdrawls;
    int withdrawalLimit;

    boolean inTheRed;

    double balance;
    double minBalance;
    double interestRate;
    double maintenanceFee;

    // CONSTANTS
    final int CURRENT_ACCT_MIN_BALANCE = 0;
    final int CURRENT_ACCT_MAINTENANCE_FEE = 0;
    final int SAVINGS_ACCT_MIN_BALANCE = 0;
    final int SAVINGS_WITHDRAWAL_LIMIT = 2;
    final int SAVINGS_ACCT_INTEREST_RATE = 0;


    // Constructors
    public BankAccount(AccountType type, String id) {
        balance = 0;
        numWithdrawls = 0;

        if (type == AccountType.CURRENT){
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
        }
    }

    public BankAccount(AccountType type, String id, double openingBalance){
        balance = openingBalance;
        numWithdrawls = 0;

        if (type == AccountType.CURRENT){
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
        }
    }

    // Getters
    public AccountType getAccountType(){
        return acctType;
    }
    public String getAccountID(){
        return acctID;
    }
    public double getMinBalance(){
        return minBalance;
    }

    // Instance methods
    public boolean withdraw(double amount){
        if (inTheRed || numWithdrawls >= withdrawalLimit){
            System.out.println("Sorry, could not perform withdrawal: You are in the red or you have exceeded your withdrawal limit");
            return false;
        } else if (balance - amount < minBalance) {
            System.out.println("Sorry, could not perform withdrawal: Insufficint balance.");
            return false;
        } else {
            return true;
        }
    }
    public void deposit(double amount){
        balance += amount;
    }
    public void performMonthlyMaintenance(){
//apples
    }
}
