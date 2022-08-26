package com.leo.fpd.entities;

import com.leo.base.entities.FilletedTabItem;

import java.util.List;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 11:25
 * Desc:
 */
public class SystemClassEntity implements FilletedTabItem {

    private String author;
    private List<SystemClassEntity> children;
    private int courseId;
    private String cover;
    private String desc;
    private int id;
    private String lisense;
    private String lisenseLink;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<SystemClassEntity> getChildren() {
        return children;
    }

    public void setChildren(List<SystemClassEntity> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLisense() {
        return lisense;
    }

    public void setLisense(String lisense) {
        this.lisense = lisense;
    }

    public String getLisenseLink() {
        return lisenseLink;
    }

    public void setLisenseLink(String lisenseLink) {
        this.lisenseLink = lisenseLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String getTabName() {
        return getName();
    }
}
