package com.test.demo.Entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    private String taskid;
    private String taskPublisherId;
    private int taskState;//任务状态 0未被接受  1被接受 2已完成
    private String detail;//任务描述
    private String reciptId;
    private String publishDate;
    private String finishDate;
    private String taskName;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTaskPublisherId() {
        return taskPublisherId;
    }

    public void setTaskPublisherId(String taskPublisherId) {
        this.taskPublisherId = taskPublisherId;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getReciptId() {
        return reciptId;
    }

    public void setReciptId(String reciptId) {
        this.reciptId = reciptId;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
