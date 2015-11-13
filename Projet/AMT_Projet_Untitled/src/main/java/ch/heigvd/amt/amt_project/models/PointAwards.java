package ch.heigvd.amt.amt_project.models;

import ch.heigvd.amt.amt_project.models.AbstractDomainModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Michaël
 */
@Entity
public class PointAwards extends AbstractDomainModel<Long>{
   
    @ManyToOne
    private EndUser endUser;
    
    private int point;
    
    private Date date;
    
    public PointAwards() {
        
    }
    
    public PointAwards(EndUser endUser, int point) {
        this.endUser = endUser;
        this.point = point;
        this.date = new Date();
    }

    public EndUser getEndUser() {
        return endUser;
    }

    public void setEndUser(EndUser endUser) {
        this.endUser = endUser;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }    
}
