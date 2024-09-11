package com.dnd.namuiwiki.crm;

public class User {
    private int userId;
    private String email;
    private UserType type;

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {
        return type;
    }

    public void changeEmail(int userId, String newEmail) {
        Object[] data = Database.getUserById(userId);
        this.userId = userId;
        this.email = (String) data[1];
        this.type = (UserType) data[2];

        if (this.email.equals(newEmail)) {
            return;
        }

        Object[] companyData = Database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        String emailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = emailDomain.equals(companyDomainName);

        // 의사결정 지점 1 : 사용자 유형 식별
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        // 의사결정 지점 2 : 회사 직원 수 계산 방법
        if (this.type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            int newNumber = numberOfEmployees + delta;
            Database.saveCompany(newNumber);
        }

        this.email = newEmail;
        this.type = newType;

        Database.saveUser(this);
        MessageBus.sendEmailChangedMessage(this.userId, newEmail);
    }

}
