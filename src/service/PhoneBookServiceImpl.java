package service;

import contact.Contact;
import contact.OrganizationContact;
import contact.PersonContact;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneBookServiceImpl implements PhoneBookService {
    Scanner scanner;

    public PhoneBookServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Contact add(List<Contact> contacts) {
        Contact contact;
        System.out.println("Enter the type (person, organization): ");
        switch (scanner.nextLine().toLowerCase()) {
            case "person" -> contact = addPerson();
            case "organization" -> contact = addOrganization();
            default -> contact = addPerson();
        }

        contacts.add(contact);
        System.out.println("The record added \n");
        return contact;
    }

    private PersonContact addPerson() {
        PersonContact contact = new PersonContact();
        System.out.println("Enter the name:");
        contact.setName(scanner.nextLine());
        System.out.println("Enter the surname:");
        contact.setSurname(scanner.nextLine());
        System.out.println("Enter the birth date:");
        contact.setBirthDate(scanner.nextLine());
        System.out.println("Enter the gender (M,F):");
        contact.setGender(scanner.nextLine());
        System.out.println("Enter the number:");
        contact.setNumber(scanner.nextLine());
        return contact;
    }

    private OrganizationContact addOrganization() {
        OrganizationContact contact = new OrganizationContact();
        System.out.println("Enter the organization name: ");
        contact.setName(scanner.nextLine());
        System.out.println("Enter the address: ");
        contact.setAddress(scanner.nextLine());
        System.out.println("Enter the number: ");
        contact.setNumber(scanner.nextLine());
        return contact;
    }


    @Override
    public void remove(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
        } else {
            list(contacts);
            System.out.println("Select record:");
            int recordId = Integer.parseInt(scanner.nextLine());
            contacts.remove(recordId - 1);
            System.out.println("The record removed!");
        }
    }

    @Override
    public void removeById(List<Contact> contacts, Integer id) {
        contacts.remove(id - 1);
        System.out.println("The record removed!");
    }

    @Override
    public Contact editById(List<Contact> contacts, Integer id) {
        Contact contact = contacts.get(id - 1);
        List<String> editableFields = contact.editableFields();

        if (!editableFields.isEmpty()) {
            System.out.println("Select a field (" + String.join(",", editableFields) + "):");
            String fieldName = scanner.nextLine();
            System.out.printf("Enter %s \n", fieldName);
            String fieldValue = scanner.nextLine();
            contact.editField(fieldName, fieldValue);

        }
        contacts.set(id - 1, contact);
        System.out.println("Saved");
        System.out.println(contact);
        return contact;
    }

    @Override
    public Contact edit(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
            return null;
        }

        list(contacts);

        System.out.println("Select a record");

        int recordId = Integer.parseInt(scanner.nextLine());
        Contact contact = contacts.get(recordId - 1);
        List<String> editableFields = contact.editableFields();

        if (!editableFields.isEmpty()) {
            System.out.println("Select a field (" + String.join(",", editableFields) + "):");
            String fieldName = scanner.nextLine();
            System.out.printf("Enter %s \n", fieldName);
            String fieldValue = scanner.nextLine();
            contact.editField(fieldName, fieldValue);

        }
        contacts.set(recordId - 1, contact);
        System.out.println("Saved");
        System.out.println(contact);

        return contact;
    }


    @Override
    public void count(List<Contact> contacts) {
        System.out.printf("The Phone Book has %d records.\n", contacts.size());
    }

    @Override
    public List<Contact> list(List<Contact> contacts) {
        int i = 1;
        for (Contact contact : contacts) {
            System.out.printf("%d. %s\n", i, contact.getFullName());
            i++;
        }

        return contacts;
    }

    @Override
    public List<Contact> listMenu(List<Contact> contacts) {
        list(contacts);
        boolean stillList = true;
        while (stillList) {
            System.out.println("[list] Enter action ([number], back)");
            String listAction = scanner.nextLine();
            switch (listAction) {
                case "back" -> stillList = false;
                default -> {
                    if (listAction.matches("\\d")) {
                        stillList = recordMenu(contacts, Integer.parseInt(listAction));
                    }
                }

            }
        }
        return contacts;
    }

    private boolean recordMenu(List<Contact> contacts, Integer id) {
        infoById(contacts, id);
        boolean stillRecord = true;
        while (stillRecord) {
            System.out.println("[record] Enter action (edit, delete, menu): ");
            String recordAction = scanner.nextLine();
            System.out.println();
            switch (recordAction) {
                case "edit" -> editById(contacts, id);
                case "delete" -> removeById(contacts, id);
                case "menu", "exit" -> {
                    stillRecord = false;
                }
            }
        }
        return stillRecord;
    }

    @Override
    public List<Contact> info(List<Contact> contacts) {
        list(contacts);
        System.out.println("Enter index to show info: ");
        int recordId = Integer.parseInt(scanner.nextLine());
        Contact contact = contacts.get(recordId - 1);
        System.out.println(contact + "\n");
        return contacts;
    }

    @Override
    public Contact infoById(List<Contact> contact, Integer id) {
        Contact foundContact = contact.get(id - 1);
        System.out.println(foundContact + "\n");
        return foundContact;
    }

    @Override
    public void search(List<Contact> contacts) {
        String query = getSearchQuery();
        List<Contact> foundContacts = searchContacts(contacts, query);
        showSearchResults(foundContacts);

        System.out.println("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        boolean stillSearch = true;
        while (stillSearch)
            switch (action) {
                case "again" -> search(contacts);
                case "back", "exit" -> stillSearch = false;
                default -> {
                    if (action.matches("\\d")) {
                        stillSearch = recordMenu(contacts, Integer.parseInt(action));
                    }
                }
            }
    }

    private String getSearchQuery() {
        System.out.println("Enter search query: ");
        return scanner.nextLine();
    }

    private void showSearchResults(List<Contact> contacts) {
        System.out.printf("Found %d results: \n", contacts.size());
        list(contacts);
    }

    private List<Contact> searchContacts(List<Contact> contacts, String query) {
        Pattern pattern = Pattern.compile("(?i).*" + query + ".*");
        return contacts.stream()
                .filter(contact -> pattern.matcher(contact.editableValues().toString()).matches())
                .collect(Collectors.toList());
    }
}
