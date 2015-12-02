package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;

/**
 *
 * @author YounTheory
 */

@Entity
public class EventType extends AbstractDomainModel<Long>{
    
    private String name;
    
    public EventType() {}
    
    public EventType(String name)
    {
        this.name = name;
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
    
    
}
