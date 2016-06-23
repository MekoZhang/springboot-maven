package cn.zhangxd.trip.web.admin.utils;

import cn.zhangxd.trip.service.api.entity.SysDict;
import cn.zhangxd.trip.service.api.service.ISysDictService;
import cn.zhangxd.trip.util.StringHelper;
import cn.zhangxd.trip.web.admin.base.SpringContextHolder;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 字典工具类
 * Created by zhangxd on 15/10/20.
 */
public class DictUtils {

    private static ISysDictService dictService = SpringContextHolder.getBean(ISysDictService.class);

    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringHelper.isNotBlank(value)) {
            for (SysDict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            List<String> valueList = Lists.newArrayList();
            for (String value : StringUtils.split(values, ",")) {
                valueList.add(getDictLabel(value, type, defaultValue));
            }
            return StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (SysDict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    public static List<SysDict> getDictList(String type) {
        return dictService.getDictList(type);
    }

}
