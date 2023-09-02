package lk.ijse.dep11;

public class Customer {
    private String accountID;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    private String name;

    private String ammount;

    public Customer() {
    }

    public Customer(String accountID, String name, String ammount) {
        this.accountID = accountID;
        this.name = name;
        this.ammount = ammount;
    }

}
