package service;

import model.dao.DibDAO;
import model.vo.DibVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DibService {
    @Autowired
    private DibDAO dao;

    public boolean Check(String nickname,String ImgUrl){
        System.out.println(nickname);
        List<DibVO> list = dao.searchOne(nickname,ImgUrl);
        System.out.println(list);
        if(list.size() != 0)
            return false;
        else
            return true;
    }
}
