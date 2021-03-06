package com.zhb.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Service;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;

import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


@Service
public class GlobalApi {
    public static final String UTF_8 = "UTF-8";
    String temple;

    public String getsDate(Map fileName) throws ParseException {

        //String sa = returnTimeContformat("2018-11-04", "cc");
        if (returnMap(fileName, "4c9").equals("X")) {
            temple = "        \"request_type\": {\n" +
                    "            \"name\": \"Incident\",\n" +
                    "            \"id\": \"1\"\n" +
                    "        },\n";
            System.out.println("ok");
        } else if (returnMap(fileName, "4c18").equals("X")) {
            temple = "        \"request_type\": {\n" +
                    "            \"name\": \"Inquiry\",\n" +
                    "            \"id\": \"3\"\n" +
                    "        },\n";
            System.out.println("ok");
        } else if (returnMap(fileName, "4c27").equals("X")) {
            temple = "        \"request_type\": {\n" +
                    "            \"name\": \"Request\",\n" +
                    "            \"id\": \"2\"\n" +
                    "        },\n";
            System.out.println("ok");
        }
        //  returnMap(fileName,"2c0");

        // String url = "http://120.46.155.62:8080/api/v3/requests";
        String url = "https://120.46.155.62:8080/api/v3/requests";
//        String data = "{\n" +
//                "    \"resolution\": {\n" +
//               "        \"content\": \"sample resolution\"\n" +
//                "    }\n" +
//              "}";
        String json = "{\n" +
                "  \"request\": {\n " +
                "   \"subject\": \"" + returnMap(fileName, "6c6").toString() + "\",\n" +
                "          \"description\": \"??????????????????????????????IR?????????????????? ???????????????\",\n" +
                " \"resolution\": { \n" +
                "     \"content\": \"???????????????????????????1\"\n" +
                "  },\n" +
                "    \"site\": {\n" +
                "       \"name\": \"" + returnSFTM(returnMap(fileName, "9c6")) + "\",\n" +
                //     "               \"id\": \"2\" \n"+
                "   },\n" +
                "   \"requester\": {\n" +
//                "       \"email_id\": \"supportdesk@tftm.com.cn\",\n"+
//                "               \"phone\": null,\n"+
                "               \"name\": \"" + returnMap(fileName, "8c6").toString() + "\",\n" +
//                "             \"mobile\": null,\n"+
//                "            \"is_vipuser\": false,\n "+
//                "             \"id\": \"1866\",\n"+
//                "             \"department\": null\n"+
                "     },\n" +
                "    \"template\": {\n" +
                "\"is_service_template\": false,\n" +
                "  \"name\": \"IR??????Global\",\n" +
                " \"id\": \"2401\",\n" +
                "},\n" +
                "     \"udf_fields\":{\n" +
                "\"udf_sline_303\":\"" + returnMap(fileName, "2c8").toString() + "\",\n" +
                " \"udf_sline_306\":\"" + returnMap(fileName, "2c24").toString() + "\",\n" +
                // "\"udf_date_31\":\""+ returnMap(fileName,"12c1").toString()+"\",\n"+
//                "     \"udf_date_31\": {\n" +
//                //  "\"display_value\": \"27/09/2021 10:04 PM\",\n"+
//                "\"value\": \"" + returnTime(returnMap(fileName, "12c6"), returnMap(fileName, "13c6")) + "\"\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"12c6")),returnMap(fileName,"13c6"),"udf_date_31")+    //????????????????????????
                // "\"udf_date_31\":\"1632751440000\",\n"+
                "\"udf_sline_601\":\"" + returnMap(fileName, "20c6").toString() + "\",\n" +
//                "     \"udf_date_28\": {\n" +
//                " \"value\":\"" + returnTime(returnMap(fileName, "20c19"), returnMap(fileName, "20c30")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"20c19")),returnMap(fileName,"20c30"),"udf_date_28")+    //????????????????????????
                "\"udf_pick_1208\":\"" + returnMap(fileName, "8c30").toString() + "\",\n" +
//                "     \"udf_date_32\": {\n" +
//                " \"value\":\"" + returnTime(returnMap(fileName, "12c19"), returnMap(fileName, "13c19")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"12c19")),returnMap(fileName,"13c19"),"udf_date_32")+
//                "     \"udf_date_33\": {\n" +
//                " \"value\":\"" + returnDate(returnMap(fileName, "48c8")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"48c8")),"udf_date_33")+

                //    "\"udf_date_33\":null,\n"+
                " \"udf_multiselect_1802\":[\"" + returnMap(fileName, "14c6").toString() + "\"],\n" +   //System
                //        " \"udf_multiselect_1802\":[\"GLA\"],\n"+              //excel ??????????????????

                " \"udf_pick_35\":" + returnIsNull(returnMap(fileName, "14c24").toString()) + ",\n" +
                " \"udf_mline_34\":\"" + returnStrtrans(returnMap(fileName, "15c6").toString()) + "\",\n" +
                " \"udf_pick_40\":" + returnIsNull(returnMap(fileName, "22c26").toString()) + ",\n" +
                " \"udf_mline_1501\":\"" + returnStrtrans(returnMap(fileName, "23c1")) + "\",\n" +
                " \"udf_mline_901\":\"" + returnStrtrans(returnMap(fileName, "32c1")) + "\",\n" +
                " \"udf_sline_603\":\"" + returnStrtrans(returnMap(fileName, "30c8")) + "\",\n" +
                //     " \"Analyst\":\""+ returnMap(fileName,"30c8").toString()+"\",\n"+
                " \"udf_pick_66\":" + returnIsNull(returnMap(fileName, "31c8").toString()) + ",\n" +
                " \"udf_sline_604\":\"" + returnMap(fileName, "30c24").toString() + "\",\n" +
                " \"udf_sline_605\":\"" + returnMap(fileName, "30c32").toString() + "\",\n" +
                " \"udf_pick_1214\":" + returnIsNull(returnMap(fileName, "31c26").toString()) + ",\n" +
                " \"udf_mline_41\":\"" + returnStrtrans(returnMap(fileName, "40c1")) + "\",\n" +
                " \"udf_sline_1202\":\"" + returnMap(fileName, "38c8").toString() + "\",\n" +

                // " \"udf_date_42\":\""+ returnTime(returnMap(fileName,"39c8"),returnMap(fileName,"39c16"))+"\",\n"+
//                "     \"udf_date_42\": {\n" +
//                " \"value\":\"" + returnTime(returnMap(fileName, "39c8"), returnMap(fileName, "39c16")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"39c8")),returnMap(fileName,"39c16"),"udf_date_42")+
                " \"udf_sline_902\":\"" + returnMap(fileName, "38c24").toString() + "\",\n" +
                " \"udf_sline_903\":\"" + returnMap(fileName, "38c32").toString() + "\",\n" +
                // " \"udf_sline_306\":\"GL20210928\",\n"+
                //   " \"udf_pick_40\":\""+ returnMap(fileName,"22c26").toString()+"\",\n"+
                " \"udf_mline_65\":\"" + returnStrtrans(returnMap(fileName, "49c1")) + "\",\n" +
                " \"udf_sline_1203\":\"" + returnMap(fileName, "47c8").toString() + "\",\n" +
//                "     \"udf_date_44\": {\n" +
//                " \"value\":\"" + returnDate(returnChinas(returnMap(fileName, "48c20"))) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"48c20")),"udf_date_44")+
                " \"udf_sline_905\":\"" + returnMap(fileName, "47c24").toString() + "\",\n" +
                " \"udf_sline_906\":\"" + returnMap(fileName, "47c32").toString() + "\",\n" +
                " \"udf_pick_1209\":" + returnIsNull(returnMap(fileName, "48c32").toString()) + ",\n" +
                " \"udf_sline_1216\":\"" + returnMap(fileName, "56c8").toString() + "\",\n" +
//                "     \"udf_date_46\": {\n" +
//                " \"value\":\"" + returnTime(returnMap(fileName, "57c8"), returnMap(fileName, "57c16")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"57c8")),returnMap(fileName,"57c16"),"udf_date_46")+
                " \"udf_pick_1215\":\"" + returnMap(fileName, "56c26").toString() + "\",\n" +
                " \"udf_pick_1210\":\"" + returnMap(fileName, "57c26").toString() + "\",\n" +

                "     },\n" +
//                " \"urgency\": {\n" +
//                " \"name\": \"" + returnMap(fileName, "13c31") + "\",\n" +
//
//                "},\n" +
                " \"urgency\":"+returnIsNulls(returnMap(fileName,"13c31"),"name")+
                "    \"status\": {\n" +
                "        \"name\": \"Open\"\n" +
                "    },\n" +
                temple +
                "}}";

        //  JSONObject jsonss = JSONObject.fromObject(json);
        //System.out.println(jsonss);
        System.out.println(json);


        int socketTimeout = 120 * 1000;
        int connectTimeout = 120 * 1000;
        Map<String, String> params = new HashMap<>();
        params.put("input_data", json);
        //  params.put("format", "josn");
        //    params.put("type","POST");
        Map<String, String> headers = new HashMap<>();
        headers.put("TECHNICIAN_KEY", "AE11172E-ED73-4952-9047-E3D58350AB16");
        // headers.put("Content-Type", "application/json");
        String response = null;
        try {
            response = GlobalApi.sendPost(url, socketTimeout, connectTimeout, headers, params, json);
        } catch (Exception e) {
            e.printStackTrace();
            return "??????????????????<br><br>" + e.toString();
        }
        System.out.println("==================================================");
        System.out.println(response);
        return "??????????????????,????????????<br><br>\t\t:" + response.toString();
    }


