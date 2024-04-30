package com.example.oopsassgn;

public class UserDTO {
    private int UID;
    private String Username;
    private String MailID;

    public UserDTO(int UID, String name, String MailID) {
        this.UID = UID;
        this.Username = name;
        this.MailID = MailID;
    }


    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getMailID() {
        return MailID;
    }

    public void setMailID(String mailID) {
        this.MailID = mailID;
    }
}
