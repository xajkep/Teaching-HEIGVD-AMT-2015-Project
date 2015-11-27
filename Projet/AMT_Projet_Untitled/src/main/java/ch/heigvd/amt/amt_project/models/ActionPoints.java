package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;

/**
 *
 * @author YounTheory
 */

@Entity
public class ActionPoints extends ActionType {
    private long point;
    
    public ActionPoints() {}

    public ActionPoints(long point, String name) {
        super(name);
        this.point = point;
    }
    /**
     * @return the point
     */
    public long getPoints() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoints(long point) {
        this.point = point;
    }
    
}
