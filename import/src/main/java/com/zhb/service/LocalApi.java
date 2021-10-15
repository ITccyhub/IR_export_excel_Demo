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
public class LocalApi {
    public static final String UTF_8 = "UTF-8";
    String temple;

    public String getsDate(Map fileName) throws ParseException {
        if(returnMap(fileName,"51c36").equals("3.0"))
        {
            temple ="        \"template\": {\n" +
                    "            \"is_service_template\": false,\n" +
                //    "            \"name\": \"IR请求Global\",\n" +
                    "            \"id\": \"2401\"\n" +
                    "        },\n" +
                    "        \"request_type\": {\n" +
                    "            \"name\": \"Request\",\n" +
                    "            \"id\": \"2\"\n" +
                    "        },\n";
            System.out.println("这是3");
        }
        else if (returnMap(fileName,"51c36").equals("2.0"))
        {   temple ="        \"template\": {\n" +
                "            \"is_service_template\": false,\n" +
            //    "            \"name\": \"IR请求Global\",\n" +
                "            \"id\": \"2401\"\n" +
                "        },\n" +
                "        \"request_type\": {\n" +
                "            \"name\": \"Inquiry\",\n" +
                "            \"id\": \"3\"\n" +
                "        },\n";
            System.out.println("这是2");

        }
        else if (returnMap(fileName,"51c36").equals("1.0"))
        {
            temple= "       \"template\": {\n" +
                    "            \"is_service_template\": false,\n" +
                 //   "            \"name\": \"IR请求Global\",\n" +
                    "            \"id\": \"2401\"\n" +
                    "        },\n" +
                    "        \"request_type\": {\n" +
                    "            \"name\": \"Incident\",\n" +
                    "            \"id\": \"1\"\n" +
                    "        },\n";
            System.out.println("这是1");


        }

        // String url = "http://120.46.155.62:8080/api/v3/requests";
        String url = "https://120.46.155.62:8080/api/v3/requests";
//        String data = "{\n" +
//                "    \"resolution\": {\n" +
//               "        \"content\": \"sample resolution\"\n" +
//                "    }\n" +
//              "}";
        String json="{\n" +
                "    \"request\": {\n" +
                "        \"subject\": \""+returnMap(fileName,"7c6")+"\",\n" +
                "        \"description\": \"I am unable to fetch mails from the mail server\",\n" +
                "        \"resolution\": {\n" +
                "            \"content\": \"Mail Fetching Server problem has been fixed\"\n" +
                "        },\n" +
                "        \"site\": {\n" +
                "            \"name\": \""+returnCompany(returnMap(fileName,"12c6").toString())+"\",\n" +
                "        },\n" +
                "     \"group\": {\n" +
                "            \"site\": {\n" +
                "                \"id\": 2\n" +
                "            },\n" +
                "            \"name\": \"天大天星\",\n" +
                "            \"id\": \"604\"\n" +
                "        },\n" +
                "        \"requester\": {\n" +

                "            \"name\": \""+returnMap(fileName,"11c6")+"\",\n" +

                "        },\n" +
                "  \"udf_fields\": {\n" +
                "\"udf_sline_601\":\""+ returnMap(fileName,"22c8").toString()+"\",\n"+
//                "     \"udf_date_31\": {\n"+
//                //  "\"display_value\": \"27/09/2021 10:04 PM\",\n"+
//                "\"value\": \""+returnDate(returnMap(fileName,"6c19"))+"\"\n"+
//                " },\n"+
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"6c19")),"udf_date_31")+    //小陈专业时间处理
//                "     \"udf_date_32\": {\n"+
//                " \"value\":\""+returnDate(returnMap(fileName,"15c19"))+"\",\n"+
//                " },\n"+
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"15c19")),"udf_date_32")+    //小陈专业时间处理
                " \"udf_sline_603\":\""+ returnMap(fileName,"27c8").toString()+"\",\n"+
                " \"udf_mline_34\":\""+ returnStrtrans(returnMap(fileName,"18c6").toString())+"\",\n"+
                " \"udf_pick_35\":"+ returnIsNull(returnMap(fileName,"17c24").toString())+",\n"+

