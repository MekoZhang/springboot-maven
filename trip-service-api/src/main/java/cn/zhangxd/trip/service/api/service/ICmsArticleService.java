package cn.zhangxd.trip.service.api.service;

import cn.zhangxd.trip.service.api.entity.CmsArticle;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 栏目Service
 * Created by zhangxd on 15/10/20.
 */
public interface ICmsArticleService {

    CmsArticle get(String id);

    PageInfo<CmsArticle> findPage(Page<CmsArticle> page, CmsArticle article, boolean isDataScopeFilter);

    void save(CmsArticle article);

    void delete(CmsArticle article, Boolean isRe);

    List<Object[]> findByIds(String ids);

}
