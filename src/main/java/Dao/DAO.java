package Dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface DAO<E, ID extends Serializable> {

	
	/**
	 * Persist an entity.
	 *
	 * @param entity the entity to persist
	 * @param entityManager the entityManager
	 * @return the persisted entity or null if duplicate key
	 */
	public E persist(E entity, EntityManager entityManager);
	
	/**
	 * Update an entity.
	 * 
	 * @param entity the entity to persist
	 * @param entityManager the entityManager
	 * @return the merged entity. Create a new entity if doesn't exist
	 */
	public E merge(E entity, EntityManager entityManager);
	
	/**
	 * Update an entity.
	 * 
	 * @param entity the entity to persist
	 * @param entityManager the entityManager
	 * @return the merged entity. Create a new entity if doesn't exist
	 */
	public EntityManager getEntityManager();
	
	/**
	 * Update entity given its id and an object with new values.
	 *
	 * @param id the id of the entity to update
	 * @param newEntityValues an object with the new values to update the entity 
	 * @param entityManager the entityManager
	 * @return the updated entity or null if the entity id does not exist
	 */
	public E update(ID id,E newEntityValues, EntityManager entityManager);

	/**
	 * Find entity by id.
	 *
	 * @param id the id of the entity to find
	 * @param entityManager the entityManager
	 * @return the found entity or null if the entity id does not exist
	 */
	public E findById(ID id, EntityManager entityManager);

	/**
	 * Find all entities.
	 * @param entityManager the entityManager
	 * @return the list of entities
	 */
	public List<E> findAll(EntityManager entityManager);

	/**
	 * Delete an entity given its id.
	 *
	 * @param id the id of the entity to delete
	 * @param entityManager the entityManager
	 * @return true, if deleted. false, if entity id does not exist
	 */
	public boolean delete(ID id, EntityManager entityManager);
	
}