package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PlayerEntityDAOImpl implements PlayerEntityDAO {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class.getName());

    /**
     * PlayerEntityDAOImpl instance.
     * */
    private static final PlayerEntityDAOImpl playerEntityDAOimpl = new PlayerEntityDAOImpl();

    /**
     * Method for get the PlayerEntityDAOImpl object.
     *
     * @return playerEntityDAOimpl object.
     * */
    public static PlayerEntityDAOImpl getPlayerEntityDAOImpl(){
        return playerEntityDAOimpl;
    }

    /**
     * private constructor.
     * */
    private PlayerEntityDAOImpl(){}


    /**
     * Method to find a player's credit by Id.
     *
     * @param Id
     *
     * @return credit of the player.
     * */
    @Override
    public Integer findPlayersCredit(Long Id) {
        try {
            Query query = DBManager.entityManager.createNamedQuery("PlayerEntity.findPlayersCredit");
            query.setParameter("Id", Id);

            int entity = (int) query.getSingleResult();

            return entity;


        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Delete a PlayerEntity from the database.
     *
     * @param entity we want to delete.
     */
    @Override
    public void delete(PlayerEntity entity) {
        if(entity == null){
            throw new IllegalArgumentException("Entity is null!");
        }

        PlayerEntity delEntity = DBManager.entityManager.find(PlayerEntity.class, entity.getId());

        DBManager.entityManager.getTransaction().begin();
        DBManager.entityManager.remove(delEntity);
        DBManager.entityManager.getTransaction().commit();
    }

    /**
     * Save a PlayerEntity in the database:
     *      -If the entity's Id is {@code null} then create new entity (persist).
     *      -Any other case we need merge.
     *
     * @param entity to save.
     *
     * @return  saved entity.
     */
    @Override
    public PlayerEntity save(PlayerEntity entity) {
        if(entity == null){
            throw new IllegalArgumentException("Entity is null!");
        }

        DBManager.entityManager.getTransaction().begin();

        if(entity.getId() == null){
            DBManager.entityManager.persist(entity);
        } else { DBManager.entityManager.merge(entity);}

        DBManager.entityManager.getTransaction().commit();

        return entity;
    }
}
