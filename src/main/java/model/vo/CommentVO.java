package model.vo;

import java.sql.Date;

public class CommentVO {
    private String nickname;
    private Date prehour;
    private String cont;
    private String moviename;
    private Integer like_no;
    private Integer cnt;
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Date getPrehour() {
        return prehour;
    }
    public void setPrehour(Date prehour) {
        this.prehour = prehour;
    }
    public String getContent() {
        return cont;
    }
    public void setContent(String cont) {
        this.cont = cont;
    }
    public String getMoviename() {
        return moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
    public Integer getLike() {
        return like_no;
    }
    public void setLike(Integer like_no) {
        this.like_no = like_no;
    }
    public Integer getCnt() {
        return cnt;
    }
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    
}
