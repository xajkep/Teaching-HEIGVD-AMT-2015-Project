package ch.heigvd.amt.amt_project.rest.dto;

import java.util.List;

/**
 *
 * @author thsch, mberthouzoz
 */
public class EndUserReputationDTO {
    
    private long points;
    private List<BadgeDTO> badges;
    
    public long getPoints(){
        return points;
    }
    
    public List<BadgeDTO> getBadge(){
        return badges;
    }
    
    public void setPoints(long points){
        this.points = points;
    }
    
    public void setBadges(List<BadgeDTO> badges){
        this.badges = badges;
    }
}
