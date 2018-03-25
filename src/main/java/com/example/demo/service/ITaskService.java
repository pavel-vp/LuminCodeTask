package com.example.demo.service;

import com.example.demo.model.TaskRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by pasha on 25.03.18.
 */
@Repository
public interface ITaskService extends JpaRepository<TaskRec, Long> {

}
