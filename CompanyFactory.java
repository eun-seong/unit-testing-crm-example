package com.dnd.namuiwiki.crm;

public class CompanyFactory {
    public static Company create(Object[] data) {
        if (data.length < 2) {
            throw new IllegalArgumentException("Insufficient data to create a Company");
        }

        int id = (int) data[0];
        String name = (String) data[1];

        return new Company(id, name);
    }
}
