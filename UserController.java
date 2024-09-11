package com.dnd.namuiwiki.crm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserController {

    private final Database database;
    private final MessageBus messageBus;

    public void changeEmail(int userId, String newEmail) {
        Object[] userData = database.getUserById(userId);
        User user = UserFactory.create(userData);

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        user.changeEmail(newEmail, company);

        database.saveCompany(company);
        database.saveUser(user);

        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}
