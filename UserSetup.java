package com.example.oopsassgn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"user\"")
@JsonSerialize(using = UserSetupSerializer.class)
public class UserSetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    private String mailID;
    private String Username;
    @JsonIgnore
    private String PsswD;

    @JsonIgnore
    @OneToMany(mappedBy = "userSetup", cascade = CascadeType.ALL)
    private List<Post> posts;
    public UserSetup() {

    }
    public UserSetup(int UID, String Username, String mailID) {
        this.UID = UID;
        this.Username = Username;
        this.mailID = mailID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getMailID() {
        return mailID;
    }

    public void setMailID(String MailID) {
        this.mailID = MailID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String name) {
        this.Username = name;
    }

    public String getPsswD() {
        return PsswD;
    }

    public void setPsswD(String Psswd) {
        this.PsswD = Psswd;
    }


}




