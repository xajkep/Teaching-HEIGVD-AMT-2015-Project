package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;

/**
 *
 * @author YounTheory
 */
@Entity
public abstract class ActionType extends AbstractDomainModel<Long>{
    private String name;
    
    public ActionType() {}
    
    public ActionType(String name) {
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