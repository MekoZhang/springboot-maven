package cn.zhangxd.trip.service.api.entity;

import cn.zhangxd.trip.service.api.entity.base.DataEntity;
import cn.zhangxd.trip.util.Reflections;
import cn.zhangxd.trip.util.StringHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 栏目Entity
 * Created by zhangxd on 15/10/20.
 */
public class CmsCategory extends DataEntity<CmsCategory> {

    private static final long serialVersionUID = 1L;

    private CmsCategory parent;
    private String parentIds;
    private String module;
    private String name;
    private String image;
    private String href;
    private String description;
    private String keywords;
    private Integer sort;

    private List<CmsCategory> childList = new ArrayList<>(); // 拥有子分类列表

    public CmsCategory() {
        super();
        this.module = "";
        this.sort = 30;
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public CmsCategory(String id) {
        this();
        this.id = id;
    }

    @JsonBackReference
    @NotNull
    public CmsCategory getParent() {
        return parent;
    }

    public void setParent(CmsCategory parent) {
        this.parent = parent;
    }

    @Length(min = 1, max = 2000)
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        String id = null;
        if (parent != null) {
            id = (String) Reflections.getFieldValue(parent, "id");
        }
        return StringHelper.isNotBlank(id) ? id : "0";
    }

    @Length(max = 20)
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Length(max = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Length(max = 255)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Length(max = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(max = 255)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<CmsCategory> getChildList() {
        return childList;
    }

    public void setChildList(List<CmsCategory> childList) {
        this.childList = childList;
    }

    public static void sortList(List<CmsCategory> list, List<CmsCategory> sourceList, String parentId) {
        for (int i = 0; i < sourceList.size(); i++) {
            CmsCategory e = sourceList.get(i);
            if (e.getParent() != null && e.getParent().getId() != null
                    && e.getParent().getId().equals(parentId)) {
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourceList.size(); j++) {
                    CmsCategory child = sourceList.get(j);
                    if (child.getParent() != null && child.getParent().getId() != null
                            && child.getParent().getId().equals(e.getId())) {
                        sortList(list, sourceList, e.getId());
                        break;
                    }
                }
            }
        }
    }

    public String getIds() {
        return (this.getParentIds() != null ? this.getParentIds().replaceAll(",", " ") : "")
                + (this.getId() != null ? this.getId() : "");
    }

    public boolean isRoot() {
        return isRoot(this.id);
    }

    public static boolean isRoot(String id) {
        return id != null && id.equals("1");
    }

}