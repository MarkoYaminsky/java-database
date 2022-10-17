package ua.com.yaminsky.bank.view;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.yaminsky.bank.controller.*;
import ua.com.yaminsky.bank.domain.*;

import java.util.*;

@Component
public class MyView {
    private final IBankAccountController bankAccountController;
    private final IBankController bankController;
    private final IClientController clientController;
    private final ICountryController countryController;
    private final ITransactionController transactionController;

    private final Map<String, String> menu;
    private final Map<String, IPrintable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final BankAccount nullBankAccount = new BankAccount(null, null, null, null, null);
    private final Bank nullBank = new Bank(null, null, null);
    private final Client nullClient = new Client(null, null, null, null);
    private final Country nullCountry = new Country(null, null);
    private final Transaction nullTransaction = new Transaction(null, null, null, null, null);


    public MyView(IBankAccountController bankAccountController, IBankController bankController,
                  IClientController clientController, ICountryController countryController,
                  ITransactionController transactionController) {

        this.bankAccountController = bankAccountController;
        this.bankController = bankController;
        this.clientController = clientController;
        this.countryController = countryController;
        this.transactionController = transactionController;

        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: bank account");
        menu.put("11", "  11 - Create bank account");
        menu.put("12", "  12 - Delete bank account");
        menu.put("13", "  13 - Get all bank accounts");
        menu.put("14", "  14 - Get bank account by ID");
        menu.put("15", "  15 - Get bank account by client ID");

        menu.put("2", "   2 - Table: bank");
        menu.put("21", "  21 - Create bank");
        menu.put("22", "  22 - Update bank");
        menu.put("23", "  23 - Delete bank");
        menu.put("24", "  24 - Get all banks");
        menu.put("25", "  25 - Get bank by ID");

        menu.put("3", "   3 - Table: client");
        menu.put("31", "  31 - Create client");
        menu.put("32", "  32 - Update client");
        menu.put("33", "  33 - Delete client");
        menu.put("34", "  34 - Get all clients");
        menu.put("35", "  35 - Get client by ID");
        menu.put("36", "  36 - Get client by first name");
        menu.put("37", "  37 - Get client by last name");

        menu.put("4", "   4 - Table: country");
        menu.put("41", "  41 - Create country");
        menu.put("42", "  42 - Delete country");
        menu.put("43", "  43 - Get all countries");
        menu.put("44", "  44 - Get country by ID");

        menu.put("5", "   5 - Table: transaction");
        menu.put("51", "  51 - Create transaction");
        menu.put("52", "  52 - Delete transaction");
        menu.put("53", "  53 - Get all transactions");
        menu.put("54", "  54 - Get transaction by ID");
        menu.put("55", "  55 - Get transaction by buyer bank account ID");
        menu.put("56", "  56 - Get transaction by seller bank account ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createBankAccount);
        methodsMenu.put("12", this::deleteBankAccount);
        methodsMenu.put("13", this::getAllBankAccounts);
        methodsMenu.put("14", this::getBankAccountById);
        methodsMenu.put("15", this::getBankAccountsByClientId);

        methodsMenu.put("21", this::createBank);
        methodsMenu.put("22", this::updateBank);
        methodsMenu.put("23", this::deleteBank);
        methodsMenu.put("24", this::getAllBanks);
        methodsMenu.put("25", this::getBankById);

        methodsMenu.put("31", this::createClient);
        methodsMenu.put("32", this::updateClient);
        methodsMenu.put("33", this::deleteClient);
        methodsMenu.put("34", this::getAllClients);
        methodsMenu.put("35", this::getClientById);
        methodsMenu.put("36", this::getClientByFirstName);
        methodsMenu.put("37", this::getClientByLastName);

        methodsMenu.put("41", this::createCountry);
        methodsMenu.put("42", this::deleteCountry);
        methodsMenu.put("43", this::getAllCountries);
        methodsMenu.put("44", this::getCountryById);

        methodsMenu.put("51", this::createTransaction);
        methodsMenu.put("52", this::deleteTransaction);
        methodsMenu.put("53", this::getAllTransactions);
        methodsMenu.put("54", this::getTransactionById);
        methodsMenu.put("55", this::getTransactionByBuyerAccountId);
        methodsMenu.put("56", this::getTransactionBySellerAccountId);
    }

    private void selectAllTable() {
        getAllBankAccounts();
        getAllBanks();
        getAllClients();
        getAllCountries();
        getAllTransactions();
    }

    private String getPersonTypeFromKeyboard() {
        System.out.println("Input person type (1 - legal, 2 - physical): ");
        String personType = input.nextLine();
        String result = null;
        switch (personType) {
            case "1" -> result = "LEGAL";
            case "2" -> result = "PHYSICAL";
            default -> getPersonTypeFromKeyboard();
        }
        return result;
    }

    private void createBankAccount() {
        System.out.println("Input requisites: ");
        String requisites = input.nextLine();
        String personType = getPersonTypeFromKeyboard();
        System.out.println("Input bank ID: ");
        Integer bankId = Integer.valueOf((input.nextLine()));
        System.out.println("Input client ID: ");
        Integer clientId = Integer.valueOf((input.nextLine()));
        BankAccount bankAccount = new BankAccount(null, requisites, personType, bankId, clientId);

        int count = bankAccountController.create(bankAccount);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteBankAccount() {
        System.out.println("Input ID: ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = bankAccountController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void getAllBankAccounts() {
        System.out.println("\nTable: bank_account");
        List<BankAccount> bankAccounts = bankAccountController.getAll();
        for (BankAccount bankAccount : bankAccounts) {
            System.out.println(bankAccount);
        }
    }

    private void getBankAccountById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<BankAccount> bankAccount = bankAccountController.getById(id);
        System.out.println(bankAccount.orElse(nullBankAccount));
    }

    private void getBankAccountsByClientId() {
        System.out.println("Input client ID: ");
        Integer clientId = Integer.valueOf(input.nextLine());

        Optional<List<BankAccount>> banks = bankAccountController.getBankAccountsByClientId(clientId);
        System.out.println(banks.orElse(List.of()));
    }

    private void createBank() {
        System.out.println("Input country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());
        System.out.println("Input bank name: ");
        String bankName = input.nextLine();

        Bank bank = new Bank(null, bankName, countryId);

        int count = bankController.create(bank);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateBank() {
        System.out.println("Input bank ID: ");
        Integer bankId = Integer.valueOf(input.nextLine());

        System.out.println("Input new bank name: ");
        String bankName = input.nextLine();
        System.out.println("Input new country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());

        Bank bank = new Bank(null, bankName, countryId);

        int count = bankController.update(bankId, bank);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteBank() {
        System.out.println("Input bank ID: ");
        Integer bankId = Integer.valueOf(input.nextLine());

        int count = bankController.delete(bankId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void getAllBanks() {
        System.out.println("\nTable: bank");
        List<Bank> banks = bankController.getAll();
        for (Bank bank : banks) {
            System.out.println(bank);
        }
    }

    private void getBankById() {
        System.out.println("Input bank ID: ");
        Integer bankId = Integer.valueOf(input.nextLine());

        Optional<Bank> country = bankController.getById(bankId);
        System.out.println(country.orElse(nullBank));
    }

    private void createClient() {
        System.out.println("Input first name: ");
        String firstName = input.nextLine();
        System.out.println("Input last name: ");
        String lastName = input.nextLine();
        System.out.println("Input country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());

        Client client = new Client(null, firstName, lastName, countryId);

        int count = clientController.create(client);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateClient() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new first name: ");
        String firstName = input.nextLine();
        System.out.println("Input new last name: ");
        String lastName = input.nextLine();
        System.out.println("Input new country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());

        Client client = new Client(null, firstName, lastName, countryId);

        int count = clientController.update(id, client);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteClient() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = clientController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void getAllClients() {
        System.out.println("\nTable: client");
        List<Client> clients = clientController.getAll();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void getClientById() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Client> client = clientController.getById(id);
        System.out.println(client.orElse(nullClient));
    }

    private void getClientByFirstName() {
        System.out.println("Input first name: ");
        String firstName = (input.nextLine());

        Optional<List<Client>> clients = clientController.getClientByFirstName(firstName);
        System.out.println(clients.orElse(List.of()));
    }

    private void getClientByLastName() {
        System.out.println("Input last name: ");
        String lastName = (input.nextLine());

        Optional<List<Client>> clients = clientController.getClientByLastName(lastName);
        System.out.println(clients.orElse(List.of()));
    }

    private void createCountry() {
        System.out.println("Input country name: ");
        String countryName = input.nextLine();

        Country country = new Country(null, countryName);

        int count = countryController.create(country);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteCountry() {
        System.out.println("Input country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());

        int count = countryController.delete(countryId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void getAllCountries() {
        System.out.println("\nTable: country");
        List<Country> countries = countryController.getAll();
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    private void getCountryById() {
        System.out.println("Input country ID: ");
        Integer countryId = Integer.valueOf(input.nextLine());

        Optional<Country> country = countryController.getById(countryId);
        System.out.println(country.orElse(nullCountry));
    }

    private void createTransaction() {
        System.out.println("Input event: ");
        String event = input.nextLine();
        System.out.println("Input sum in dollars: ");
        Float sumInDollars = Float.valueOf(input.nextLine());
        System.out.println("Input buyer account ID: ");
        String buyerAccountId = input.nextLine();
        System.out.println("Input seller account ID: ");
        String sellerAccountId = input.nextLine();

        Transaction transaction = new Transaction(null, event, sumInDollars, buyerAccountId, sellerAccountId);

        int count = transactionController.create(transaction);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteTransaction() {
        System.out.println("Input transaction ID: ");
        Integer transactionId = Integer.valueOf(input.nextLine());

        int count = bankController.delete(transactionId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void getAllTransactions() {
        System.out.println("\nTable: transaction");
        List<Transaction> transactions = transactionController.getAll();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void getTransactionById() {
        System.out.println("Input transaction ID: ");
        Integer transactionId = Integer.valueOf(input.nextLine());

        Optional<Transaction> transaction = transactionController.getById(transactionId);
        System.out.println(transaction.orElse(nullTransaction));
    }

    private void getTransactionByBuyerAccountId() {
        System.out.println("Input buyer account ID: ");
        Integer buyerAccountId = Integer.valueOf(input.nextLine());

        Optional<List<Transaction>> transaction = transactionController.getTransactionsByBuyerAccountId(buyerAccountId);
        System.out.println(transaction.orElse(List.of()));
    }

    private void getTransactionBySellerAccountId() {
        System.out.println("Input seller account ID: ");
        Integer sellerAccountId = Integer.valueOf(input.nextLine());

        Optional<List<Transaction>> transaction = transactionController.getTransactionsBySellerAccountId(sellerAccountId);
        System.out.println(transaction.orElse(List.of()));
    }

    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String digit) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(digit)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!keyMenu.equals("Q"));
    }
}

