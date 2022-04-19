package com.codeyaa;

import com.codeyaa.model.calendar.dto.TXYearRequest;
import com.codeyaa.model.calendar.vo.BDCalendarVo;
import com.codeyaa.model.calendar.vo.TXYearVo;
import com.codeyaa.model.vx.gzh.GzhMediaBase;
import com.codeyaa.model.vx.gzh.dto.SendMsgRequest;
import com.codeyaa.model.vx.gzh.dto.SendMsgRequest.TextBean;
import com.codeyaa.model.vx.gzh.vo.ErrorResult;
import com.codeyaa.model.vx.gzh.vo.UploadImgResultl;
import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.tripartite.tianxin.calendar.impl.CalendarUtil;
import com.codeyaa.utils.tripartite.wechat.client.WeChatGzhClient;
import com.codeyaa.utils.tripartite.wechat.impl.WeChatGZHUtil;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class GzhTest {
    private static WeChatGZHUtil weChatGZHUtil = new WeChatGZHUtil();
    private static WeChatGzhClient wechatGzhClient;
    private static CalendarUtil calendarUtil;

    public static void main(String[] args) {
        imgList();
    }

    private static void imgList() {
        List<GzhMediaBase> imgListModel = weChatGZHUtil.getAllGzhMedia("image", "53_d_J9RuNfop6xY3lzrcIs1lR7aCeyLoWquHCyIV4QFeOKGMm0L9ZMvZU9FnwW-CJ8B3vvGoYLXEyGlmlrCTo8ynSrR8DtEM8ZMkPKSrFMKqklAkQ9aRkggOJdeTQliYhy2i1ZgAIfqZxCzoeoDIDhAAAUZU");
        imgListModel.forEach(System.out::println);
    }
    @SneakyThrows
    private static void uploadInputstream() {
        String downloadUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/j2wuJF4j9M3INwYktVbBVI1eU9h6C2pdBkgdlBFvgny4e1deUgicfmLqNiaYJCkafD7qO71oH22vic49Onp33EZZA/0";
        String file = "d:\\";
        String fileName = "haogege.png";
        File inputStream = wechatGzhClient.downloadImage(downloadUrl, file, fileName);
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material";
        String token = "48_jqPsJt0eZvq-_1jWFnm3HVrFfHb2NCqo3IhJfSlWijs_Cu63ylM2oAqCP8kBAV_cOKM-97t2fkLDET1Mp0aE4LjIYyLoDRHM_oKdN9t-JtUkIulQ6BGy_Y4sUiqsY9HoLwpJlJ2ce-OUKpi5FWWjAHAVMM";
        String type = "image";
        String apiUrl = url + "?access_token=" + token + "&type=" + type;

        UploadImgResultl uploadImgResultl = wechatGzhClient.uploadImage(apiUrl, inputStream.toString());
        System.out.println("uploadImgResultl = " + uploadImgResultl);
    }

    private static void sendMsg() {

        String text = "【摸鱼办公室】 8月24日\n" +
                "下午好，摸鱼人，工作再累，一定不要忘记摸鱼哦\n" +
                "有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的\n" +
                "\n" +
                "距离中秋假期还有26天\n" +
                "距离国庆假期还有38天\n" +
                "距离跨年假期还有129天\n" +
                "距离春节假期还有160天";
        SendMsgRequest sendMsgRequest = new SendMsgRequest();
        ArrayList<String> toUsers = new ArrayList<>();
        toUsers.add("oxOvS5oVWbbS_gCHh4TaHlBNWzaE");
        sendMsgRequest.setTouser(toUsers);
        TextBean textBean = new TextBean();
        textBean.setContent(text);
        sendMsgRequest.setText(textBean);
        sendMsgRequest.setMsgtype("text");
        sendMsgRequest.setIsTmp(true);
        sendMsgRequest.setToken("48_G7gtxUc9_x0Ji0JpGNjWE5qda-zXsmkGd9SJIBBrDIlJl7lkoZHw5j7YtNFYGodGmCvjW2Xi8oP178tRZ4QAEIqu-KyAV4VZGaWzblepeWH86QaVQUFYDiz3JfS9moncX9AJEtmNmyETk8EdGUQfADANBX");

        ErrorResult errorResult = weChatGZHUtil.sendPreText(sendMsgRequest);
        System.out.println("errorResult = " + errorResult);
    }

    private static void queryMonth() {
        Optional.ofNullable(calendarUtil.getMonthCalendar("2022-01"))
                .map(BDCalendarVo::getData)
                .map(BDCalendarVo.DataBean::getList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> StringUtils.isNotBlank(item.getSolarFestival()))
                .collect(Collectors.toSet()).forEach(System.out::println);

    }

    private static void txQueryYear() {
        TXYearRequest txYearRequest = new TXYearRequest();
        txYearRequest.setKey("b469943bf1667f1d740126d94ae17823");
        txYearRequest.setType("1");
        txYearRequest.setDate("2021");

        Optional.ofNullable(calendarUtil.txGetMonthYear(txYearRequest))
                .map(TXYearVo::getNewslist)
                .orElse(new ArrayList<>())
                .stream()
                .map(item->(TXYearVo.NewslistBean) item)
                .filter(item -> StringUtils.isNotBlank(item.getName()))
                .filter(item -> Boolean.FALSE.equals(item.getName().contains("清明")))
                .filter(item -> Boolean.FALSE.equals(item.getName().contains("劳动")))
                .filter(item -> StringUtils.isNotBlank(item.getName()))
                .peek(row -> {
                    String rowWage = row.getWage();
                    String vacation = row.getVacation().get(0);
                    if (StringUtils.isNotBlank(rowWage) && rowWage.contains("|")) {
                        String wage = rowWage.split("\\|")[0];
                        row.setWage(wage);
                    } else if (StringUtils.isNotBlank(vacation)) {
                        if (vacation.contains("|")) {
                            String value = vacation.split("\\|")[0];
                            row.setWage(value);
                        } else {
                            row.setWage(vacation);
                        }
                    }
                })
                .sorted(Comparator.comparing(TXYearVo.NewslistBean::getWage, (x, y) -> {
                    if (x.contains("|")) {
                        x = x.split("\\|")[0];
                    }
                    if (y.contains("|")) {
                        y = y.split("\\|")[0];
                    }
                    return x.compareTo(y);
                }))
                .collect(Collectors.toCollection(LinkedHashSet::new)).forEach(System.out::println);

    }
}
