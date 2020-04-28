import com.hlc.carrent.domain.LogInfo;
import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.mapper.LogInfoMapper;
import com.hlc.carrent.mapper.MenuMapper;
import com.hlc.carrent.service.LogInfoService;
import com.hlc.carrent.service.MenuService;
import com.hlc.carrent.vo.LogInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class logInfoTest {

    @Autowired
    private LogInfoMapper logInfoMapper;
    @Autowired
    private LogInfoService logInfoService;


    @Test
    public void testSelectUser() throws Exception {
        LogInfoVo logInfoVo = new LogInfoVo();
//        logInfoVo.setStartTime(new Date());
        logInfoVo.setEndTime(new Date());
        List<LogInfo> logInfos = logInfoMapper.selectAllLogInfo(logInfoVo);
        System.out.println(logInfos);
    }
}
