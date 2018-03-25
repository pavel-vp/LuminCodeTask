package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by pasha on 25.03.18.
 */
@Data
@Entity
@Table(name = "tasks")
public class TaskRec {

    public static final Long TASK_STATUS_NEW = 1L;
    public static final Long TASK_STATUS_FINISHED = 2L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Long status = TASK_STATUS_NEW;

    private Date timeAdded;

    private Date timeFinished;

}
