package ch.heigvd.amt.amt_project.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author YounTheory, xajkep, mberthouzoz
 */

@Entity
@NamedQueries({
    @NamedQuery(name="Rule.findByEventType", query = "SELECT r FROM Rule r WHERE r.eventType = :eventType")
})
public class Rule extends AbstractDomainModel<Long>{
    @ManyToOne
    private EventType eventType;
    
    @ManyToOne
    private ActionType actionType;

    @OneToMany
    private List<RuleProperties> eventProperties;
   
    public List<RuleProperties> getEventProperties() {
        return eventProperties;
    }

    public void setEventProperties(List<RuleProperties> eventProperties) {
        this.eventProperties = eventProperties;
    }
    
    public Rule() {}

    public Rule(EventType eventType, List<RuleProperties> eventProperties, ActionType actionType) {
        this.eventType = eventType;
        this.actionType = actionType;
        this.eventProperties = eventProperties;
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
     * 
     * @return ActionType
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * 
     * @param actionType 
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
