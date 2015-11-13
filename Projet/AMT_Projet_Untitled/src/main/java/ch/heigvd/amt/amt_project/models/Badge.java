package ch.heigvd.amt.amt_project.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author xajkep
 */
@Entity
public class Badge extends AbstractDomainModel<Long>{
    
    private String description;
    
    private String picture;
    
    @OneToMany(mappedBy = "badge")
    private List<BadgeAward> badgeAwards;
    
    public Badge() {}
    
    public Badge(String desc, String pic) {
        this.description = desc;
        this.picture = pic;
    }

    /**
     * @return the badge description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the badge description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the badge picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the badge picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return badge award list
     */
    public List<BadgeAward> getBadgeAwards() {
        return badgeAwards;
    }

    /**
     * @param badgeAwards list
     */
    public void setBadgeAwards(List<BadgeAward> badgeAwards) {
        this.badgeAwards = badgeAwards;
    }

    
    
}
