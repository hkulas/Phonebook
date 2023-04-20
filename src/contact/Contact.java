package contact;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Contact {
    private String name;
    private String number;
    private final LocalDateTime created;
    private LocalDateTime lastEdit;


    public Contact() {
        this.created = LocalDateTime.now().withSecond(0).withNano(0);
        this.lastEdit = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public abstract List<String> editableFields();

    public abstract List<String> editableValues();

    public abstract void editField(String field, String newValue);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Bad name!");
            this.name = "[no data]";
        } else {
            this.name = name;
        }
    }

    public abstract String getFullName();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (NumberValidator.validateNumber(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }

    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

}