    public static String sendPost(String url, int socketTimeout, int connectTimeout,
                                  Map<String, String> headers, Map<String, ?> params,
                                  String json) throws Exception {
        // URL??????: ?????????????????????URL?????????
        if (null != params && params.size() != 0) {
            List<NameValuePair> list = new ArrayList<>();
            for (Entry<String, ?> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            String uri = EntityUtils.toString(new UrlEncodedFormEntity(list, UTF_8));
            if (uri != null) {
                url = url.contains("?") ? (url + "&" + uri) : (url + "?" + uri);
            }
        }

        // ????????????: HttpPost
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();
        post.setConfig(config);

        // ???????????????: Header
        if (null != headers && headers.size() != 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // ???????????????: HttpEntity
        if (null != json && !"".equals(json.trim())) {
//            JSONObject json_test = JSONObject.fromObject(json);
            post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }

        // ?????????????????????
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), UTF_8);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
    }

    public String returnMap(Map fileName, String key) {

        return fileName.get(key).toString();


    }

    /**
     * 10???13???????????????String ??????(2018-10-15 16:03:27) ??????
     *
     * @param timestamp
     * @param simpleDateFormatType ???????????????("yyyy-MM-dd HH:mm:ss")
     * @return ????????????????????????????????????????????????
     * ????????????????????????CSDN?????????????????????-?????????????????????????????????CC 4.0 BY-SA???????????????????????????????????????????????????????????????
     * ???????????????https://blog.csdn.net/weixin_32271479/article/details/114808967
     * ????????????????????????????????????????????????
     * ????????????????????????CSDN?????????????????????-?????????????????????????????????CC 4.0 BY-SA???????????????????????????????????????????????????????????????
     * ???????????????https://blog.csdn.net/weixin_32271479/article/details/114808967
     */

