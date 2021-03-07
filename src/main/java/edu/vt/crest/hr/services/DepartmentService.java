package edu.vt.crest.hr.services;

import java.util.List;

import edu.vt.crest.hr.entity.DepartmentEntity;

/**
 * A DepartmentService defines the contract for a class that will provide
 * service access to database entities.
 */
public interface DepartmentService {

  /**
   * Create Department
   * @param department the DepartmentEntity to create
   * @return the new DepartmentEntity
   * @throws Exception 
   */
  DepartmentEntity createDepartment(DepartmentEntity department) throws Exception;

  /**
   * Method to find department by id
   * @param id of the DepartmentEntity to return
   * @return the matching DepartmentEntity
   * @throws Exception 
   */
  DepartmentEntity findById(Long id) throws Exception;

  /**
   * Method to get list of departments within given range
   * @param startPosition the index of the first DepartmentEntity to return
   * @param maxResult the maximum number of DepartmentEntity(s) to return
   *                  beyond the startPosition
   * @return a list of DepartmentEntity(s)
   * @throws Exception 
   */
  List<DepartmentEntity> listAll(Integer startPosition, Integer maxResult) throws Exception;

  /**
   * Method to update department
   * @param id the id of the DepartmentEntity to update
   * @param department the entity used to update
   * @return the updated DepartmentEntity
   * @throws Exception 
   */
  DepartmentEntity update(Long id, DepartmentEntity department) throws Exception;
}