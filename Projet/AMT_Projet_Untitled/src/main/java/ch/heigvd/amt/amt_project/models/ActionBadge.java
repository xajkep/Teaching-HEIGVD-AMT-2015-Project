package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;

/**
 *
 * @author YounTheory
 */

@Entity
public class ActionBadge extends ActionType{
    private Badge badge;
    
    public ActionBadge() {}

    public ActionBadge(Badge badge, String name) {
        super(name);
        this.badge = badge;
    }
    /**
     * @return the badge
     */
    public Badge getBadge() {
        return badge;
    }

    /**
     * @param badge the badge to set
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    
}
