package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlType(propOrder = { "username"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;

    @Basic
    private int password;

    private String salt;
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return this.password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}