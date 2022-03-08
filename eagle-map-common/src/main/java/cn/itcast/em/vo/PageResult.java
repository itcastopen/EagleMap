package cn.itcast.em.vo;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    private Integer total = 0;//总记录数
    private Integer pageSize = 0;//页大小
    private Integer pageCount = 0;//总页数
    private Integer page = 0;//当前页码
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