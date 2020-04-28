import com.hlc.carrent.domain.News;
import com.hlc.carrent.mapper.NewsMapper;
import com.hlc.carrent.service.NewsService;
import com.hlc.carrent.vo.NewsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class NewsTest {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private NewsService newsService;


    @Test
    public void testSelectUser() throws Exception {
        NewsVo newsVo = new NewsVo();
//        newsVo.setStartTime(new Date());
        newsVo.setEndTime(new Date());
        newsVo.setPage(1);
        newsVo.setLimit(3);
        List<News> newss = newsService.selectAllNews(newsVo);
        System.out.println(newss);
    }
    @Test
    public void add() throws Exception {
        NewsVo newsVo = new NewsVo();
//        newsVo.setStartTime(new Date());
        newsVo.setTitle("测试标题");
        newsService.addNews(newsVo);
    }
}
