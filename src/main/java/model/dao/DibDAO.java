package model.dao;


import model.vo.DibVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class DibDAO {

    @Autowired
    SqlSession session = null;

    public boolean insertM(DibVO vo) {
        boolean result = true;

        String statment = "resource.DibMapper.insertDib";
        if (session.insert(statment, vo) != 1) {
            result = false;
        }
        return result;
    }



}
