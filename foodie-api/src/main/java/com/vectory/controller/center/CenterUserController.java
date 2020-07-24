package com.vectory.controller.center;

import com.vectory.controller.BaseController;
import com.vectory.qo.UserUpdateQO;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.UserVO;
import com.vectory.resource.FileUpload;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.ICenterUserService;
import com.vectory.util.JsonUtil;
import com.vectory.util.CookieUtil;
import com.vectory.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Api(value = "USER_INFO")
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Resource
    private ICenterUserService centerUserService;
    @Resource
    private FileUpload fileUpload;

    @ApiOperation(value = "USER_UPLOAD", httpMethod = "POST")
    @PostMapping("uploadFace")
    public CommonReturnType uploadFace(@ApiParam(name = "userId", value = "用户ID", required = true)
                                           @RequestParam String userId,
                                       @ApiParam(name = "file", value = "头像图片", required = true)
                                       MultipartFile file,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        String fileSpace = fileUpload.getImageUserFaceLocation();
        String uploadPathPrefix = File.separator + userId;
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    String[] fileNameArr = fileName.split("\\.");
                    String suffix = fileNameArr[fileNameArr.length - 1];
                    if (!suffix.equalsIgnoreCase("png") &&
                            !suffix.equalsIgnoreCase("jpg") &&
                            !suffix.equalsIgnoreCase("jpeg") ) {
                        throw new BusinessException(EmBusinessResult.FILE_SUFFIX_ERROR);
                    }
                    // 文件名称重组 覆盖式上传，增量式：额外拼接当前时间
                    String newFileName = "face-" + userId + "." + suffix;
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                    // 用于提供给web服务访问的地址
                    uploadPathPrefix += ("/" + newFileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        outFile.getParentFile().mkdirs();
                        // 文件输出保存到目录
                        fileOutputStream = new FileOutputStream(outFile);
                        InputStream inputStream = file.getInputStream();
                        IOUtils.copy(inputStream, fileOutputStream);
                    }else {
                        throw new BusinessException(EmBusinessResult.IMAGE_UPLOAD_FAIL);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new BusinessException(EmBusinessResult.IMAGE_UPLOAD_FAIL);
        }
        String imageServerUrl = fileUpload.getImageServerUrl();
        // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        UserVO userVO = centerUserService.updateUserFace(userId, finalUserFaceUrl);
        log.info(JsonUtil.obj2String(userVO));
        CookieUtil.setCookie(request, response, "user", JsonUtil.obj2String(userVO), true);
        // TODO 二期 弃用cookie，生成用户token，存入redis会话
        return CommonReturnType.success();
    }

    @ApiOperation(value = "USER_UPDATE", httpMethod = "POST")
    @PostMapping("update")
    public CommonReturnType update(@RequestBody UserUpdateQO userUpdateQO,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        ValidatorUtil.validate(userUpdateQO);
        UserVO userVO = centerUserService.updateUserInfo(userUpdateQO);
        CookieUtil.setCookie(request, response, "user", JsonUtil.obj2String(userVO), true);
        // TODO 二期 弃用cookie，生成用户token，存入redis会话
        return CommonReturnType.success();
    }
}
