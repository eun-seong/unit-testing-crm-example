package com.dnd.namuiwiki.crm;

public interface MessageBus {
    void sendEmailChangedMessage(int userId, String newEmail);
}
