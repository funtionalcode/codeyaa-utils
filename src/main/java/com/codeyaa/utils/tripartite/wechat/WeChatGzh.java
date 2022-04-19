package com.codeyaa.utils.tripartite.wechat;

import com.codeyaa.model.vx.gzh.dto.DeleteDiyMenuRequest;
import com.codeyaa.model.vx.gzh.GzhMediaBase;
import com.codeyaa.model.vx.gzh.dto.SendMsgBase;
import com.codeyaa.model.vx.gzh.dto.SendMsgRequest;
import com.codeyaa.model.vx.gzh.dto.VxCheckRequest;
import com.codeyaa.model.vx.gzh.dto.diy.CreateDiyMenuRequest;
import com.codeyaa.model.vx.gzh.dto.only.CreateOnlyMenuRequest;
import com.codeyaa.model.vx.gzh.dto.xml.RequestBodyXml;
import com.codeyaa.model.vx.gzh.vo.ErrorResult;
import com.codeyaa.model.vx.gzh.vo.ImgListModel;
import com.codeyaa.model.vx.gzh.vo.OpenIdResult;
import com.codeyaa.model.vx.gzh.vo.UploadImgResultl;
import com.codeyaa.model.wechat.vo.AccessTokenResult;
import com.codeyaa.model.wechat.vo.UserInfoResult;
import com.codeyaa.utils.common.wxgzh.WXBizMsgCrypt;

import java.io.File;
import java.util.List;

public interface WeChatGzh {
    /**
     * @return 公众号 Token
     */
    AccessTokenResult getToken();

    /**
     * @return 公众号用户 OpenId
     */
    AccessTokenResult getOpenId(String code);

    /**
     * @return 公众号关注人数
     */
    double getGZHUserNum(String token);

    /**
     * @return 用户基本信息
     */
    UserInfoResult getUserInfo(String token, String openid);

    /**
     * @return 自动回复文本信息给用户
     */
    String sendXmlBody(RequestBodyXml requestBodyXml, String content, VxCheckRequest vxCheckRequest, boolean encode);


    /**
     * @return 自动回复语音信息给用户
     */
    String sendVoiceXmlBody(RequestBodyXml requestBodyXml, VxCheckRequest vxCheckRequest, boolean encode);

    /**
     * @return 发送图文信息给用户
     */
    String sendArticleXmlBody(RequestBodyXml requestBodyXml, String content, WXBizMsgCrypt pc, VxCheckRequest vxCheckRequest, boolean encode);

    /**
     * @return 自动回复图片信息给用户
     */
    String sendImgXmlBody(RequestBodyXml requestBodyXml, VxCheckRequest vxCheckRequest, boolean encode);

    /**
     * @return 上传本地图片
     */
    GzhMediaBase uploadImageInput(String type, File file, String token);

    /**
     * @return 批量上传本地图片
     */
    List<UploadImgResultl> batchUploadImages(String type, File[] files, String token);

    /**
     * @return 获取指定类型下的所有素材
     */
    List<GzhMediaBase> getAllGzhMedia(String type, String token);

    /**
     * @return 删除指定类型下的所有素材
     */
    ErrorResult deleteAllMedia(String type, String token);

    /**
     * @return 获取指定类型下的所有素材详情
     */
    ImgListModel getImgListDetailModel(String type, String token);

    /**
     * @return 下载公众号素材图片到本地，文件名自动生成
     */
    Boolean downloadImage(String url, String location);

    /**
     * show 下载公众号素材图片到本地，指定文件名.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @Param url
     * @Param location
     * @Param name
     * @Return
     * @Author xin11.xin
     * @Date 2021/10/28 22:38
     */
    File downloadImage(String url, String location, String name);

    /**
     * show 获取所有用户的openId.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @Param token
     * @Return
     * @Author xin11.xin
     * @Date 2021/10/28 22:39
     */
    OpenIdResult getUserOpenId(String token);

    ErrorResult sendPreText(SendMsgRequest sendMsgRequest);

    ErrorResult sendPreTextRequests(SendMsgBase sendMsgRequest);

    ErrorResult sendTextRequests(SendMsgBase sendMsgRequest);

    ErrorResult createOnlyMenu(CreateOnlyMenuRequest createOnlyMenuRequest, String token);

    ErrorResult deleteOnlyMenu(String token);

    ErrorResult createDiyMenu(CreateDiyMenuRequest createMenuRequest, String token);

    ErrorResult deleteDiyMenu(DeleteDiyMenuRequest deleteDiyMenu, String token);
}
