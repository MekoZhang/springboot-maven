package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.mapper.CmsCategoryMapper;
import cn.zhangxd.trip.service.api.entity.CmsCategory;
import cn.zhangxd.trip.service.api.service.ICmsCategoryService;
import cn.zhangxd.trip.service.provider.common.service.CrudService;
import cn.zhangxd.trip.util.StringHelper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 栏目Service
 * Created by zhangxd on 15/10/20.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class CmsCategoryService extends CrudService<CmsCategoryMapper, CmsCategory> implements ICmsCategoryService {


    @Override
    public List<CmsCategory> findByUser(String module) {
        CmsCategory category = new CmsCategory();
        category.setParent(new CmsCategory());
        List<CmsCategory> list = dao.findList(category);

        List<CmsCategory> categoryList = new ArrayList<>();
        for (CmsCategory e : list) {
            if (StringHelper.isNotEmpty(module)) {
                if (module.equals(e.getModule()) || "".equals(e.getModule())) {
                    categoryList.add(e);
                }
            } else {
                categoryList.add(e);
            }
        }
        return categoryList;
    }

    public List<CmsCategory> findByParentId(String parentId) {
        CmsCategory entity = new CmsCategory();
        CmsCategory parent = new CmsCategory();
        parent.setId(parentId);
        entity.setParent(parent);
        return dao.findByParentId(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(CmsCategory entity) {

        // 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
        if (entity.getParent() == null || StringHelper.isBlank(entity.getParentId())
                || "0".equals(entity.getParentId())) {
            entity.setParent(null);
        } else {
            entity.setParent(super.get(entity.getParentId()));
        }
        if (entity.getParent() == null) {
            CmsCategory parentEntity = new CmsCategory("0");
            entity.setParent(parentEntity);
            entity.getParent().setParentIds(StringHelper.EMPTY);
        }

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = entity.getParentIds();

        // 设置新的父节点串
        entity.setParentIds(entity.getParent().getParentIds() + entity.getParent().getId() + ",");

        // 保存或更新实体
        super.save(entity);

        // 更新子节点 parentIds
        CmsCategory o = new CmsCategory();
        o.setParentIds("%," + entity.getId() + ",%");
        List<CmsCategory> list = dao.findByParentIdsLike(o);
        for (CmsCategory e : list) {
            if (e.getParentIds() != null && oldParentIds != null) {
                e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
                dao.updateParentIds(e);
            }
        }
    }

}
