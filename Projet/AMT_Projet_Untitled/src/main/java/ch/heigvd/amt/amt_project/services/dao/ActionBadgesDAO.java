package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.Badge;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class ActionBadgesDAO extends GenericDAO<ActionBadge, Long> implements ActionBadgesDAOLocal {

    @Override
    public ActionBadge findByBadge(Badge badge) throws BusinessDomainEntityNotFoundException {
        ActionBadge res = null;

        try {
            res = (ActionBadge) em.createNamedQuery("ActionBadge.findByBadge")
                    .setParameter("badge", badge.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }

        return res;
    }

}
