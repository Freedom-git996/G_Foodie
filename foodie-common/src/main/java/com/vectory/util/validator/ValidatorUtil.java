package com.vectory.util.validator;

import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {

    public static Validator validator;

    static {
        validator = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    /**
     * 静态方法校验使用的
     * @param object object
     */
    public static void validate(Object object) {
        if(object == null){
            throw new BusinessException(EmBusinessResult.ILLEGAL_ARGUMENT);
        }
        Set<ConstraintViolation<Object>> validate = validator.validate(object);
        String errMsg = null;
        if(!"success".equals(errMsg = resultValidate(validate)))
            throw new BusinessException(EmBusinessResult.ILLEGAL_ARGUMENT.setMsg(errMsg));

    }

    /**
     * 静态方法校验使用，并且带分组的
     * @param object object
     * @param group group
     * @return String
     */
    public static void validate(Object object, Class group) {
        if (group == null) {
            validate(object);
        } else {
            Set<ConstraintViolation<Object>> validate = validator.validate(object, group);
            String errMsg = null;
            if(!"success".equals(errMsg = resultValidate(validate)))
                throw new BusinessException(EmBusinessResult.ILLEGAL_ARGUMENT.setMsg(errMsg));
        }
    }

    private static String resultValidate(Set<ConstraintViolation<Object>> validate) {
        if (!validate.isEmpty()) {
            final StringBuffer stringBuffer = new StringBuffer();
            validate.forEach(
                    item -> stringBuffer.append(item.getMessage()).append(","));
            stringBuffer.setLength(stringBuffer.length() - 1);
            return stringBuffer.toString();
        }
        return "success";
    }
}
