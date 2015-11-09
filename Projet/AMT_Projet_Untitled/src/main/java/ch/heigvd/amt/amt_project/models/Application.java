
package ch.heigvd.amt.amt_project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * This class implements the Application domain model object.
 *
 * @author YounTheory, mberthouzoz, xajkep
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Application.findAllByUserId", query = "SELECT a FROM Application a WHERE a.creator.id = :user"),
})
public class Application extends AbstractDomainModel<Long> {
    
    @Column(unique=true)
    private String name;
    
    private String description;
    
    @OneToOne
    private ApiKey key;
    
    private Boolean enable;
    
    @ManyToOne
    private Account creator;
    
    public Application() {
        
    }

    public Application(String name, String description, ApiKey key, Boolean enable, Account creator )
    {
        this.key = key;
        this.name = name;
        this.description = description;
        this.enable = enable;
        this.creator = creator;
    }
    /**
     * @return the key
     */
    public ApiKey getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(ApiKey key) {
        this.key = key;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * @return the creator
     */
    public Account getCreator() {
        return creator;
    }

    /**
     * @param creator the idCreator to set
     */
    public void setCreator(Account creator) {
        this.creator = creator;
    }
    
}
