package com.dnd.namuiwiki.crm;

public class UserFactory {
    public static User create(Object[] data) {
        if (data.length < 3) {
            throw new IllegalArgumentException("Insufficient data to create a User");
        }

        int id = (int) data[0];
        String email = (String) data[1];
        UserType type = (UserType) data[2];

        return new User(id, email, type);
    }
}
