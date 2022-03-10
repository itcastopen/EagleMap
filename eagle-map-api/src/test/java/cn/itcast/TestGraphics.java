package cn.itcast;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/10
 */
public class TestGraphics {

    @Test
    public void start() throws Exception {
        String positions = "116.347544,40.058863|116.347072,40.060374|116.343231,40.05957|116.340313,40.059093|116.3413,40.057418|116.343274,40.056761|116.34808,40.057205|116.348831,40.057303|116.353209,40.05686|116.354775,40.057845|116.351042,40.060259|116.348831,40.061015|116.347544,40.058863";
        int width = 800, height = 800;
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
        double scale = (xScale < yScale ? xScale : yScale) * 0.9;

        // 计算偏移量
        double xoffset = (width - (xMax - xMin) * scale) / 2;
        double yoffset = (height - (yMax - yMin) * scale) / 2;

        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE); //设置背景颜色
        graphics.clearRect(0, 0, width, height); //设置背景颜色的填充

        //由于坐标轴与经纬度不相同，所以需要平移原点并旋转画布
        graphics.translate(0, height);
        graphics.rotate(-Math.PI / 2);

        //设置画笔颜色
        graphics.setColor(new Color(102, 153, 255));
        //设置线条连接的属性
        Stroke s = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        graphics.setStroke(s);
        //消除锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //开始画线，线的数量 = 点数 - 1
        for (int i = 0; i < amuXArr.size() - 1; i++) {
            Line2D line = new Line2D.Double((amuXArr.get(i) - xMin) * scale + xoffset,
                    (yMax - amuYArr.get(i)) * scale + yoffset,
                    (amuXArr.get(i + 1) - xMin) * scale + xoffset,
                    (yMax - amuYArr.get(i + 1)) * scale + yoffset);
            graphics.draw(line);
        }

        //处理绘图
        graphics.dispose();
        //将绘制好的图片写入到图片
        ImageIO.write(image, "jpg", new File("F:\\abc.jpg"));
    }
}
