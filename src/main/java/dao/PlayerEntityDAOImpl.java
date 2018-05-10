package dao;

/*-
 * #%L
 * BlackJack
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PlayerEntityDAOImpl implements PlayerEntityDAO {

    /**
     * Logger instance for logging.
     *
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
     *
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
     * @param myname the name of the entity.
     *
     * @return credit of the player.
     * */
    @Override
    public Integer findPlayersCredit(String myname) {
        try {
            Query query = DBManager.entityManager.createNamedQuery("PlayerEntity.findPlayersCredit");
            query.setParameter("myname", myname);

            int entity = (int) query.getSingleResult();

            return entity;


        } catch (NoResultException e) {
            LOGGER.error("Entity not found by name.");
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
     * @param entity we want to save.
     *
     * @return saved entity.
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

    /**
     * Save a PlayerEntity in the database:
     *      -If the entity's Id is {@code null} then create new entity (persist).
     *      -Any other case we need merge.
     *
     * @param myname name of the entity to find.
     *
     * @return saved entity.
     */
    @Override
    public PlayerEntity findPlayerbyName(String myname) {
        try {
            Query query = DBManager.entityManager.createNamedQuery("PlayerEntity.findPlayerbyName");
            query.setParameter("myname", myname);

            PlayerEntity entity = (PlayerEntity) query.getSingleResult();

            return entity;


        } catch (NoResultException e) {
            LOGGER.error("Entity not found by name.");
            return null;
        }
    }
}
