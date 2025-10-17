public class BankAccount {

    // Instance variables
    AccountType acctType;
    String acctID;

    int numWithdrawals;
    int withdrawalLimit;

    boolean inTheRed;

    double balance;
    double minBalance;
    double interestRate;
    double maintenanceFee;

    // CONSTANTS
    static final double CURRENT_ACCT_MIN_BALANCE = 100.0;
    static final double CURRENT_ACCT_MAINTENANCE_FEE = 5.0;
    static final double SAVINGS_ACCT_MIN_BALANCE = 500.0;
    static final double SAVINGS_ACCT_INTEREST_RATE = 0.05;
    static final int SAVINGS_WITHDRAWAL_LIMIT = 2;



    // Constructors
    public BankAccount(AccountType type, String id) {
        balance = 0;
        numWithdrawals = 0;
        this.acctType = type;
        this.acctID = id;

        if (type == AccountType.CURRENT){
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
        }
        inTheRed = (balance < minBalance);
    }

    public BankAccount(AccountType type, String id, double openingBalance){
        balance = openingBalance;
        numWithdrawals = 0;
        this.acctType = type;
        this.acctID = id;

        if (type == AccountType.CURRENT){
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
        } else {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
        }
        inTheRed = (balance < minBalance);
    }

    // Getters
    public AccountType getAccountType(){
        return acctType;
    }
    public String getAccountID() {
        return acctID;
    }
    public double getMinBalance(){
        return minBalance;
    }
    public double getBalance(){
        return balance;
    }

    // Instance methods
    public boolean withdraw(double amount){
        if (inTheRed || numWithdrawals >= withdrawalLimit){
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
        double earnedInterest = (interestRate / 12) * balance;

        balance += earnedInterest;
        balance -= maintenanceFee;
        inTheRed = (balance < minBalance);
        numWithdrawals = 0;

        System.out.println("Earned interest: " + earnedInterest);
        System.out.println("Maintenance fee: " + maintenanceFee);
        System.out.println("Updated balance: " + balance);

        if (inTheRed) {
            System.out.println("WARNING: This account is in the red!");
        }
    }
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
        if (transferTo) {
            if (this.withdraw(amount)) {
                otherAccount.deposit(amount);
                return true;
            } else {
                return false;
            }
        } else {
            // Transfer FROM other account TO this account
            if (otherAccount.withdraw(amount)) {
                this.deposit(amount);
                return true;
            } else {
                return false;
            }
        }
    }
}
