package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;

/**
 *
 * @author YounTheory
 */

@Entity
public class RuleProperties extends AbstractDomainModel<Long>{
    
    private String name;
    private String value;
    
    public RuleProperties(String name, String value)
    {
        this.name = name;
        this.value = value;
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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    
}
