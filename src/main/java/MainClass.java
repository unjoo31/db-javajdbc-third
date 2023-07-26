import dao.BlogDao;
import dao.BlogDaoImpl;
import db.DBConnection;
import dto.BlogDto;
import org.json.simple.JSONObject;
import util.NaverAPI;
import util.UtilJson;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("네이버 검색 프로그램");
        // 사용자가 검색어를 입력하면
        Scanner sc = new Scanner(System.in);
        System.out.println("검색어를 입력하세요");
        String keyword = sc.next();
        // 네이버 블로그 검색 내용이 출력
        System.out.println(keyword + "검색내용");
        String jsonData = NaverAPI.blog(keyword);

//        JSONObject jsonObject = UtilJson.createJsonData();
//        System.out.println(jsonObject);

        // json 파싱을 해서 화면 출력
        List<BlogDto> list = UtilJson.parseJsonData(keyword, jsonData);

        // 디비연결
        Connection connection = DBConnection.getInstance();
        BlogDao blogDao = new BlogDaoImpl(connection);

        // 출력하는 내용을 db에 저장(검색어 포함)
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getKeyword());
//            System.out.println(list.get(i).getTitle());
//            System.out.println(list.get(i).getDescription());
//            System.out.println(list.get(i).getLink());
//            System.out.println(list.get(i).getBloggername());
//            System.out.println(list.get(i).getBloggerlink());
//            System.out.println(list.get(i).getPostdate());
//            blogDao.save(list.get(i));
//        }

        // 데이터를 저장
        // blogDao.save();

        // 전체 데이터 출력
        //blogDao.findAll();

        // 키워드 입력받아서 데이터 출력
        System.out.println("찾고 싶은 키워드 입력 : ");
        keyword = sc.next();
        blogDao.find(keyword);

        // 키워드가 데이터 베이스에 있는지 없는지에 따라 디비에 있는 데이터 출력 또는 네이버 api 연결해서
        // 디비 저장하고 데이터 보여주기
    }
}