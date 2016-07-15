package cn.zhangxd.trip.web.admin.controller;

import cn.zhangxd.trip.service.api.service.ICmsCategoryService;
import cn.zhangxd.trip.web.admin.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 内容管理Controller
 * Created by zhangxd on 15/10/20.
 */
@Controller
@RequestMapping(value = "/cms")
public class CmsController extends BaseController {

    @Autowired
    private ICmsCategoryService categoryService;

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "")
    public String index() {
        return "modules/cms/cmsIndex";
    }

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "tree")
    public String tree(Model model) {
        model.addAttribute("categoryList", categoryService.findByUser(null));
        return "modules/cms/cmsTree";
    }

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "none")
    public String none() {
        return "modules/cms/cmsNone";
    }

}