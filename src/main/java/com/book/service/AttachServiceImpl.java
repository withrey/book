package com.book.service;

import com.book.dao.AttachDao;
import com.book.model.AttachImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService{

    @Autowired
    private AttachDao attachDao;

    // 이미지 데이터 반환
    @Override
    public List<AttachImageVO> getAttachList(int bookId) {

        return attachDao.getAttachList(bookId);

    }


}
