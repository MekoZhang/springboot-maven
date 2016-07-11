package cn.zhangxd.trip.service.provider.serviceImpl;

import cn.zhangxd.trip.infrastructure.mapper.CmsArticleDataMapper;
import cn.zhangxd.trip.service.api.entity.CmsArticleData;
import cn.zhangxd.trip.service.api.service.ICmsArticleDataService;
import cn.zhangxd.trip.service.provider.common.service.CrudService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章内容Service
 * Created by zhangxd on 15/10/20.
 */
@Service(protocol = {"dubbo"}, timeout = 5000)
@Transactional(readOnly = true)
public class CmsArticleDataService extends CrudService<CmsArticleDataMapper, CmsArticleData> implements ICmsArticleDataService {

}
