package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.DogDAO;
import cz.muni.fi.pa165.entities.Dog;
import cz.muni.fi.pa165.exceptions.DAOException;
import cz.muni.fi.pa165.exceptions.ServiceException;

import java.util.List;

/**
 * {@inheritDoc}
 *
 * @author Denis Richtarik
 */
public class DogServiceImpl implements DogService {

    private final DogDAO dogDAO;

    public DogServiceImpl(DogDAO dogDAO) {
        this.dogDAO = dogDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Dog dog) throws ServiceException {
        if (dog == null)
            throw new IllegalArgumentException("Dog is null");

        try {
            this.dogDAO.create(dog);
        } catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dog getById(long id) throws ServiceException {
        if (id < 0)
            throw new IllegalArgumentException("ID cannot be less than 0");

        return this.dogDAO.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dog> getAll() throws ServiceException {
        try {
            return dogDAO.getAll();
        } catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Dog dog) throws ServiceException {
        if (dog == null)
            throw new IllegalArgumentException("Dog is null");

        try {
            dogDAO.update(dog);
        } catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Dog dog) throws ServiceException {
        if (dog == null)
            throw new IllegalArgumentException("Dog is null");

        try {
            dogDAO.delete(dog);
        } catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }
    }
}
