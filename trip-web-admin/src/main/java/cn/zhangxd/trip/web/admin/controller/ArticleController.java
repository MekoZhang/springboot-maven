package cn.zhangxd.trip.web.admin.controller;

import cn.zhangxd.trip.service.api.entity.CmsArticle;
import cn.zhangxd.trip.service.api.entity.CmsCategory;
import cn.zhangxd.trip.service.api.service.ICmsArticleDataService;
import cn.zhangxd.trip.service.api.service.ICmsArticleService;
import cn.zhangxd.trip.service.api.service.ICmsCategoryService;
import cn.zhangxd.trip.util.StringHelper;
import cn.zhangxd.trip.web.admin.common.web.BaseController;
import cn.zhangxd.trip.web.admin.utils.UserUtils;
import cn.zhangxd.trip.web.admin.utils.upload.FileIndex;
import cn.zhangxd.trip.web.admin.utils.upload.FileManager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章Controller
 * Created by zhangxd on 15/10/20.
 */
@Controller
@RequestMapping(value = "/cms/article")
public class ArticleController extends BaseController {

    @Autowired
    private ICmsArticleService articleService;
    @Autowired
    private ICmsArticleDataService articleDataService;
    @Autowired
    private ICmsCategoryService categoryService;
    @Autowired
    private FileManager fileManager;

    @ModelAttribute
    public CmsArticle get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return articleService.get(id);
        } else {
            return new CmsArticle();
        }
    }

    @RequiresPermissions("cms:article:view")
    @RequestMapping(value = {"list", ""})
    public String list(CmsArticle article, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = getPage(request, response);
        PageInfo<CmsArticle> pageInfo = articleService.findPage(page, article, true);
        model.addAttribute("page", pageInfo);
        model.addAttribute("article", article);
        return "modules/cms/articleList";
    }

    @RequiresPermissions("cms:article:view")
    @RequestMapping(value = "form")
    public String form(CmsArticle article, Model model) {
        // 如果当前传参有子节点，则选择取消传参选择
        if (article.getCategory() != null && StringUtils.isNotBlank(article.getCategory().getId())) {
            List<CmsCategory> list = categoryService.findByParentId(article.getCategory().getId());
            if (list.size() > 0) {
                article.setCategory(null);
            } else {
                article.setCategory(categoryService.get(article.getCategory().getId()));
            }
        }
        article.setArticleData(articleDataService.get(article.getId()));
        model.addAttribute("article", article);
        model.addAttribute("file", fileManager.getFileUrlByRealpath(article.getImage()));
        return "modules/cms/articleForm";
    }

    @RequiresPermissions("cms:article:edit")
    @RequestMapping(value = "save")
    public String save(CmsArticle article,
                       @RequestParam(value = "file", required = false) MultipartFile file,
                       Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, article)) {
            return form(article, model);
        }
        if (article.getIsNewRecord()) {
            article.setCreateBy(UserUtils.getUser());
        }

        if (file != null && file.getSize() > 0) {
            FileIndex ufi = new FileIndex();
            ufi.setmUpfile(file);
            ufi.setTruename(file.getOriginalFilename());
            ufi.setMcode("cms");
            ufi = fileManager.save(ufi);

            String path = ufi.getPath();
            article.setImage(path);
        }

        article.setUpdateBy(UserUtils.getUser());
        articleService.save(article);
        addMessage(redirectAttributes, "保存文章'" + StringHelper.abbr(article.getTitle(), 50) + "'成功");
        String categoryId = article.getCategory() != null ? article.getCategory().getId() : null;
        return "redirect:/cms/article/?repage&category.id=" + (categoryId != null ? categoryId : "");
    }

    @RequiresPermissions("cms:article:edit")
    @RequestMapping(value = "delete")
    public String delete(CmsArticle article, String categoryId, @RequestParam(required = false) Boolean isRe, RedirectAttributes redirectAttributes) {
        articleService.delete(article, isRe);
        addMessage(redirectAttributes, (isRe != null && isRe ? "发布" : "删除") + "文章成功");
        return "redirect:/cms/article/?repage&category.id=" + (categoryId != null ? categoryId : "");
    }

    /**
     * 文章选择列表
     */
    @RequiresPermissions("cms:article:view")
    @RequestMapping(value = "selectList")
    public String selectList(CmsArticle article, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(article, request, response, model);
        return "modules/cms/articleSelectList";
    }

    /**
     * 通过编号获取文章标题
     */
    @RequiresPermissions("cms:article:view")
    @ResponseBody
    @RequestMapping(value = "findByIds")
    public String findByIds(String ids) {
        List<Object[]> list = articleService.findByIds(ids);
        return new Gson().toJson(list);
    }

}
