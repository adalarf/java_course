package lr2;


interface BankAccountInterface {

    void createAccount(String ownerName, double initialBalance);

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();
}

class BankAccount implements BankAccountInterface {
    private String owner;
    private double balance;

    @Override
    public void createAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
        System.out.println("Счет создан для " + owner);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Переводено: " + amount);
        } else {
            System.out.println("Некорретный размер переводимых средств");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Снято: " + amount);
        } else {
            System.out.println("Некорректный размер снимаемых средств");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}


public class Task7 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.createAccount("Тестовый пользователь", 1000);
        account.deposit(200);
        account.withdraw(100);

        System.out.println("Текущий баланс: " + account.getBalance());
    }
}
