package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.domain.News;
import com.hlc.carrent.mapper.NewsMapper;
import com.hlc.carrent.service.NewsService;
import com.hlc.carrent.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectAllNews(NewsVo newsVo) {
        PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
        if (newsVo.getPage() == null) {
            newsVo.setPage(1);
        }
        if (newsVo.getLimit() == null) {
            newsVo.setPage(10);
        }
        return newsMapper.selectAllNews(newsVo);
    }

    @Override
    public int selectAllNewsCount() {
        return newsMapper.selectAllNewsCount();
    }

    @Override
    public int addNews(News news) {
        return newsMapper.addNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public void deleteNews(Integer newsid) {
        System.out.println(newsid);
        //删除公告表里的数据
        newsMapper.deleteNews(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer rid : ids) {
            deleteNews(rid);
        }
    }

    @Override
    public News selectNewById(Integer id) {
        return newsMapper.selectNewById(id);
    }


}
