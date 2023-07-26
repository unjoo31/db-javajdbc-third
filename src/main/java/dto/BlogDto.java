package dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BlogDto {
    // 변수
    private String keyword;
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;
}
