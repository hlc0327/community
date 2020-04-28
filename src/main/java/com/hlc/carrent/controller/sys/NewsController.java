package com.hlc.carrent.controller.sys;

import com.hlc.carrent.domain.News;
import com.hlc.carrent.domain.User;
import com.hlc.carrent.service.NewsService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.utils.WebUtils;
import com.hlc.carrent.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 公告管理控制器
 */
@Controller
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 公告列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo) {

        List<News> list = newsService.selectAllNews(newsVo);
        System.out.println(newsVo);
        int count = newsService.selectAllNewsCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);
        return dataGridView;
    }
    /**
     * 添加公告
     */
    @ResponseBody
    @RequestMapping("addNews")
    public ResultObj addNews(NewsVo newsVo) {
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setCreatetime(new Date());   //设置发布时间
            newsVo.setOpername(user.getRealname());
            newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改公告
     */
    @ResponseBody
    @RequestMapping("updateNews")
    public ResultObj updateNews(NewsVo newsVo) {
        System.out.println(newsVo);
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setCreatetime(new Date());   //设置发布时间
            newsVo.setOpername(user.getRealname());
            newsService.updateNews(newsVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id删除公告
     * @param newsVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(NewsVo newsVo) {
        System.out.println(newsVo);
        try {

            newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除公告
     * @param newsVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @ResponseBody
    @RequestMapping("loadNewsById")
    public News loadNewsById(Integer id){
        return this.newsService.selectNewById(id);
    }



}
