package com.dnd.namuiwiki.crm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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

    public int changeEmail(String newEmail, String companyDomainName, int numberOfEmployees) {
        if (this.email.equals(newEmail)) {
            return numberOfEmployees;
        }

        String emailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = emailDomain.equals(companyDomainName);

        // 의사결정 지점 1 : 사용자 유형 식별
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;
        
        // 의사결정 지점 2 : 회사 직원 수 계산 방법
        if (this.type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            numberOfEmployees += delta;
        }

        this.email = newEmail;
        this.type = newType;

        return numberOfEmployees;
    }

}
