package com.example.oopsassgn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({"pid", "postBody", "date", "user", "comm"})
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PID;
    private String postBody;
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userID")
    private UserSetup userSetup;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> Comm;


    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserSetup getUser() {
        return userSetup;
    }

    public void setUser(UserSetup userSetup) {
        this.userSetup = userSetup;
    }

    public List<Comment> getComm() {
        return Comm;
    }

    public void setComm(List<Comment> comm) {
        this.Comm = comm;
    }
}