    public long returnTime(String GlobalDate, String GlobalTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = sdf.parse(GlobalDate + " " + GlobalTime);
        System.out.println(date.toString() + "???????????????");

        long timeInMillisSinceEpoch = date.getTime();
        long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
        System.out.println(timeInMillisSinceEpoch + "?????????");
        return timeInMillisSinceEpoch;

    }

    public long returnDate(String GlobalDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse(GlobalDate);
        System.out.println(date.toString() + "???????????????");

        long timeInMillisSinceEpoch = date.getTime();
        long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
        System.out.println(timeInMillisSinceEpoch + "?????????");
        return timeInMillisSinceEpoch;

    }

    public String returnStrtrans(String text) {
        return text.replaceAll("\n", "\\\\n");


    }

    public String returnChinas(String GlobalDate) {
        Map<String, String> chineseMap = new HashMap<String, String>();
        chineseMap.put("??????", "01");
        chineseMap.put("??????", "02");
        chineseMap.put("??????", "03");
        chineseMap.put("??????", "04");
        chineseMap.put("??????", "05");
        chineseMap.put("??????", "06");
        chineseMap.put("??????", "07");
        chineseMap.put("??????", "08");
        chineseMap.put("??????", "09");
        chineseMap.put("??????", "10");
        chineseMap.put("?????????", "11");
        chineseMap.put("?????????", "12");

        String[] strArray = GlobalDate.split("-");
        return strArray[2] + "/" + chineseMap.get(strArray[1]) + "/" + strArray[0];

    }

