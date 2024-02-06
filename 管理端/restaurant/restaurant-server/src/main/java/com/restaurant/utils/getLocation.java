package com.restaurant.utils;

import com.restaurant.entity.Location;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class getLocation {

    private static final String BAIDU_MAP_AK = "U4Bn9fW4tEtgEOtQ29cTpIBm47Ey4LCX";

    public static String resolveLocation(Location location) {

        String locationInfo = resolveLocation(location.getLat(), location.getLng());
        return locationInfo;
    }

    public static String resolveLocation(String latitude, String longitude) {
        String apiUrl = "http://api.map.baidu.com/reverse_geocoding/v3?" +
                "ak=" + BAIDU_MAP_AK +
                "&output=json" +
                "&coordtype=wgs84ll" +
                "&location=" + latitude + "," + longitude;

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(apiUrl);

            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                // 解析 responseBody 获取地址信息
                String address = parseAddressFromJson(responseBody);
                return address;
            } else {
                System.out.println("请求失败，HTTP 状态码：" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 解析 JSON 获取地址信息
    private static String parseAddressFromJson(String responseBody) {
        try {
            // 使用 org.json 库进行 JSON 解析
            org.json.JSONObject jsonObject = new org.json.JSONObject(StringEscapeUtils.unescapeJson(responseBody));

            if (jsonObject.has("status") && jsonObject.getInt("status") == 0) {
                org.json.JSONObject addressComponent = jsonObject.getJSONObject("result").getJSONObject("addressComponent");

                String province = addressComponent.getString("province");
                String city = addressComponent.getString("city");
                String district = addressComponent.getString("district");

                return province + " " + city + " " + district;
            }
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}