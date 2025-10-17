public class TestBankAccount {
    public static void main(String[] args) {
        // Testing constructors
        BankAccount current = new BankAccount(AccountType.CURRENT, "C001");
        BankAccount savings = new BankAccount(AccountType.SAVINGS, "S001", 1000.0);

        System.out.println("Current account balance: " + current.getBalance());
        System.out.println("Savings account balance: " + savings.getBalance());
        System.out.println();

        // Test deposit
        current.deposit(500.0);
        System.out.println("After deposit: " + current.getBalance());

        // Test withdrawal
        boolean result = current.withdraw(200.0);
        System.out.println("Withdrawal successful: " + result);
        System.out.println("Balance: " + current.getBalance());

        // Try to withdraw too much
        result = current.withdraw(300.0);
        System.out.println("Balance: " + current.getBalance());
        System.out.println();

        // Test withdrawal limit on savings
        savings.withdraw(100.0);
        savings.withdraw(100.0);
        savings.withdraw(50.0); // should fail
        System.out.println("Savings balance: " + savings.getBalance());
        System.out.println();

        // Test monthly maintenance
        System.out.println("Monthly maintenance on savings:");
        savings.performMonthlyMaintenance();
        System.out.println();

        System.out.println("Monthly maintenance on current:");
        current.performMonthlyMaintenance();
        System.out.println();

        // Test transfer
        BankAccount acc1 = new BankAccount(AccountType.CURRENT, "C002", 1000.0);
        BankAccount acc2 = new BankAccount(AccountType.CURRENT, "C003", 500.0);

        System.out.println("Before transfer:");
        System.out.println("Account 1: " + acc1.getBalance());
        System.out.println("Account 2: " + acc2.getBalance());

        acc1.transfer(true, acc2, 300.0);

        System.out.println("After transfer:");
        System.out.println("Account 1: " + acc1.getBalance());
        System.out.println("Account 2: " + acc2.getBalance());
    }
}