    public String returnSFTM(String site) {
        if (site.equals("SFTM")) {
            return "SFTM_Sichuan";

        }
        return site;
    }

    public String returnTimeContformat(String time) {
        if (time.equals("")) {
//            time ="        \""+fonts+"\": null +";
            time = null;
            return time;

        } else if (time.matches("^\\d{4}(\\/)\\d{1,2}\\1\\d{1,2}$")) // YYYY/MM/dd
        {
            return time;
        } else if (time.matches("\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}")) // YYYY-MM-dd
        {
            time = time.replaceAll("-", "/");

            return time;
        }
        else if (time.matches("\\d{4}(\\.)\\d{1,2}\\1\\d{1,2}")) // YYYY.MM.dd
        {
            time = time.replaceAll("\\.", "/");
            return time;
        }
        else if (time.matches("\\d{1,2}(\\-)[\\u4E00-\\u9FA5]{2,3}\\1\\d{4}")) // dd-MM(??????)-YYYY
        {
            time = returnChinas(time);
            return time;
        }


        return null;
    }
    public String returnIsshowDateTime(String fomatGlobaldate,String Globaltime,String fonts) throws ParseException {   //api??????json
        if(fomatGlobaldate==null) {
            return "        \"" + fonts + "\": null,\n ";

        }
       else if(fomatGlobaldate!=null&&Globaltime.equals(""))
        {

            try {
                return "     \""+fonts+"\": {\n" +
                        " \"value\":\"" + returnDate(fomatGlobaldate) + "\",\n" +
                        " },\n";
            } catch (ParseException e) {
                e.printStackTrace();
                return "        \"" + fonts + "\": null,\n ";
            }
        }


            return "     \""+fonts+"\": {\n" +
                    " \"value\":\"" +returnTime(fomatGlobaldate,Globaltime) + "\",\n" +
                    " },\n";


    }
    public String returnIsshowDateTime(String fomatGlobaldate,String fonts) throws ParseException {
        if(fomatGlobaldate==null)
        {
            return "        \"" + fonts + "\": null,\n ";
        }
        return "     \""+fonts+"\": {\n" +
                " \"value\":\"" +returnDate(fomatGlobaldate) + "\",\n" +
                " },\n";

    }
    public String returnIsNull(String ns){                  //??????????????????
        if (ns.equals(""))
        {
            return null;
        }
        return  "     \""+ns+"\"";

    }
    public  String returnIsNulls(String ns,String name){
        if (ns.equals(""))
        {
            return null+",\n";
        }
        return "{\n" +
        " \""+name+"\": \"" + ns+ "\",\n" +

                "},\n";
    }

    }

