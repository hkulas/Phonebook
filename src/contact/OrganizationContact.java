package contact;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrganizationContact extends Contact {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.isEmpty()) {
            System.out.println("Bad address!");
            this.address = "[bad data]";
        } else {
            this.address = address;
        }
    }

    @Override
    public List<String> editableFields() {
        return List.of("name", "address", "number");
    }

    @Override
    public List<String> editableValues() {
        List<String> fields = new ArrayList<>();
        fields.add(this.getName());
        fields.add(this.getAddress());
        fields.add(this.getNumber());
        return fields;
    }

    @Override
    public void editField(String field, String newValue) {
        switch (field.toLowerCase()) {
            case "name" -> setName(newValue);
            case "address" -> setAddress(newValue);
            case "number" -> setNumber(newValue);
            default -> System.out.println("Invalid field name: " + field);
        }
        setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
    }

    @Override
    public String getFullName() {
        return this.getName();
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() + "\n" +
                "Address: " + address + "\n" +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getCreated() + "\n" +
                "Time last edit: " + super.getLastEdit();
    }
}
