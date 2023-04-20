package service;

import contact.Contact;

import java.util.List;

public interface PhoneBookService {
    Contact add(List<Contact> contacts);

    void remove(List<Contact> contacts);

    void removeById(List<Contact> contacts, Integer id);

    Contact edit(List<Contact> contacts);

    Contact editById(List<Contact> contacts, Integer id);

    void count(List<Contact> contacts);

    List<Contact> list(List<Contact> contacts);

    List<Contact> listMenu(List<Contact> contacts);

    List<Contact> info(List<Contact> contacts);

    Contact infoById(List<Contact> contacts, Integer id);

    void search(List<Contact> contacts);

}

