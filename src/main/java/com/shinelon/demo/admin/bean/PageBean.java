package com.shinelon.demo.admin.bean;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/***
 *
 * @author syq
 *
 * @param <T>
 */
public class PageBean<T> {
    private String order;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private List<T> rows;

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getOrder() {
        return order;
    }

    public List<T> getRows() {
        return rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
