package com.project.projectversion1;

public class user {

    public String name, email;
    public String tgllahir,tempatlahir;
    public String nik;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public user()
    {

    }

    public user(String Name, String Email,String tgllahir,String tempatlahir,String nik)
    {
        this.name = Name;
        this.email = Email;
        this.tgllahir = tgllahir;
        this.tempatlahir = tempatlahir;
        this.nik = nik;

    }
}
