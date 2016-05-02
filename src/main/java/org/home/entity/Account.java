package org.home.entity;

import org.home.media.MediaCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by bitybite on 5/2/16.
 */
public class Account {

    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(Account.class);

    public final static Account DEFAULT_ACCOUNT = initDefaultAccount();
    private int id;
    private String userName;
    private String password;
    private String email;
    private String fname;
    private String lname;
    private boolean isDefaultAccount = false;
    private List<MediaCollection> mediaCollections;

    private static Account initDefaultAccount(){
        if(DEFAULT_ACCOUNT == null){
            return new Account()
                    .setId(1)
                    .setUserName("default_user")
                    .setPassword("default_pass")
                    .setEmail("default_email")
                    .setFname("Jack")
                    .setLname("Johnson")
                    .setIsDefaultAccount(true)
                    .setMediaCollections(Collections.EMPTY_LIST);
        }else{
            return DEFAULT_ACCOUNT;
        }
    }

    private Account setIsDefaultAccount(boolean isDefaultAccount) {
        this.isDefaultAccount = isDefaultAccount;
        return this;
    }

    public int getId() {
        return id;
    }

    public Account setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Account setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public Account setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public Account setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public boolean isDefaultAccount() {
        return isDefaultAccount;
    }

    public List<MediaCollection> getMediaCollections() {
        return mediaCollections;
    }

    public Account setMediaCollections(List<MediaCollection> mediaCollections) {
        this.mediaCollections = mediaCollections;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", isDefaultAccount=" + isDefaultAccount +
                '}';
    }
}
