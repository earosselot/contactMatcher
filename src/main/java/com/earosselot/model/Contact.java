package com.earosselot.model;

import java.util.Map;

public class Contact {

    private final Integer id;
    private final Map<FieldName, Field> fields;

    public Contact(Builder builder) {
        this.id = builder.id;
        this.fields = Map.of(
                FieldName.FIRST_NAME, new Field(builder.firstName),
                FieldName.LAST_NAME, new Field(builder.lastName),
                FieldName.EMAIL, new Field(builder.email),
                FieldName.ZIP_CODE, new Field(builder.zipCode),
                FieldName.ADDRESS, new Field(builder.address)
        );
    }

    public boolean hasSame(FieldName fieldName, Contact otherContact) {
        if (fieldName == null) throw new IllegalArgumentException("Field Name cannot be null");
        if (otherContact == null) return false;
        if (getField(fieldName).isEmpty()) return false;
        return otherContact.hasSame(fieldName, getField(fieldName));
    }

    private boolean hasSame(FieldName fieldName, Field field) {
        return getField(fieldName).equals(field);
    }

    public boolean hasSimilar(FieldName fieldName, Contact otherContact, int threshold) {
        if (fieldName == null) throw new IllegalArgumentException("Field Name cannot be null");
        return otherContact.hasSimilar(fieldName, getField(fieldName), threshold);
    }

    private boolean hasSimilar(FieldName fieldName, Field otherField, int threshold) {
        return otherField.isSimilar(getField(fieldName), threshold);
    }

    private Field getField(FieldName fieldName) {
        return this.fields.get(fieldName);
    }

    public boolean canBeInitial(FieldName fieldName, Contact otherContact) {
        if (fieldName == null) throw new IllegalArgumentException("Field Name cannot be null");
        if (otherContact == null) return false;
        return otherContact.canBeInitial(fieldName, getField(fieldName));
    }

    private boolean canBeInitial(FieldName fieldName, Field otherField) {
        Field field = getField(fieldName);
        if (field.isInitial() || otherField.isInitial())
            return otherField.sameInitial(field);
        return false;
    }

    public Integer getId() {
        return this.id;
    }

    public static class Builder {

        private String firstName = "", lastName = "", email = "", zipCode = "", address = "";
        private final Integer id;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }


}
