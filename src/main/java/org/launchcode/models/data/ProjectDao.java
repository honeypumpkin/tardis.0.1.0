/*
Made by robin on 8/3/17, 9:50 PM
*/

package org.launchcode.models.data;


import org.launchcode.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProjectDao extends CrudRepository<Project, Integer> {
}
