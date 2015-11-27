package ch.heigvd.amt.amt_project.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author YounTheory
 */

@Entity
public class Rule extends AbstractDomainModel<Long>{
    @ManyToOne
    private EventType eventType;
    /*
    Il faut rajouter actionType qui peut être soit un actionbadge ou actionpoints
    @ManyToOne
    private ActionType actionType;
    */
    @OneToMany
    private List<RuleProperties> properties;

    public Rule(EventType eventType, List<RuleProperties> properties)
    {
        this.eventType = eventType;
        //this.actionType = actionType;
        this.properties = properties;
    }
    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    
     /**
     * @return the properties
     */
    public List<RuleProperties> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(List<RuleProperties> properties) {
        this.properties = properties;
    }
    
    
    
    
}
