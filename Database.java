package com.dnd.namuiwiki.crm;

public interface Database {
    Object[] getUserById(int userId);

    Object[] getCompany();

    void saveCompany(int newNumber);

    void saveUser(User user);
}
