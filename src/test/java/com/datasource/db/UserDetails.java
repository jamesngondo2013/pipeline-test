package com.datasource.db;

public class UserDetails {

    private String username;
    private String password;
    
    public UserDetails(String user, String pass){
        setUsername (user);
        setPassword (pass);
    }
    
    public UserDetails(){
        setUsername (username);
        setPassword (password);
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }
    
    
}