//                "     \"udf_date_42\": {\n"+
//                " \"value\":\""+returnDate(returnMap(fileName,"34c8"))+"\",\n"+
//                " },\n"+
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"34c8")),"udf_date_42")+    //小陈专业时间处理
                " \"udf_pick_1214\":"+ returnIsNull(returnMap(fileName,"28c26").toString())+",\n"+
                " \"udf_sline_1202\":\""+ returnMap(fileName,"33c8").toString()+"\",\n"+
                " \"udf_pick_40\":"+ returnIsNull(returnMap(fileName,"22c26").toString())+",\n"+
                "\"udf_sline_303\":\""+ returnMap(fileName,"2c15").toString()+"\",\n"+
                "\"udf_pick_1208\":\""+ returnMap(fileName,"11c30").toString()+"\",\n"+
//                "     \"udf_date_28\": {\n"+
//                " \"value\":\""+returnDate(returnMap(fileName,"6c19"))+"\",\n"+
//                " },\n"+
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"6c19")),"udf_date_28")+    //小陈专业时间处理
                " \"udf_sline_604\":\""+ returnMap(fileName,"27c16").toString()+"\",\n"+
//                "     \"udf_date_33\": {\n"+
//                " \"value\":\""+returnDate(returnMap(fileName,"15c19"))+"\",\n"+
//                " },\n"+
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"15c19")),"udf_date_33")+    //小陈专业时间处理
                " \"udf_sline_605\":\""+ returnMap(fileName,"27c24").toString()+"\",\n"+
                " \"udf_multiselect_1802\":[\"" + returnMap(fileName, "17c6").toString() + "\"],\n"+ //+ System
                " \"udf_mline_1501\":\"" + returnStrtrans(returnMap(fileName, "33c1")) + "\",\n" +
                " \"udf_mline_901\":\"" + returnStrtrans(returnMap(fileName, "29c1")) + "\",\n" +
                " \"udf_pick_66\":" + returnIsNull(returnMap(fileName, "28c8").toString()) + ",\n" +
                " \"udf_mline_41\":\"" + returnStrtrans(returnMap(fileName, "35c1")) + "\",\n" +
                " \"udf_sline_902\":\"" + returnMap(fileName, "33c16").toString() + "\",\n" +
                " \"udf_sline_903\":\"" + returnMap(fileName, "33c24").toString() + "\",\n" +
                " \"udf_mline_65\":\"" + returnStrtrans(returnMap(fileName, "44c1")) + "\",\n" +
                " \"udf_sline_1203\":\"" + returnMap(fileName, "42c8").toString() + "\",\n" +
//                "     \"udf_date_44\": {\n" +
//                " \"value\":\"" + returnTime(returnChinas(returnMap(fileName, "43c8")), returnMap(fileName, "43c16")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"43c8")),returnMap(fileName,"43c16"),"udf_date_44")+
                " \"udf_sline_905\":\"" + returnMap(fileName, "42c16").toString() + "\",\n" +
                " \"udf_sline_906\":\"" + returnMap(fileName, "42c24").toString() + "\",\n" +
                " \"udf_pick_1209\":" + returnIsNull(returnMap(fileName, "43c24").toString()) + ",\n" +
                " \"udf_sline_1216\":\"" + returnMap(fileName, "50c8").toString() + "\",\n" +
//                "     \"udf_date_46\": {\n" +
//                " \"value\":\"" + returnTime(returnChinas(returnMap(fileName, "51c8")), returnMap(fileName, "51c16")) + "\",\n" +
//                " },\n" +
                returnIsshowDateTime(returnTimeContformat(returnMap(fileName,"51c8")),returnMap(fileName,"51c16"),"udf_date_46")+
                " \"udf_pick_1215\":\"" + returnMap(fileName, "50c26").toString() + "\",\n" +
                " \"udf_pick_1210\":\"" + returnMap(fileName, "51c26").toString() + "\",\n" +
                " },\n"+

