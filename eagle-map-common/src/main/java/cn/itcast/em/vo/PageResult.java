package cn.itcast.em.vo;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {

    @ApiModelProperty(value = "总记录数", required = true)
    private Integer total = 0;
    @ApiModelProperty(value = "页面大小", required = true)
    private Integer pageSize = 0;
    @ApiModelProperty(value = "总页数", required = true)
    private Integer pageCount = 0;
    @ApiModelProperty(value = "当前页码", required = true)
    private Integer page = 0;
    @ApiModelProperty(value = "数据列表", required = true)
    private List<T> items = Collections.emptyList(); //列表

    /**
     * 将mybatisplus的分页对象Page参数写入到当前对象中，不包括数据集合
     *
     * @param page
     */
    public PageResult toBean(Page<?> page) {
        this.setPage(Convert.toInt(page.getCurrent()));
        this.setPageSize(Convert.toInt(page.getSize()));
        this.setTotal(Convert.toInt(page.getTotal()));
        this.setPageCount(Convert.toInt(page.getPages()));
        return this;
    }

    /**
     * 将mybatisplus的分页对象Page参数写入到当前对象中，包括数据集合
     *
     * @param page
     */
    public PageResult convert(Page<T> page) {
        this.toBean(page);
        this.setItems(page.getRecords());
        return this;
    }


}