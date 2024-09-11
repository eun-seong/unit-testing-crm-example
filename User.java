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

    public void changeEmail(String newEmail, Company company) {
        if (this.email.equals(newEmail)) {
            return;
        }

        // 의사결정 지점 1 : 사용자 유형 식별
        UserType newType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;

        // 의사결정 지점 2 : 회사 직원 수 계산 방법
        if (this.type != newType) {
            int delta = (newType == UserType.Employee) ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = newEmail;
        this.type = newType;
    }

}
