package com.dnd.namuiwiki.crm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Company {
    private int numberOfEmployees;
    private String domainName;

    public String getDomainName() {
        return domainName;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    // 직원 수 변경
    public void changeNumberOfEmployees(int delta) {
        if (numberOfEmployees + delta < 0) {
            throw new IllegalArgumentException("Number of employees cannot be negative.");
        }
        numberOfEmployees += delta;
    }

    // 이메일이 회사 도메인인지 확인
    public boolean isEmailCorporate(String email) {
        String emailDomain = email.split("@")[1];
        return emailDomain.equals(domainName);
    }
}
