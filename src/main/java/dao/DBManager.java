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

import javax.persistence.*;

/**
 * DBManager class is handling the JPA databse-connection.
 */
public class DBManager {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class.getName());

    /**
     * One instance of DBManager class.
     */
    private static final DBManager DP_INSTANCE = new DBManager();

    /**
     * EntityManager instance.
     */
    private EntityManager entityManager;

    /**
     * Private constructor..
     */
    private DBManager(){}

    /**
     * Say if the EntityManager is connected to the database.
     *
     * @return true, if it is.
     */
    public boolean connected() {

        return entityManager != null && entityManager.isOpen();
    }

    /**
     * Getter for DP_INSTANCE variable.
     */
    public static DBManager getDpInstance(){
        return DP_INSTANCE;
    }

    /**
     * Create the database-connection with JPA.
     *
     * @throws Exception in case JPA fault.
     */
    public void connectDB() throws Exception{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bjDB");
        entityManager = entityManagerFactory.createEntityManager();

        LOGGER.trace("Database connection is OK.. ");
    }

    /**
     * Close the databse-connection with JPA.
     *
     */
    public void disconnectDB(){
        entityManager.close();
        entityManager = null;

        LOGGER.trace("Disconnect is OK.. ");
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
    public PlayerEntity save(PlayerEntity entity){
        if(!connected()){
            throw new IllegalStateException("No connection!");
        }

        if(entity == null){
            throw new IllegalArgumentException("Entity is null!");
        }

        entityManager.getTransaction().begin();

        if(entity.getId() == null){
            entityManager.persist(entity);
        } else { entityManager.merge(entity);}

        entityManager.getTransaction().commit();

        return entity;
    }

    /**
     * Delete a PlayerEntity from the database.
     *
     * @param entity we want to delete.
     */
    public void delete(PlayerEntity entity){
        if(!connected()){
            throw new IllegalStateException("No connection!");
        }

        if(entity == null){
            throw new IllegalArgumentException("Entity is null!");
        }

        PlayerEntity delEntity = entityManager.find(PlayerEntity.class, entity.getId());

        entityManager.getTransaction().begin();
        entityManager.remove(delEntity);
        entityManager.getTransaction().commit();
    }


    public Integer findPlayersCredit(Long Id) {

        if (!connected()) {
            throw new IllegalStateException("No database connection!");
        }

        try {
            Query query = entityManager.createNamedQuery("PlayerEntity.findPlayersCredit");
            query.setParameter("Id", Id);

            int entitys = (int) query.getSingleResult();

            return entitys;


        } catch (NoResultException e) {
            return null;
        }
    }

}
