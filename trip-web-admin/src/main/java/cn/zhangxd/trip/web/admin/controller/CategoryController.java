package cn.zhangxd.trip.web.admin.controller;

import cn.zhangxd.trip.service.api.entity.CmsCategory;
import cn.zhangxd.trip.service.api.service.ICmsCategoryService;
import cn.zhangxd.trip.web.admin.common.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 栏目Controller
 * Created by zhangxd on 15/10/20.
 */
@Controller
@RequestMapping(value = "/cms/category")
public class CategoryController extends BaseController {

    @Autowired
    private ICmsCategoryService categoryService;

    @ModelAttribute
    public CmsCategory get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return categoryService.get(id);
        } else {
            return new CmsCategory();
        }
    }

    @RequiresPermissions("cms:category:view")
    @RequestMapping(value = {"list", ""})
    public String list(Model model) {
        List<CmsCategory> list = Lists.newArrayList();
        List<CmsCategory> sourceList = categoryService.findByUser(null);
        CmsCategory.sortList(list, sourceList, "1");
        model.addAttribute("list", list);
        return "modules/cms/categoryList";
    }

    @RequiresPermissions("cms:category:view")
    @RequestMapping(value = "form")
    public String form(CmsCategory category, Model model) {
        if (category.getParent() == null || category.getParent().getId() == null) {
            category.setParent(new CmsCategory("1"));
        }
        CmsCategory parent = categoryService.get(category.getParent().getId());
        category.setParent(parent);
        model.addAttribute("category", category);
        return "modules/cms/categoryForm";
    }

    @RequiresPermissions("cms:category:edit")
    @RequestMapping(value = "save")
    public String save(CmsCategory category, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, category)) {
            return form(category, model);
        }
        categoryService.save(category);
        addMessage(redirectAttributes, "保存栏目'" + category.getName() + "'成功");
        return "redirect:/cms/category";
    }

    @RequiresPermissions("cms:category:edit")
    @RequestMapping(value = "delete")
    public String delete(CmsCategory category, RedirectAttributes redirectAttributes) {
        if (CmsCategory.isRoot(category.getId())) {
            addMessage(redirectAttributes, "删除栏目失败, 不允许删除顶级栏目或编号为空");
        } else {
            categoryService.delete(category);
            addMessage(redirectAttributes, "删除栏目成功");
        }
        return "redirect:/cms/category";
    }

    /**
     * 批量修改栏目排序
     */
    @RequiresPermissions("cms:category:edit")
    @RequestMapping(value = "updateSort")
    public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
        int len = ids.length;
        CmsCategory[] entitys = new CmsCategory[len];
        for (int i = 0; i < len; i++) {
            entitys[i] = categoryService.get(ids[i]);
            entitys[i].setSort(sorts[i]);
            categoryService.save(entitys[i]);
        }
        addMessage(redirectAttributes, "保存栏目排序成功!");
        return "redirect:/cms/category";
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(String module, @RequestParam(required = false) String extId, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<CmsCategory> list = categoryService.findByUser(module);
        for (CmsCategory e : list) {
            if (extId == null || !extId.equals(e.getId()) && !e.getParentIds().contains("," + extId + ",")) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParent() != null ? e.getParent().getId() : 0);
                map.put("name", e.getName());
                map.put("module", e.getModule());
                mapList.add(map);
            }
        }
        return mapList;
    }

}
