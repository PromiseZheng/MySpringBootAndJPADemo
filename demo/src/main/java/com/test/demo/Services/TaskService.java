package com.test.demo.Services;

import com.test.demo.Dao.TaskDao;
import com.test.demo.Entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    TaskDao taskDao;
    public List<Task> getAllTask(String name,Integer page, Integer size){
        if(page==null){
            page=0;
        }
        if(size==null){
            size=10;
        }
        List<Task> tasks=taskDao.findByTaskPublisherIdNotLikeAndTaskStateEquals(name,0);
        return tasks;
    }
    public List<Task> getMyPubTask(String name,Integer page, Integer size){
        if(page==null){
            page=0;
        }
        if(size==null){
            size=10;
        }
        List<Task> tasks=taskDao.findByTaskPublisherIdEquals(name);
        return tasks;
    }
    public List<Task> getMyRecTask(String name,Integer page, Integer size){
        if(page==null){
            page=0;
        }
        if(size==null){
            size=10;
        }
        List<Task> tasks=taskDao.findByReciptIdEquals(name);
        return tasks;
    }
    public Task getTaskById(String id){
            if(taskDao.findById(id).isPresent()){
                Task task= taskDao.findById(id).get();
                return task;
            }
            return null;
    }
    public boolean insert(Task task){
        try{
            taskDao.save(task);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public boolean deleteById(String id){
        try{
            taskDao.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
