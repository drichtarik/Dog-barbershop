package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ServiceDTO;
import cz.muni.fi.pa165.exceptions.DAOException;

import java.util.List;

/**
 * Facade object for retrieving {@link ServiceDTO} objects.
 *
 * @author Dominik Gmiterko
 */
public interface ServiceFacade {

    /**
     * Creates new entry from provided {@link ServiceDTO} object.
     *
     * @param service                   {@link ServiceDTO} object to save
     * @throws IllegalArgumentException when {@code facade} is {@code null}
     */
    void create(ServiceDTO service);

    /**
     * Retrieves a {@see ServiceDTO} object with provided <b>ID</b>.
     *
     * @param id                        <b>ID</b> number of {@link ServiceDTO} to retrieve
     * @return                          found {@link ServiceDTO} object or {@code null} if object with specified <b>ID</b> not found
     * @throws IllegalArgumentException if {@code id} is a negative number
     */
    ServiceDTO getById(long id);

    /**
     * Retrieves all {@link ServiceDTO} objects.
     *
     * @return              list with all {@link ServiceDTO} objects
     *                      or <b>empty list</b> if there are no entities
     */
    List<ServiceDTO> getAll();

    /**
     * Updates attributes of an existing {@link ServiceDTO} object.
     *
     * @param service                   {@link ServiceDTO} object with updated attributes
     * @throws IllegalArgumentException when {@code facade} is {@code null}
     */
    void update(ServiceDTO service);

    /**
     * Deletes an existing {@link ServiceDTO} entry.
     *
     * @param service                   {@link ServiceDTO} object to delete
     * @throws IllegalArgumentException when {@code facade} is {@code null}
     */
    void delete(ServiceDTO service);
}