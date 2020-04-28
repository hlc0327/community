package com.hlc.carrent.utils;

import com.hlc.carrent.constast.SysConstast;
import lombok.Data;

@Data
public class ResultObj {

    private Integer code;
    private String msg;

    /**
     *  返回确认码,成功或者失败
     */
    //添加状态码
    public static final ResultObj ADD_SUCCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.ADD_SUCCESS);
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.ADD_ERROR);
    //更新状态码
    public static final ResultObj UPDATE_SUCCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.UPDATE_SUCCESS);
    public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.UPDATE_ERROR);
    //删除状态码
    public static final ResultObj DELETE_SUCCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.DELETE_SUCCESS);
    public static final ResultObj DELETE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.UPDATE_ERROR);
    //重置状态码
    public static final ResultObj RESET_SUCCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.RESET_SUCCESS);
    public static final ResultObj RESET_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.RESET_ERROR);

    //分配状态码
    public static final ResultObj DISPATCH_SUCCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.DISPATCH_SUCCESS);
    public static final ResultObj DISPATCH_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.DISPATCH_ERROR);
    //出租状态码
    public static final ResultObj RENT_OUT_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.RENT_OUT_SUCCESS);
    public static final ResultObj RENT_OUT_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.RENT_OUT_ERROR);

    /**
     *  状态码 STATUS_TRUE : 0
     *  状态码 STATUS_FALSE : -1
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(SysConstast.CODE_SUCCESS);
    public static final ResultObj STATUS_FALSE = new ResultObj(SysConstast.CODE_ERROR);

    /**
     * 构造器
     */
    private ResultObj(Integer code) {
        this.code = code;
    }
    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
