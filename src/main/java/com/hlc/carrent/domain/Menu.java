package com.hlc.carrent.domain;

import java.util.List;

public class Menu {

    private Integer id;
    private Integer pid;
    private Integer parentId;   //树状查询使用
    private String title;
    private String href;
    private String spread;      //是否展开,展示类型boolean
    private String spread2;      //接收参数,用于保存
    private String target;
    private String icon;
    private Integer available;

    List<Menu> children;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getSpread2() {
        return spread2;
    }

    public void setSpread2(String spread2) {
        this.spread2 = spread2;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", parentId=" + parentId +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", spread2='" + spread2 + '\'' +
                ", target='" + target + '\'' +
                ", icon='" + icon + '\'' +
                ", available=" + available +
                ", children=" + children +
                '}';
    }
}