//                " \"urgency\": {\n"+
//                " \"name\": \""+returnMap(fileName,"9c6")+"\",\n"+
//
//                "},\n"+
                " \"urgency\":"+returnIsNulls(returnMap(fileName,"9c6"),"name")+
                "        \"status\": {\n" +
                "            \"name\": \"Open\"\n" +
                "        },\n" +

                temple+
                "}}";

        //  JSONObject jsonss = JSONObject.fromObject(json);
        //System.out.println(jsonss);
        System.out.println(json);

        int socketTimeout = 120 * 1000;
        int connectTimeout = 120 * 1000;
        Map<String, String> params = new HashMap<>();
        params.put("input_data",json);
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
            return "数据处理失败,数据上传:<br><br>\t\t"+e.toString();
        }
        System.out.println("==================================================");

        System.out.println(response);
        return "数据处理成功<br><br>，数据上传:"+response.toString();
    }


    public static String sendPost(String url, int socketTimeout, int connectTimeout,
                                  Map<String, String> headers, Map<String, ?> params,
                                  String json) throws Exception {
        // URL处理: 请求参数拼接到URL中传递
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

        // 构建请求: HttpPost
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();
        post.setConfig(config);

        // 设置请求头: Header
        if (null != headers && headers.size() != 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置请求体: HttpEntity
        if (null != json && !"".equals(json.trim())) {
//            JSONObject json_test = JSONObject.fromObject(json);
            post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }

        // 调用并返回结果
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
    public String returnMap(Map fileName,String key){

        return fileName.get(key).toString();



    }
    public long returnTime(String GlobalDate,String GlobalTime) throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = sdf.parse(GlobalDate+" "+GlobalTime);
        System.out.println(date.toString()+"日期格式是");

        long timeInMillisSinceEpoch = date.getTime();
        long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
        System.out.println(timeInMillisSinceEpoch+"时间为");
        return  timeInMillisSinceEpoch;

    }
    public long returnDate(String GlobalDate) throws ParseException {


        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse(GlobalDate);
        System.out.println(date.toString()+"日期格式是");

        long timeInMillisSinceEpoch = date.getTime();
        long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
        System.out.println(timeInMillisSinceEpoch+"时间为");
        return  timeInMillisSinceEpoch;

    }
    public String returnChinas(String GlobalDate)
    {Map<String,String>chineseMap=new HashMap<String,String>();
        chineseMap.put("一月","01");
        chineseMap.put("二月","02");
        chineseMap.put("三月","03");
        chineseMap.put("四月","04");
        chineseMap.put("五月","05");
        chineseMap.put("六月","06");
        chineseMap.put("七月","07");
        chineseMap.put("八月","08");
        chineseMap.put("九月","09");
        chineseMap.put("十月","10");
        chineseMap.put("十一月","11");
        chineseMap.put("十二月","12");

        String[] strArray=GlobalDate.split("-");
        return strArray[2]+"/"+chineseMap.get(strArray[1])+"/"+strArray[0];

    }
    public String returnCompany(String site){
        String[] strArray=site.split("#");
        return strArray[0];
    }
    public String returnStrtrans(String text) {
        return text.replaceAll("\n", "\\\\n");


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
        else if (time.matches("\\d{1,2}(\\-)[\\u4E00-\\u9FA5]{2,3}\\1\\d{4}")) // dd-MM(中文)-YYYY
        {
            time = returnChinas(time);
            return time;
        }

        return null;
    }
    public String returnIsshowDateTime(String fomatGlobaldate,String Globaltime,String fonts) throws ParseException {   //api时间json
        if(fomatGlobaldate==null) {
            return "        \"" + fonts + "\": null ,\n";

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
    public String returnIsNull(String ns){                  //排除为空错误
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
