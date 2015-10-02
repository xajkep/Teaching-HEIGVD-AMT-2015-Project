package ch.heigvd.amt.amt_project.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author YounTheory, mberthouzoz
 */
@Entity
public class Role extends AbstractDomainModel<Long>{
    @Column(unique=true)
    private String name;
    
    @ManyToMany
    private List<Account> accounts;
    
    public Role() {
        
    }
    
    public Role(String name, List<Account> accounts)
    {
        this.name = name;
        this.accounts = accounts;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the Account to set
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    /**
     * @param account the Account to add
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    
    /**
     * @param account the account to remove
     */
    public void removeAccount(Account account) {
        if (accounts.contains(account))
        {
            this.accounts.remove(account);
        }
    }
    
    
    
}
