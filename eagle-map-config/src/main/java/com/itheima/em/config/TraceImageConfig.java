package com.itheima.em.config;

import lombok.Data;

@Data
public class TraceImageConfig {

    private double scale = 0.9; //缩放比，默认0.9
    private String background = "#FFFFFF"; //背景颜色
    private String traceColor = "#6699FF"; //轨迹颜色
    private int strokeWidth = 3; //画笔宽度
    private int width = 300; //生成图片的宽度
    private int height = 300; //生成图片的高度

}
