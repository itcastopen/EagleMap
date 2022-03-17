package cn.itcast.em.server.service.impl;

import cn.itcast.em.service.TraceTerminalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TraceTerminalServiceTest {

    @Resource(name = "AMapTraceTerminalService")
    private TraceTerminalService amapTraceTerminalService;

    @Resource(name = "BaiduTraceTerminalService")
    private TraceTerminalService baiduTraceTerminalService;

    @Test
    public void queryLastPoint() {
        Long serverId = 617418L;
        Long terminalId = 492666422L;
        Long traceId = 260L;
        String data = this.amapTraceTerminalService.queryLastPoint(serverId, terminalId, traceId);
        System.out.println(data);
    }

    @Test
    public void queryLastPoint2() {
        Long serverId = 231526L;
        Long terminalId = 1501743673574510592L;
        Long traceId = 11L;
        String data = this.baiduTraceTerminalService.queryLastPoint(serverId, terminalId, traceId);
        System.out.println(data);
    }
}