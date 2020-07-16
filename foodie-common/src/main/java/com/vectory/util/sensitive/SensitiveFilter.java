package com.vectory.util.sensitive;

import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class SensitiveFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        if (!(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        try {
            Field field = object.getClass().getDeclaredField(name);
            SensitiveAnno sensitiveAnno;
            if (String.class != field.getType() || (sensitiveAnno = field.getAnnotation(SensitiveAnno.class)) == null) {
                return value;
            }
            String valueStr = (String) value;
            SensitiveType type = sensitiveAnno.type();
            switch (type) {
                case CHINESE_NAME:
                    return SensitiveInfoUtil.chineseName(valueStr);
                case ID_CARD:
                    return SensitiveInfoUtil.idCardNum(valueStr);
                case FIXED_PHONE:
                    return SensitiveInfoUtil.fixedPhone(valueStr);
                case MOBILE_PHONE:
                    return SensitiveInfoUtil.mobilePhone(valueStr);
                case ADDRESS:
                    return SensitiveInfoUtil.address(valueStr, 8);
                case EMAIL:
                    return SensitiveInfoUtil.email(valueStr);
                case BANK_CARD:
                    return SensitiveInfoUtil.bankCard(valueStr);
                case CNAPS_CODE:
                    return SensitiveInfoUtil.cnapsCode(valueStr);
                case PASSWORD:
                    return SensitiveInfoUtil.password(valueStr);
                case CARNUMBER:
                    return SensitiveInfoUtil.carNumber(valueStr);
                default:
            }
        } catch (NoSuchFieldException e) {
            log.error("当前数据类型为{},值为{}", object.getClass(), value);
            return value;
        }
        return value;
    }
}
