package ch.heigvd.amt.amt_project.models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author YounTheory, mberthouzoz
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Account.login", query = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password")
})
public class Account extends AbstractDomainModel<Long>{
    /* Attributs */
    
    @Column(unique=true)
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String password;
    
    @ManyToMany(mappedBy = "accounts")
    private List<Role> roles;
    
    public Account() {
        
    }
    
    public Account(String email, String firstName, String lastName, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        try {    
            this.password = dotHash(password);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param password the string password not hashed
     * @return the password hashed
     */
    public String dotHash(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       digest.reset();
       digest.update(this.email.getBytes());
       return digest.digest(password.getBytes("UTF-8")).toString();
 }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        try {    
            this.password = dotHash(password);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the Role to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    /**
     * Add a role to Account
     * @param role 
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    /**
     * Remove a role to Account
     * @param role 
     */
    public void removeRole(Role role) {
        if (roles.contains(role)) {
            this.roles.remove(role);
        }
    }
    
    
}
