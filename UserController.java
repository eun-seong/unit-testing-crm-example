package com.dnd.namuiwiki.crm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserController {

    private final Database database;
    private final MessageBus messageBus;

    public void changeEmail(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        int newNumberOfEmployees = user.changeEmail(newEmail, companyDomainName, numberOfEmployees);

        database.saveCompany(newNumberOfEmployees);
        database.saveUser(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}
