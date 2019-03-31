package com.test.demo.Controller.PageController;

import com.test.demo.Entity.Task;
import com.test.demo.Services.TaskService;
import com.test.demo.Utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;
    @GetMapping("/task")
    public String getTask(HttpSession httpSession){
        return "task";
    }
    @GetMapping("/myTasks")
    public String getMyTasks(HttpSession httpSession){
        return "myTasks";
    }
    @RequestMapping("/setTask")
    @ResponseBody
    public String setTask(HttpSession httpSession, @RequestBody HashMap<String,Object> map){
        Task task=new Task();
        String id= UUID.randomUUID().toString();
        task.setTaskid(id);
        if(map.get("taskname")!=null){
            task.setTaskName(map.get("taskname").toString());
        }
        if(map.get("price")!=null){
            task.setPrice(map.get("price").toString());
        }
        if(map.get("detail")!=null){
            task.setDetail(map.get("detail").toString());
        }
        task.setTaskState(0);//0表示未完成
        task.setTaskPublisherId(map.get("name").toString());
        task.setPublishDate(TaskController.getNowDate());
        boolean isInsert=taskService.insert(task);
        if(isInsert){
            return "1";
        }else
            return "0";
    }
    @RequestMapping("/listTask")
    public String getAllTaskEM(){
        return  "listTask";
    }
    @RequestMapping("/getAllMyRecTaskData")
    @ResponseBody
    public WebResult getAllMyRecTaskData(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Task> allTask=taskService.getMyRecTask(name,null,null);
            return  WebResult.ok("0",allTask,allTask.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @RequestMapping("/getAllMyPubTaskData")
    @ResponseBody
    public WebResult getAllMyPubTaskData(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Task> allTask=taskService.getMyPubTask(name,null,null);
            return  WebResult.ok("0",allTask,allTask.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @RequestMapping("/getAllTaskData")
    @ResponseBody
    public WebResult getAllTaskData(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Task> allTask=taskService.getAllTask(name,null,null);
            return  WebResult.ok("0",allTask,allTask.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @RequestMapping("/acceptTask")
    @ResponseBody
    public String acceptTask(HttpSession session,@RequestBody HashMap<String,Object>map){
        String id = map.get("taskid").toString();
        String name = map.get("acceptName").toString();
        try{
            Task task=taskService.getTaskById(id);
            task.setTaskState(1);
            task.setReciptId(name);
            taskService.insert(task);
            return "1";
        }catch (Exception e){
            return "0";
        }

    }
    @RequestMapping("/deleteTask")
    @ResponseBody
    public String deleteTask(@RequestBody HashMap<String,Object>map){
        String id=map.get("taskid").toString();
        try{
            taskService.deleteById(id);
            return "1";
        }catch (Exception e){
            return "0";
        }

    }
    @RequestMapping("/finishTask")
    @ResponseBody
    public String finishTask(@RequestBody HashMap<String,Object>map){
        String id=map.get("taskid").toString();
        try{
            Task task=taskService.getTaskById(id);
            String date = getNowDate();
            task.setFinishDate(date);
            task.setTaskState(2);
            taskService.insert(task);
            return "1";
        }catch (Exception e){
            return "0";
        }

    }
    /**
     * 获取现在时间
     *
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        String dateString = formatter.format(currentTime).toString();
       return dateString;
    }
}