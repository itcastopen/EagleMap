package com.itheima.em.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.config.TraceImageConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 绘制轨迹缩略图
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/10
 */
@Service
public class TraceImageService {

    @Resource
    private EagleConfig eagleConfig;

    public static void main(String[] args) {
        TraceImageService traceImageService = new TraceImageService();
        traceImageService.drawImage(null, 1, 2);
    }

    public BufferedImage drawImage(String positions, Integer width, Integer height) {
        TraceImageConfig traceImageConfig = this.eagleConfig.getTraceImage();
        //如果传入的宽度和高度为0时，读取默认的配置
        width = width == null ? traceImageConfig.getWidth() : width;
        height = height == null ? traceImageConfig.getHeight() : height;

        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.decode(traceImageConfig.getBackground())); //设置背景颜色
        graphics.clearRect(0, 0, width, height); //设置背景颜色的填充

        if (StrUtil.isEmpty(positions) || !StrUtil.contains(positions, '|')) {
            //没有坐标数据，返回空白图片
            graphics.dispose();
            return image;
        }

        //解析经纬度数据
        List<String> amuArr = StrUtil.split(positions, '|');

        //纬度数据集合
        List<Double> amuXArr = amuArr.stream()
                .map(s -> Convert.toDouble(StrUtil.split(s, ',').get(1)))
                .collect(Collectors.toList());

        //经度数据集合，y轴需要进行取负操作
        List<Double> amuYArr = amuArr.stream()
                .map(s -> Convert.toDouble(StrUtil.split(s, ',').get(0)) * -1)
                .collect(Collectors.toList());

        // 拿到方向轴的最大最小值
        double xMax = CollUtil.max(amuXArr);
        double xMin = CollUtil.min(amuXArr);
        double yMax = CollUtil.max(amuYArr);
        double yMin = CollUtil.min(amuYArr);

        // 根据canvas宽高计算缩放级别
        double xScale = width / (xMax - xMin);
        double yScale = height / (yMax - yMin);
        // 为了图像留白，所以将缩放比按照90%进行绘画
        double scale = (xScale < yScale ? xScale : yScale) * traceImageConfig.getScale();

        // 计算偏移量
        double xOffset = (width - (xMax - xMin) * scale) / 2;
        double yOffset = (height - (yMax - yMin) * scale) / 2;


        //由于坐标轴与经纬度不相同，所以需要平移原点并旋转画布
        graphics.translate(0, height);
        graphics.rotate(-Math.PI / 2);

        //设置画笔颜色
        graphics.setColor(Color.decode(traceImageConfig.getTraceColor()));
        //设置线条连接的属性
        Stroke s = new BasicStroke(traceImageConfig.getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        graphics.setStroke(s);
        //消除锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //开始画线，线的数量 = 点数 - 1
        for (int i = 0; i < amuXArr.size() - 1; i++) {
            Line2D line = new Line2D.Double((amuXArr.get(i) - xMin) * scale + xOffset,
                    (yMax - amuYArr.get(i)) * scale + yOffset,
                    (amuXArr.get(i + 1) - xMin) * scale + xOffset,
                    (yMax - amuYArr.get(i + 1)) * scale + yOffset);
            graphics.draw(line);
        }

        //处理绘图
        graphics.dispose();
        return image;
    }

}
