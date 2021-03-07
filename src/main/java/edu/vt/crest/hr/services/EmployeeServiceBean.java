package edu.vt.crest.hr.services;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.vt.crest.hr.entity.EmployeeEntity;

/**
 * Implements an EmployeeService
 */
@ApplicationScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmployeeServiceBean implements EmployeeService {

  @PersistenceContext
  private EntityManager em;

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public EmployeeEntity createEmployee(EmployeeEntity employee) throws Exception {
	  try {
		 em.persist(employee);
		 System.out.println("Created employee.");
		 return employee;
	  }
	  catch(Exception e) {
		  throw new PersistenceException("Employee not created "+e.getMessage()); 
	  }
	 
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  public EmployeeEntity findById(Long id) throws NoResultException{
	  try {
		  EmployeeEntity employee = em.find(EmployeeEntity.class, id);
		  if(employee != null) {
			  System.out.println("Found employee : " + employee.getFirstName());
			  return employee;
		  }
		  else {
			  throw new NoResultException();
			  }
		  } 
	  catch (NoResultException e) {
		  e.printStackTrace();
		  throw new NoResultException("Could not find employee with id: "+id);
		}
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  public List<EmployeeEntity> listAll(Integer startPosition, Integer maxResult) throws NoResultException {
	  try {
		  TypedQuery<EmployeeEntity> findAllQuery = em.createQuery(
					"SELECT DISTINCT e FROM Employee e ORDER BY e.id", EmployeeEntity.class);
		  
		  if (startPosition != null) {
				findAllQuery.setFirstResult(startPosition);
			}
			if (maxResult != null) {
				findAllQuery.setMaxResults(maxResult);
			}
			final List<EmployeeEntity> results = findAllQuery.getResultList();
			return results;
			}
	  catch (NoResultException e) {
		  e.printStackTrace();
		  throw new NoResultException("Error getting employees ");
		  }
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public EmployeeEntity update(Long id, EmployeeEntity employee) throws Exception {
	  try {
		  EmployeeEntity existingEmployee = em.find(EmployeeEntity.class, id);
			if(existingEmployee != null) {
				em.merge(employee);
				System.out.println("Updated employee : " + existingEmployee.getId());
			}
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException("Error updating department with id : " +id );
		}
  }
}
