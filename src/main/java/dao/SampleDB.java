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

import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;

@Slf4j
public class SampleDB {

    private static final SampleDB DP_INSTANCE = new SampleDB();
    private EntityManager entityManager;

    private SampleDB(){}

    public boolean connected() {

        return entityManager != null && entityManager.isOpen();
    }

    public static SampleDB getDpInstance(){
        return DP_INSTANCE;
    }

    public void connectDB() throws Exception{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bjDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void disconnectDB(){
        entityManager.close();
        entityManager = null;
    }

    public PlayerEntity save(PlayerEntity entity){
        if(!connected()){
            throw new IllegalStateException();
        }

        if(entity == null){
            throw new IllegalArgumentException();
        }

        entityManager.getTransaction().begin();

        if(entity.getId() == null){
            entityManager.persist(entity); //insert entity to table
        } else { entityManager.merge(entity);} //update

        entityManager.getTransaction().commit();

        return entity;
    }

    public AiEntity save(AiEntity entity){
        if(!connected()){
            throw new IllegalStateException();
        }

        if(entity == null){
            throw new IllegalArgumentException();
        }

        entityManager.getTransaction().begin();

        if(entity.getId() == null){
            entityManager.persist(entity); //insert entity to table
        } else { entityManager.merge(entity);} //update

        entityManager.getTransaction().commit();

        return entity;
    }

    public void delete(PlayerEntity entity){
        if(!connected()){
            throw new IllegalStateException();
        }

        if(entity == null){
            throw new IllegalArgumentException();
        }

        PlayerEntity delEntity = entityManager.find(PlayerEntity.class, entity.getId());

        entityManager.getTransaction().begin();
        entityManager.remove(delEntity);
        entityManager.getTransaction().commit();
    }

    public PlayerEntity findPlayersCredit(Long id) throws IllegalStateException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        if (id == null) {
            return null;
        }

        try {
            Query query = entityManager.createNamedQuery("PlayerEntity.findPlayersCredit");
            query.setParameter("Id", id);
            PlayerEntity entity = (PlayerEntity) query.getSingleResult();

            return entity;

        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new Exception("JPA hiba!", e);
        }
    }

    public AiEntity findAisCredit(Long id) throws IllegalStateException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        if (id == null) {
            return null;
        }

        try {
            Query query = entityManager.createNamedQuery("AiEntity.findAisCredit");
            query.setParameter("Id", id);
            AiEntity entity = (AiEntity) query.getResultList();

            return entity;

        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new Exception("JPA hiba!", e);
        }
    }

    public Integer findHighScore() throws IllegalStateException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        try {
            Query query = entityManager.createNamedQuery("PlayerEntity.findMaxCredit");

            int maximum = (int) query.getSingleResult();

            return maximum;

        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            //log.error("JPA lekérdezési hiba!");
            throw new Exception("JPA hiba!", e);
        }
    }
}
