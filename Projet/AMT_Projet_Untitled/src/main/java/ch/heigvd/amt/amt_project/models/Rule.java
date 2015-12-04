package ch.heigvd.amt.amt_project.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author YounTheory, xajkep
 */

@Entity
public class Rule extends AbstractDomainModel<Long>{
    @ManyToOne
    private EventType eventType;
    
    @ManyToOne
    private ActionType actionType;

    @OneToMany
    private List<RuleProperties> eventProperties;
    
    @OneToMany
    private List<RuleProperties> actionProperties;

    public List<RuleProperties> getEventProperties() {
        return eventProperties;
    }

    public void setEventProperties(List<RuleProperties> eventProperties) {
        this.eventProperties = eventProperties;
    }

    public List<RuleProperties> getActionProperties() {
        return actionProperties;
    }

    public void setActionProperties(List<RuleProperties> actionProperties) {
        this.actionProperties = actionProperties;
    }
    
    public Rule() {}

    public Rule(EventType eventType, List<RuleProperties> eventProperties, ActionType actionType, List<RuleProperties> actionProperties) {
        this.eventType = eventType;
        this.actionType = actionType;
        this.eventProperties = eventProperties;
        this.actionProperties = actionProperties;
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
