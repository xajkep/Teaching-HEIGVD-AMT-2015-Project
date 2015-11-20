package ch.heigvd.amt.amt_project.rest.resources;

import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.DTO.BadgeDTO;
import ch.heigvd.amt.amt_project.rest.DTO.EndUserReputationDTO;
import ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author thsch
 */
@Stateless
@Path("users")
public class UserResource {

    @Context
    UriInfo uriInfo;

    @EJB
    EndUsersDAOLocal endUsersDAO;

    public UserResource() {
    }

    @GET
    @Path("{endUserID}/reputation")
    public EndUserReputationDTO getEndUserReputation(@PathParam("endUserID") long endUserID) {
        EndUser endUser = endUsersDAO.findById(endUserID);

        return toDTO(endUser);
    }

    private EndUserReputationDTO toDTO(EndUser endUser) {
        try {
            EndUserReputationDTO dto = new EndUserReputationDTO();
            dto.setPoints(endUsersDAO.getPoints(endUser.getId()));
            List<BadgeDTO> badgeList = new ArrayList<>();

            for (BadgeAward badgeAward : endUser.getBadgeAwards()) {
                BadgeDTO badgeDTO = new BadgeDTO();
                Badge badge = badgeAward.getBadge();
                badgeDTO.setDescription(badge.getDescription());
                badgeDTO.setPicture(badge.getPicture());

                URI badgeHref = uriInfo
                        .getAbsolutePathBuilder()
                        .path(BadgeResource.class, "getBadge")
                        .build(badge.getId());
                badgeDTO.setHref(badgeHref);

                badgeList.add(badgeDTO);
            }
            return dto;
        } catch (BusinessDomainEntityNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
