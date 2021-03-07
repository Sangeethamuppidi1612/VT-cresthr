package edu.vt.crest.hr.services;

import java.util.List;

import edu.vt.crest.hr.entity.EmployeeEntity;

/**
 * An EmployeeService defines the contract for a class that will provide
 * service access to database entities.
 */
public interface EmployeeService {
  /**
   * Method to create employee
   * @param employee the EmployeeEntity to create
   * @return the new EmployeeEntity
   * @throws Exception 
   */
  EmployeeEntity createEmployee(EmployeeEntity employee) throws Exception;

  /**
   * Method to find employee by id
   * @param id of the EmployeeEntity to return
   * @return the matching EmployeeEntity
   * @throws Exception 
   */
  EmployeeEntity findById(Long id) throws Exception;

  /**
   * Method to get employees list within given range
   * @param startPosition the index of the first EmployeeEntity to return
   * @param maxResult the maximum number of EmployeeEntity(s) to return
   *                  beyond the startPosition
   * @return a list of EmployeeEntity(s)
 * @throws Exception 
   */
  List<EmployeeEntity> listAll(Integer startPosition, Integer maxResult) throws Exception;

  /**
   * Method to update an employee
   * @param id the id of the EmployeeEntity to update
   * @param employee the entity used to update
   * @return the updated EmployeeEntity
 * @throws Exception 
   */
  EmployeeEntity update(Long id, EmployeeEntity employee) throws Exception;
}