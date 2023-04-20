import contact.Contact;
import service.PhoneBookServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBookServiceImpl service = new PhoneBookServiceImpl(scanner);
        List<Contact> contacts = new LinkedList<>();

        boolean exit = true;
        while (exit) {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String input = scanner.nextLine();
            switch (Action.valueOf(input.toUpperCase())) {
                case ADD -> service.add(contacts);
                case REMOVE -> service.remove(contacts);
                case LIST -> service.listMenu(contacts);
                case EDIT -> service.edit(contacts);
                case COUNT -> service.count(contacts);
                case INFO -> service.info(contacts);
                case SEARCH -> service.search(contacts);
                case EXIT -> exit = false;
            }
        }

    }
}





