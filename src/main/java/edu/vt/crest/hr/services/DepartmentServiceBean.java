package edu.vt.crest.hr.services;

import java.util.List;
import java.util.Set;

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

import edu.vt.crest.hr.entity.DepartmentEntity;
import edu.vt.crest.hr.entity.EmployeeEntity;

/**
 * Implements a DepartmentService
 */
@ApplicationScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DepartmentServiceBean implements DepartmentService {

  @PersistenceContext
  private EntityManager em;

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public DepartmentEntity createDepartment(DepartmentEntity department) throws Exception  {
	  try {
		  em.persist(department);
		  System.out.println("Created department.");
		  return department;
		  }
	  catch(Exception e) {
		  throw new PersistenceException("Department not created "+e.getMessage()); 
		  }
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  public DepartmentEntity findById(Long id) throws Exception {
	  try {
		  DepartmentEntity department = em.find(DepartmentEntity.class, id);
		  System.out.println("Found department : " + department.getIdentifier());

		  Set<EmployeeEntity> employees = department.getEmployees();
		  System.out.println("Employees in department : " + department.getIdentifier()+" : "+ employees);
		  return department;
		  } 
	  catch (Exception e) {
		  e.printStackTrace();
		  throw new NoResultException("Could not find department with id: "+id +e.getMessage());
		}
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  public List<DepartmentEntity> listAll(Integer startPosition, Integer maxResult) throws Exception {
	  try {
		  TypedQuery<DepartmentEntity> departmentListQuery = em.createQuery(
					"SELECT DISTINCT d.id, d.identifier, d.name FROM Department d ORDER BY d.id", DepartmentEntity.class);
		  
		  if (startPosition != null) {
			  departmentListQuery.setFirstResult(startPosition);
			}
			if (maxResult != null) {
				departmentListQuery.setMaxResults(maxResult);
			}
			final List<DepartmentEntity> departments = departmentListQuery.getResultList();
			/*
			 * for(DepartmentEntity dep : departments) { Set<EmployeeEntity> employees =
			 * dep.getEmployees(); System.out.println("Employees in department : " +
			 * dep.getIdentifier() + employees); }
			 */
			return departments;
			}
	  catch (Exception e) {
		  e.printStackTrace();
		  throw new Exception("Error getting departments " +e.getMessage());
		  }
  }

  /**
   * {@inheritDoc}
   * @throws Exception 
   */
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public DepartmentEntity update(Long id, DepartmentEntity department) throws Exception {
		try {
			DepartmentEntity existingDepartment = em.find(DepartmentEntity.class, id);
			if(existingDepartment != null) {
				em.merge(department);
				System.out.println("Updated department : " + existingDepartment.getIdentifier());
			}
			return department;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error updating department with id : " +id );
		}
  }

}