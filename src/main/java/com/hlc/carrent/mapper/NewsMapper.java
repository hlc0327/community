package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.News;
import com.hlc.carrent.vo.NewsVo;

import java.util.List;

public interface NewsMapper {


    /**
     * 查询所有公告
     */
    List<News> selectAllNews(NewsVo newsVo);

    /**
     * 查询角色的条数
     */
    int selectAllNewsCount();

    /**
     * 添加公告
     */
    int addNews(News news);

    /**
     * 修改公告
     */
    int updateNews(News news);

    /**
     * 根据id删公告
     *
     */
    void deleteNews(Integer newsId);

    /**
     * 批量删除角色
     */
    void deleteBatchNews(Integer[] ids);

    /**
     *  查询一个公告
     */
    News selectNewById(Integer id);
}
