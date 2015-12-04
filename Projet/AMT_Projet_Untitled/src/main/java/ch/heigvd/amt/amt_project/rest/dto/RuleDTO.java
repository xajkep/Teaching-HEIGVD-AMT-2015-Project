package ch.heigvd.amt.amt_project.rest.dto;

/**
 *
 * @author xajkep
 */
public class RuleDTO {    
    private RuleConditionDTO condition;
    private RuleActionDTO action;

    public RuleConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(RuleConditionDTO condition) {
        this.condition = condition;
    }

    public RuleActionDTO getAction() {
        return action;
    }

    public void setAction(RuleActionDTO action) {
        this.action = action;
    }
}
