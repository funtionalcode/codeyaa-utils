package com.codeyaa.utils.tripartite;

import com.codeyaa.model.hw.dto.*;
import com.codeyaa.model.hw.vo.ImgResult;
import com.codeyaa.utils.common.FileUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import net.dongliu.requests.Header;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Funtionalcode
 * @className HUAWEIUtil
 * @description 华为接口工具类
 * @date 2021/5/27 12:11
 */
@AllArgsConstructor
public class HUAWEIUtil {
    /**
     * 华为控制台用户id
     */
    private final String userName;
    /**
     * 华为控制台父用户id，非IAM用户则与上用户id一致
     */
    private final String domainName;
    /**
     * 用户登录密码
     */
    private final String passWord;
    /**
     * 项目地区代码
     */
    private final String projectName;

    /**
     * @Description: 华为接口认证获取token
     * @return: 取token
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 15:48
     */
    public String getToken() {
        final String apiUrl = "https://iam.myhuaweicloud.com/v3/auth/tokens?nocatalog=true";
        HWTokenModel hwTokenModel = new HWTokenModel();
        Auth auth = new Auth();
        Identity identity = new Identity();
        ArrayList<String> methodList = new ArrayList<>();
        methodList.add("password");
        identity.setMethods(methodList);
        Password password = new Password();
        User user = new User();
        Domain domain = new Domain();
        domain.setName(domainName);
        user.setDomain(domain);
        user.setName(userName);
        user.setPassword(passWord);
        password.setUser(user);
        identity.setPassword(password);
        auth.setIdentity(identity);
        Scope scope = new Scope();
        Project project = new Project();
        project.setName(projectName);
        scope.setProject(project);
        auth.setScope(scope);
        hwTokenModel.setAuth(auth);

        RawResponse response = Requests.session().post(apiUrl)
                .jsonBody(hwTokenModel)
                .charset(StandardCharsets.UTF_8)
                .send();
        String token = response.getHeader("X-Subject-Token");
        String res = response.readToText();
        return token;
    }

    /**
     * @param endpoint  图文识别控制台区域域名
     * @param projectId 图文识别控制台项目ID
     * @param imgPath   本地图片绝对路径
     * @param token     华为认证 Token
     * @Description: 图文识别获取所有文字
     * @return: 图文识别结果
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 15:49
     */
    public ImgResult readImage(String endpoint, String projectId, String imgPath, String token) {
        String apiUrl = String.format("https://%s/v2/%s/ocr/web-image", endpoint, projectId);
        ArrayList<Header> headers = new ArrayList<>();
        headers.add(new Header("X-Auth-Token", token));
        headers.add(new Header("Accept", "application/json"));
        ImgRequest imgRequest = new ImgRequest();
        byte[] fileBytes = FileUtil.getFilePathBytes(imgPath);
        String img = Base64.encodeBase64String(fileBytes);
        imgRequest.setImage(img);
        imgRequest.setDetect_direction(true);
        RawResponse response = Requests.session()
                .post(apiUrl)
                .headers(headers)
                .jsonBody(imgRequest)
                .charset(StandardCharsets.UTF_8)
                .send();

        String res = response.readToText();
        Gson gson = new Gson();

        return gson.fromJson(res, ImgResult.class);
    }
}
