package model.dao;


import model.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Repository
public class CommentDAO {

    @Autowired
    SqlSession session = null;

    public List<CommentVO> searchM(String movieNm){
        String statement = "resource.CommentMapper.selectComment";
        List<CommentVO> list = session.selectList(statement, movieNm);
        System.out.println("comment-list:" + list);
        return list;
    }

    public boolean insertM(CommentVO vo) {
        boolean result = true;

        String statement = "resource.CommentMapper.insertComment";
        if (session.insert(statement, vo) !=1)
            result = false;
        return result;
    }

    public boolean updateM(CommentVO vo){

        boolean result = true;
        String statement = "resource.CommentMapper.updateComment";
        if(session.update(statement, vo) !=1)
            result = false;
        return result;
    }

    public boolean deleteM(int cnt){
        System.out.println("cnt:"+ cnt);
        boolean result = true;
        String statement = "resource.CommentMapper.deleteComment";
        if(session.delete(statement, cnt)!=1)
            result = false;
        return result;
    }

    public boolean likeM(CommentVO vo){
        boolean result = true;
        String statement = "resource.CommentMapper.like";
        if(session.update(statement, vo)!=1)
            result = false;
        return result;
    }


}
