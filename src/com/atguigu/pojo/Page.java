package com.atguigu.pojo;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-14 9:23
* @version 1.0
*/
/**
* @description: page是分页的模型对象
* @param: <T>是具体的模块的javaBean类
* @return:
* @author wxj27
* @date: 2023-03-14 9:33
*/
public class Page<T> {

    public static final Integer PAGE_SIZE =4;

    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //当前页显示数量
    private Integer pageSize= PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //当前页数据
    private List<T> items;
    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /*数据边界的有效检查*/
        if(pageNo<1 || pageNo==null){
            this.pageNo=1;
        }else if(pageNo>this.pageTotal){
            this.pageNo=this.pageTotal;
        }else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
