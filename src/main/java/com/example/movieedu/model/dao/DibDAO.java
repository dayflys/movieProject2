package com.example.movieedu.model.dao;

import com.example.movieedu.model.vo.CommentVO;
import com.example.movieedu.model.vo.DibVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Repository
public class DibDAO {

    @Autowired
    SqlSession session = null;

    public List<DibVO> searchM(String nickname){
        String statement = "resource.DibMapper.selectDib";
        List<DibVO> diblist = session.selectList(statement, nickname);
        System.out.println("Dib-list:" + diblist);
        return diblist;
    }
    public List<DibVO> searchOne(String nickname,String ImgUrl){
        String statement = "resource.DibMapper.selectOneDib";
        DibVO vo = new DibVO();
        vo.setImgUrl(ImgUrl);
        vo.setNickname(nickname);
        List<DibVO> diblist = session.selectList(statement, vo);
        System.out.println("Dib-list:" + diblist);
        return diblist;
    }
    public boolean insertM(DibVO vo) {
        boolean result = true;

        String statment = "resource.DibMapper.insertDib";
        if (session.insert(statment, vo) != 1) {
            result = false;
        }
        return result;
    }

    public boolean deleteM(int cnt) {
        boolean result = true;
        String statement = "resource.DibMapper.deleteDib";
        if (session.delete(statement, cnt) != 1) {
            result = false;
        }
        return result;
    }



}
