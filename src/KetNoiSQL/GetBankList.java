///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package KetNoiSQL;
//
///**
// *
// * @author SingPC
// */
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//
//import java.io.File;
//import java.nio.file.Path;
//
//// Java version "1.8.0_201"
//import org.apache.http.NameValuePair; // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json
//import org.json.JSONArray;
//import vn.zalopay.crypto.HMACUtil;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class GetBankList {
//
//    private static Map<String, String> config = new HashMap<String, String>(){{
//        put("appid", "553");
//        put("key1", "9phuAOYhan4urywHTh0ndEXiV3pKHr5Q");
//        put("key2", "Iyz2habzyr7AG8SgvoBCbKwKi3UzlLi3");
//        put("endpoint", "https://sbgateway.zalopay.vn/api/getlistmerchantbanks");
//    }};
//
//    public static void main(String[] args) throws Exception {
//        String appid = config.get("appid");
//        String reqtime = Long.toString(System.currentTimeMillis());
//        String data = appid +"|"+ reqtime;
//        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data);
//
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("appid", appid));
//        params.add(new BasicNameValuePair("reqtime", reqtime)); // miliseconds
//        params.add(new BasicNameValuePair("mac", mac));
//
//        URIBuilder uri = new URIBuilder(config.get("endpoint"));
//        uri.addParameters(params);
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet get = new HttpGet(uri.build());
//
//        CloseableHttpResponse res = client.execute(get);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
//        StringBuilder resultJsonStr = new StringBuilder();
//        String line;
//
//        while ((line = rd.readLine()) != null) {
//            resultJsonStr.append(line);
//        }
//
//        JSONObject result = new JSONObject(resultJsonStr.toString());
//        JSONObject banksObject = result.getJSONObject("banks");
//
//        System.out.format("returncode = %s", result.getInt("returncode"));
//        System.out.format("returnmessage = %s", result.getString("returnmessage"));
//
//        for(String pmcid : banksObject.keySet()) {
//            JSONArray banks = banksObject.getJSONArray(pmcid);
//            banks.forEach(bank -> {
//                System.out.format("%s. %s\n", pmcid, bank.toString());
//            });
//        }
//    }
//}