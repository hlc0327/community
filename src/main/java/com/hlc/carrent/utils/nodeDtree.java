package com.hlc.carrent.utils;

import lombok.Data;

/**
 * 封装layui数据表格的数据对象
 *
 * @author LJH
 */
@Data
public class nodeDtree {

    private Integer code = 0;
    private String msg = "";
    private Long count;
    private Object data;

}