package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Dog;
import cz.muni.fi.pa165.entities.Order;
import cz.muni.fi.pa165.entities.Service;
import cz.muni.fi.pa165.exceptions.DAOException;

import java.util.List;

/**
 * Data access object for retrieving {@link Order} objects from database.
 *
 * @author Martin Vrábel
 * @version 31.10.2016 0:03
 */
public interface OrderDAO {

    /**
     * Creates new entry in database from provided {@link Order} object
     *
     * @param order         {@link Order} object to save
     * @throws DAOException if {@link Order} already exists in database or if saving to database fails
     */
    void create(Order order) throws DAOException;

    /**
     * Retrieves an {@link Order} object with provided <b>ID</b> from database
     *
     * @param id            <b>ID</b> number of {@link Order} to retrieve
     * @return              found {@link Order} object
     * @throws DAOException if {@link Order} with provided <b>ID</b> not found in database
     */
    Order getById(long id) throws DAOException;

    /**
     * Retrieves all {@link Order} objects from database
     *
     * @return              list of all {@link Order} objects from database
     * @throws DAOException when some error occurs during getting {@link Order} objects from database
     */
    List<Order> getAll() throws DAOException;

    /**
     * Retrieves all {@link Order} objects from database relating to provided {@link Dog} object
     *
     * @param dog           {@link Dog} object which relates to searched {@link Order} objects
     * @return              list of all {@link Order} objects from database relating to provided {@link Dog} object
     * @throws DAOException when some error occurs during getting {@link Order} objects from database
     */
    List<Order> getByDog(Dog dog) throws DAOException;

    /**
     * Retrieves all {@link Order} objects from database relating to provided {@link Service} object
     *
     * @param service       {@link Service} object which relates to searched {@link Order} objects
     * @return              list of all {@link Order} objects from database relating to provided {@link Service} object
     * @throws DAOException when some error occurs during getting {@link Order} objects from database
     */
    List<Order> getByService(Service service) throws DAOException;

    /**
     * Updates attributes of an existing {@link Order} object in database
     *
     * @param order         {@link Order} object with updated attributes}
     * @throws DAOException when update of {@link Order} object in database fails
     */
    void update(Order order) throws DAOException;

    /**
     * Deletes an existing {@link Order} entry from database
     *
     * @param order         {@link Order} object to delete from database
     * @throws DAOException when deleteing of {@link Order} object in database fails
     */
    void delete(Order order) throws DAOException;
}
