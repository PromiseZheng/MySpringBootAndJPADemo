package com.test.demo.Dao;

import com.test.demo.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task,String> {
    public List<Task> findByTaskPublisherIdNotLikeAndTaskStateEquals(String name,int p);
    public List<Task> findByTaskPublisherIdEquals(String name);
    public List<Task> findByReciptIdEquals(String name);

}
