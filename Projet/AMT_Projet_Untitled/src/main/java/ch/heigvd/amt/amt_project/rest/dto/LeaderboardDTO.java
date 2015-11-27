package ch.heigvd.amt.amt_project.rest.dto;

import ch.heigvd.amt.amt_project.rest.dto.BadgeDTO;
import java.util.List;

/**
 *
 * @author YounTheory
 */
public class LeaderboardDTO {
    private String name;
    private long points;
    private List<BadgeDTO> badges;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the points
     */
    public long getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * @return the badges
     */
    public List<BadgeDTO> getBadges() {
        return badges;
    }

    /**
     * @param badges the badges to set
     */
    public void setBadges(List<BadgeDTO> badges) {
        this.badges = badges;
    }
    
    
}
