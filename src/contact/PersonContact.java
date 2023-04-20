package contact;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonContact extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

    @Override
    public List<String> editableFields() {
        return List.of("name", "surname", "birth", "gender", "number");
    }

    @Override
    public List<String> editableValues() {
        List<String> fields = new ArrayList<>();
        fields.add(this.getName());
        fields.add(this.getSurname());
        fields.add(this.getNumber());
        return fields;
    }

    @Override
    public void editField(String field, String newValue) {
        switch (field.toLowerCase()) {
            case "name" -> setName(newValue);
            case "surname" -> setSurname(newValue);
            case "birthdate" -> setBirthDate(newValue);
            case "gender" -> setGender(newValue);
            case "number" -> setNumber(newValue);
            default -> System.out.println("Invalid field name: " + field);
        }
        setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
    }

    @Override
    public String getFullName() {
        return this.getName() + " " + this.getSurname();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.isEmpty()) {
            System.out.println("Bad surname!");
            this.surname = "[no data]";
        } else {
            this.surname = surname;
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate.isEmpty()) {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        } else {
            this.birthDate = birthDate;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.isEmpty()) {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        } else {
            this.gender = gender;
        }
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getCreated() + "\n" +
                "Time last edit: " + super.getLastEdit();

    }
}
