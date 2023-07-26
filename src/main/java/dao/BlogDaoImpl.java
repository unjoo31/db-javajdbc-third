package dao;

import dto.BlogDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlogDaoImpl implements  BlogDao{
    // 변수
    private Connection connection;
    public BlogDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(BlogDto dto) {
        System.out.println("데이터 저장");
        String sql = "insert into blog(keyword, title, link, description, bloggername, bloggerlink, postdate) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, dto.getKeyword());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3, dto.getLink());
            pstmt.setString(4, dto.getDescription());
            pstmt.setString(5, dto.getBloggername());
            pstmt.setString(6, dto.getBloggerlink());
            pstmt.setString(7, dto.getPostdate());
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("데이터 삽입 성공!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }

    @Override
    public void findAll() {
        String sql = "select * from blog";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            //pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("title: " + rs.getString("title"));
                System.out.println("link: " + rs.getString("link"));
            }
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }

    @Override
    public void find(String keyword) {
        String sql = "select * from blog where keyword = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, keyword);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("title: " + rs.getString("title"));
                System.out.println("link: " + rs.getString("link"));
            }
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
