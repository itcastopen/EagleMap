package com.itheima.em.server.service.impl.baidu;

import com.itheima.em.enums.CoordinateType;
import com.itheima.em.service.CoordinateService;
import com.itheima.em.vo.CoordinateVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CoordinateBaiduServiceImplTest {

    @Resource(name = "BaiduCoordinateService")
    private CoordinateService coordinateService;

    @Test
    public void convertToGcj02() {
        //百度转高德
        this.coordinateService.convertToGcj02(CoordinateType.BAIDU, new CoordinateVo(121.618075, 31.040699))
                .forEach(coordinateVo -> System.out.println(coordinateVo));
    }

    @Test
    public void convert() {
        //高德转百度
        CoordinateVo from = new CoordinateVo(121.61150429372418, 31.035032349988033);
        CoordinateVo vo = this.coordinateService.convert(from, CoordinateType.AMAP, CoordinateType.BAIDU);
        System.out.println(vo);
    }
}