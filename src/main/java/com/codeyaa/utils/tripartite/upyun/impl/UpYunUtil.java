package com.codeyaa.utils.tripartite.upyun.impl;

import com.codeyaa.model.upyun.vo.ImageListResult;
import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.common.date.DateUtil;
import com.codeyaa.utils.tripartite.HttpClientBase;
import com.codeyaa.utils.tripartite.http.requests.RequestsUtil;
import com.codeyaa.utils.tripartite.upyun.UpYun;
import com.codeyaa.utils.tripartite.upyun.UpYunAuth;
import com.google.gson.Gson;
import com.upyun.RestManager;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import lombok.Data;
import net.dongliu.requests.Header;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UpYunUtil
 * @Description TODO
 * @Date 2021/5/24 18:40
 * @Created by DELL
 */
@Data
public class UpYunUtil implements UpYun {
    private String bucketName;
    private String userName;
    private String password;
    private final static Session REQUESTS = Requests.session();
    private final static String UPYUN_AUTO_BASE_URL = "http://v0.api.upyun.com/";
    private final static Gson GSON = new Gson();
    private final RestManager restManager;

    public UpYunUtil(String bucketName, String userName, String password) {
        this.bucketName = bucketName;
        this.userName = userName;
        this.password = password;
        restManager = new RestManager(bucketName, userName, password);
    }

    @Override
    public Response uploadImage(String upyunPath, InputStream inputStream, String fileName, String secret) {
        String apiUrl = upyunPath + fileName;
        Map<String, String> params = new HashMap<>();
        params.put(RestManager.PARAMS.CONTENT_SECRET.getValue(), secret);
        try {
            return restManager.writeFile(apiUrl, inputStream, params);
        } catch (IOException | UpException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Response uploadImageNoSecret(String upyunPath, InputStream inputStream, String fileName) {
        String apiUrl = UPYUN_AUTO_BASE_URL + bucketName + upyunPath + fileName;
        try {
            List<Header> headers = getSign(apiUrl, HttpClientBase.Methons.PUT);
            headers.add(new Header(HttpClientBase.HeaderParams.CONTENT_LENGTH, inputStream.available() + ""));
            headers.add(new Header(HttpClientBase.HeaderParams.CONTENT_TYPE, HttpClientBase.ContentTypes.IMAGE));

            String body = REQUESTS.put(apiUrl)
                    .headers(headers)
                    .multiPartBody(RequestsUtil.getPart(fileName, inputStream))
                    .send()
                    .readToText();
            if (StringUtils.isBlank(body)) {
                return getResponse(HttpClientBase.SUCCESS_STATUS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResponse(HttpClientBase.ERROR_STATUS_CODE);
    }

    @Override
    public Response deleteImage(String upyunPath) {
        String apiUrl = UPYUN_AUTO_BASE_URL + bucketName + upyunPath;
        try {
            List<Header> headers = getSign(apiUrl, HttpClientBase.Methons.DELETE);
            String body = REQUESTS.delete(apiUrl)
                    .headers(headers)
                    .send()
                    .readToText();
            if (StringUtils.isBlank(body)) {
                return getResponse(HttpClientBase.SUCCESS_STATUS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResponse(HttpClientBase.ERROR_STATUS_CODE);
    }

    /**
     * show 获取指定路径下最大 10000 张图片信息
     *
     * @param upyunPath
     * @return
     * @Param upyunPath  又拍云文件夹路径
     * @Return
     * @Author xin11.xin
     * @Date 2021/9/3 2:16
     */
    @Override
    public ImageListResult getImageListTen(String upyunPath) {
        String apiUrl = UPYUN_AUTO_BASE_URL + bucketName + upyunPath;
        try {
            List<Header> headers = getSign(apiUrl, HttpClientBase.Methons.GET);
            headers.add(new Header(HttpClientBase.HeaderParams.X_LIST_LIMIT, 10000));
            headers.add(new Header(HttpClientBase.HeaderParams.ACCEPT, HttpClientBase.ContentTypes.JSON));
            return GSON.fromJson(REQUESTS.get(apiUrl)
                    .headers(headers)
                    .send()
                    .readToText(), ImageListResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ImageListResult();
    }

    private Response getResponse(int code) {
        Response.Builder builder = new Response.Builder();
        Request request = new Request.Builder().url(UPYUN_AUTO_BASE_URL).build();
        return builder.request(request)
                .protocol(Protocol.HTTP_2)
                .code(code)
                .message("")
                .build();
    }

    private List<Header> getSign(String apiUrl, String Method) {
        String sign = null;
        String rfc1123Time = DateUtil.getRfc1123Time();
        try {
            sign = UpYunAuth.sign(userName, UpYunAuth.md5(password), Method, (new URI(apiUrl)).getRawPath(),
                    rfc1123Time, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Header authorization = new Header(HttpClientBase.HeaderParams.AUTHORIZATION, sign);
        Header dateHeader = new Header(HttpClientBase.HeaderParams.DATE, rfc1123Time);
        ArrayList<Header> headers = new ArrayList<>();
        headers.add(authorization);
        headers.add(dateHeader);
        return headers;
    }

}
