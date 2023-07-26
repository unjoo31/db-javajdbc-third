package dao;

import dto.BlogDto;

public interface BlogDao {
    // 데이터 저장
    public void save(BlogDto dto);
    // 데이터 찾기
    public void findAll();
    // 키워드로 데이터 찾기
    public void find(String keyword);
    // 데이터 수정
    public void update();
    // 데이터 삭제
    public void delete();
}
