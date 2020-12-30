package com.example.testrestapi;

public class Member {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String faculty;
    private String username;
    private String password;
    private int choice;
    private String role;

    public Member(String id, String firstname, String lastname, String email, String faculty, String username,String password, int c,String r) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.faculty = faculty;
        this.username = username;
        this.password = password;
        this.choice = c;
        this.role = r;
    }
    public String getId() {
        return id;
    }
    public void setId(String l) {
        this.id = l;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getChoice() {
        return choice;
    }
    public void setChoice(int choice) {
        this.choice = choice;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "account "+this.getFirstname()+"\n"
                +this.lastname+"\n"
                +this.getEmail()+"\n"
                +this.getFaculty();
    }
}
