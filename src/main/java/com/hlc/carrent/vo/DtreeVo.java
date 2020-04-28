package com.hlc.carrent.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * layui树形结构数据
 */
public class DtreeVo {

    private Integer id;
    private Integer parentId;

    private String title;
    private String checkArr = "0";  //是否选中


    private boolean spread;  //是否展开
    private String target;

    protected List<DtreeVo> children = new ArrayList<DtreeVo>();




    public DtreeVo(Integer id,Integer parentId,String title, boolean spread,String checkArr) {
        this.id = id;
        this.title = title;
        this.checkArr = checkArr;
        this.parentId = parentId;
        this.spread = spread;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<DtreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<DtreeVo> children) {
        this.children = children;
    }
}
