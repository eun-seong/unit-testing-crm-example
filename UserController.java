package com.dnd.namuiwiki.crm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserController {

    private final Database database;
    private final MessageBus messageBus;

    public void changeEmail(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        String email = (String) data[1];
        UserType type = (UserType) data[2];

        User user = new User(userId, email, type);

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        int newNumberOfEmployees = user.changeEmail(newEmail, companyDomainName, numberOfEmployees);

        database.saveCompany(newNumberOfEmployees);
        database.saveUser(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}
