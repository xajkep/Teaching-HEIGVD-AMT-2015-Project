package ch.heigvd.amt.amt_project.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author YounTheory, mberthouzoz
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "ActionBadge.findByBadge", query = "SELECT ab FROM ActionBadge ab WHERE ab.badge.id = :badge")
})
public class ActionBadge extends ActionType{
    @ManyToOne
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
