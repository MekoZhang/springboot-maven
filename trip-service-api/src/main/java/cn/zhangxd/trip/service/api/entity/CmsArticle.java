/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.zhangxd.trip.service.api.entity;

import cn.zhangxd.trip.service.api.entity.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 文章Entity
 *
 * @author ThinkGem
 * @version 2013-05-15
 */
public class CmsArticle extends DataEntity<CmsArticle> {

    private static final long serialVersionUID = 1L;
    private CmsCategory category;// 分类编号
    private String title;    // 标题
    private String link;    // 外部链接
    private String color;    // 标题颜色（red：红色；green：绿色；blue：蓝色；yellow：黄色；orange：橙色）
    private String image;    // 文章图片
    private String keywords;// 关键字
    private String description;// 描述、摘要
    private Integer weight;    // 权重，越大越靠前

    private CmsArticleData articleData;    //文章副表

    private SysUser createBy; //创建人
    private SysUser updateBy; //更新人

    public CmsArticle() {
        super();
        this.weight = 0;
    }

    public CmsArticle(String id) {
        this();
        this.id = id;
    }

    public CmsArticle(CmsCategory category) {
        this();
        this.category = category;
    }

    public void prePersist() {
        articleData.setId(this.id);
    }

    public CmsCategory getCategory() {
        return category;
    }

    public void setCategory(CmsCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(max = 255)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Length(max = 50)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Length(max = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Length(max = 255)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Length(max = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SysUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(SysUser createBy) {
        this.createBy = createBy;
    }

    public SysUser getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(SysUser updateBy) {
        this.updateBy = updateBy;
    }

    @NotNull
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public CmsArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(CmsArticleData articleData) {
        this.articleData = articleData;
    }

}


