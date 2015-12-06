package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author YounTheory
 */

@Entity

@NamedQueries({
    @NamedQuery(name = "ActionPoints.findByPoints", query = "SELECT ap FROM ActionPoints ap WHERE ap.point = :points")
})
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
