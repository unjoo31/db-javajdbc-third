package util;

import dto.BlogDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class UtilJson {
    public static JSONObject createJsonData() {
        /**
         * 모든 데이터를 담을 JSONObject 생성
         */
        JSONObject productInfo = new JSONObject();

        /**
         * value로 단순 값(string, int)를 가지는 프로퍼티 처리.
         * product_no
         * product_name
         */
        productInfo.put("product_no", 1022);
        productInfo.put("product_name", "Shirt");

        /**
         * value로 Json을 가지는 프로퍼티 처리.
         * extra_product_info JSONObject 생성
         */
        JSONObject extraProductInfo = new JSONObject();
        extraProductInfo.put("ship_fee", 3000);
        extraProductInfo.put("discount_rate", 11);
        productInfo.put("extra_product_info", extraProductInfo);

        /**
         * value로 Array를 가지는 프로퍼티 처리.
         * item_list JSONArray 생성
         */
        JSONArray itemList = new JSONArray();

        /**
         * value로 Json을 가지는 프로퍼티 처리.
         * item_list.* JSONObject 생
         */
        JSONObject item1 = new JSONObject();
        item1.put("item_no", 21);
        item1.put("item_name", "L/Blue");
        itemList.add(item1);
        JSONObject item2 = new JSONObject();
        item2.put("item_no", 126);
        item2.put("item_name", "S/Red");
        itemList.add(item2);
        JSONObject item3 = new JSONObject();
        item3.put("item_no", 1052);
        item3.put("item_name", "XL/Green");
        itemList.add(item3);
        productInfo.put("item_list", itemList);

        return productInfo;
    }

    public static String jsonToString(JSONObject jsonObject)
    {
        return jsonObject.toString();
    }

    public static void parseJsonData(JSONObject jsonObject) {
        /**
         * String 형태로 만들어져있는 JSON 데이터를 파싱해줄 객체 생성.
         */
        JSONParser jsonParser = new JSONParser();

        /**
         * JSON 데이터가 String 형태로 들어왔음을 가정하기 위해
         * Json 데이터를 생성하고 String 형태로 바꿔주었다.
         */
        String sProductInfo = UtilJson.jsonToString(jsonObject);

        try {
            JSONObject productInfo = (JSONObject) jsonParser.parse(sProductInfo);

            /**
             * value로 단순 값(string, int)를 가지는 프로퍼티 조회.
             * product_no, product_name
             */
            System.out.println("product_no : " + productInfo.get("product_no"));
            System.out.println("product_name : " + productInfo.get("product_name"));

            /**
             * value로 Json을 가지는 프로퍼티 조회.
             * extra_product_info를 꺼낼 때, JSONObject로 캐스팅.
             */
            JSONObject extraProductInfo = (JSONObject) productInfo.get("extra_product_info");
            System.out.println("ship_fee : " + extraProductInfo.get("ship_fee"));
            System.out.println("discount_rate : " + extraProductInfo.get("discount_rate"));

            /**
             * value로 Array를 가지는 프로퍼티 조회.
             * item_list를 꺼낼 때, JSONArray로 캐스팅.
             */
            JSONArray itemList = (JSONArray) productInfo.get("item_list");
            for (Object itemInfo : itemList) {
                System.out.println("item_no : " + ((JSONObject) itemInfo).get("item_no"));
                System.out.println("item_name : " + ((JSONObject) itemInfo).get("item_name"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<BlogDto> parseJsonData(String keyword, String jsonString) {
        // dto를 담기 위한 그릇
        List<BlogDto> list = new ArrayList<>();

        /**
         * String 형태로 만들어져있는 JSON 데이터를 파싱해줄 객체 생성.
         */
        JSONParser jsonParser = new JSONParser();

        /**
         * JSON 데이터가 String 형태로 들어왔음을 가정하기 위해
         * Json 데이터를 생성하고 String 형태로 바꿔주었다.
         */
        //String sProductInfo = UtilJson.jsonToString(jsonObject);

        try {
            JSONObject productInfo = (JSONObject) jsonParser.parse(jsonString);

            /**
             * value로 단순 값(string, int)를 가지는 프로퍼티 조회.
             * product_no, product_name
             */
            //System.out.println("product_no : " + productInfo.get("product_no"));
            //System.out.println("product_name : " + productInfo.get("product_name"));

            /**
             * value로 Json을 가지는 프로퍼티 조회.
             * extra_product_info를 꺼낼 때, JSONObject로 캐스팅.
             */
            //JSONObject extraProductInfo = (JSONObject) productInfo.get("extra_product_info");
            //System.out.println("ship_fee : " + extraProductInfo.get("ship_fee"));
            //System.out.println("discount_rate : " + extraProductInfo.get("discount_rate"));

            /**
             * value로 Array를 가지는 프로퍼티 조회.
             * item_list를 꺼낼 때, JSONArray로 캐스팅.
             */
            JSONArray itemList = (JSONArray) productInfo.get("items");
            for (Object itemInfo : itemList) {
                // new를 for문 내부에 만들어야 데이터가 별도로 계속 들어갈 수 있다. 계속 new가 되어야 공간이 계속 생긴다.
                BlogDto dto = new BlogDto();
                dto.setKeyword(keyword);
                dto.setTitle(((JSONObject) itemInfo).get("title").toString());
                dto.setLink(((JSONObject) itemInfo).get("link").toString());
                dto.setDescription(((JSONObject) itemInfo).get("description").toString());
                dto.setBloggername(((JSONObject) itemInfo).get("bloggername").toString());
                dto.setBloggerlink(((JSONObject) itemInfo).get("bloggerlink").toString());
                dto.setPostdate(((JSONObject) itemInfo).get("postdate").toString());
                list.add(dto);
                //System.out.println("title : " + ((JSONObject) itemInfo).get("title"));
                //System.out.println("link : " + ((JSONObject) itemInfo).get("link"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
