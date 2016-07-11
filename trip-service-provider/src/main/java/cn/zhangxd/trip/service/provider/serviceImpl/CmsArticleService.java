package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.mapper.CmsArticleDataMapper;
import cn.zhangxd.trip.infrastructure.mapper.CmsArticleMapper;
import cn.zhangxd.trip.infrastructure.mapper.CmsCategoryMapper;
import cn.zhangxd.trip.service.api.entity.CmsArticle;
import cn.zhangxd.trip.service.api.entity.CmsArticleData;
import cn.zhangxd.trip.service.api.entity.CmsCategory;
import cn.zhangxd.trip.service.api.service.ICmsArticleService;
import cn.zhangxd.trip.service.provider.common.service.CrudService;
import cn.zhangxd.trip.util.StringHelper;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章Service
 * Created by zhangxd on 15/10/20.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class CmsArticleService extends CrudService<CmsArticleMapper, CmsArticle> implements ICmsArticleService {

    @Autowired
    private CmsArticleDataMapper articleDataDao;
    @Autowired
    private CmsCategoryMapper categoryDao;

    @Transactional(readOnly = false)
    public PageInfo<CmsArticle> findPage(Page<CmsArticle> page, CmsArticle article, boolean isDataScopeFilter) {
        if (article.getCategory() != null && StringHelper.isNotBlank(article.getCategory().getId()) && !CmsCategory.isRoot(article.getCategory().getId())) {
            CmsCategory category = categoryDao.get(article.getCategory().getId());
            if (category == null) {
                category = new CmsCategory();
            }
            category.setParentIds(category.getId());
            article.setCategory(category);
        } else {
            article.setCategory(new CmsCategory());
        }
        return super.findPage(page, article);

    }

    @Transactional(readOnly = false)
    public void save(CmsArticle article) {
        if (article.getArticleData().getContent() != null) {
            article.getArticleData().setContent(StringEscapeUtils.unescapeHtml4(
                    article.getArticleData().getContent()));
        }
        article.setUpdateDate(new Date());

        CmsArticleData articleData;
        if (StringHelper.isBlank(article.getId())) {
            article.preInsert();
            articleData = article.getArticleData();
            articleData.setId(article.getId());
            dao.insert(article);
            articleDataDao.insert(articleData);
        } else {
            article.preUpdate();
            articleData = article.getArticleData();
            articleData.setId(article.getId());
            dao.update(article);
            articleDataDao.update(article.getArticleData());
        }
    }

    @Transactional(readOnly = false)
    public void delete(CmsArticle article, Boolean isRe) {
        super.delete(article);
    }

    /**
     * 通过编号获取内容标题
     *
     * @return new Object[]{栏目Id,文章Id,文章标题}
     */
    public List<Object[]> findByIds(String ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        List<Object[]> list = Lists.newArrayList();
        String[] idss = StringHelper.split(ids, ",");
        CmsArticle e;
        for (int i = 0; (idss.length - i) > 0; i++) {
            e = dao.get(idss[i]);
            list.add(new Object[]{e.getCategory().getId(), e.getId(), StringHelper.abbr(e.getTitle(), 50)});
        }
        return list;
    }


}
