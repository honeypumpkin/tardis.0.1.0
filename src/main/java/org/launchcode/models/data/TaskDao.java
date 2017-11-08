/*
Made by robin on 8/3/17, 9:50 PM
*/

package org.launchcode.models.data;

import org.launchcode.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface TaskDao extends CrudRepository<Task, Integer> {
}
