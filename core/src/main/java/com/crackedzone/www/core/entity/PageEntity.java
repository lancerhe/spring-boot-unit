package com.crackedzone.www.core.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Date;

/**
 * Package com.crackedzone.www.core.entity
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Entity
@Table(name = "pages")
public class PageEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "cname")
    private String cname;

    @Column(name = "content")
    private String content;

    @Column(name = "publish_date", columnDefinition = "DATE")
    @JSONField(name = "publish_date")
    private Date publishDate;

    @Column(name = "create_time")
    @JSONField(name = "create_time")
    private int createTime;

    @Column(name = "update_time")
    @JSONField(name = "update_time")
    private int updateTime = 0;

    @Column(name = "deleted")
    private boolean deleted = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}