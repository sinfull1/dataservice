package com.abc.newsserversec.service.wechat.imp;

import com.abc.newsserversec.mapper.user.FeedbackInfoMapper;
import com.abc.newsserversec.mapper.wechat.WxspInfoMapper;
import com.abc.newsserversec.model.wechat.WxspUserInfo;
import com.abc.newsserversec.service.user.FeedbackInfoService;
import com.abc.newsserversec.service.wechat.WxspInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class WxspInfoServiceImp implements WxspInfoService {

    @Autowired
    private WxspInfoMapper wxspInfoMapper;

    @Override
    public ArrayList<WxspUserInfo> selectWxspUserByOpenid(String openid) {
        return wxspInfoMapper.selectWxspUserByOpenid(openid);
    }

    @Override
    public int insertWxspUserByMap(Map<String, String> map) {
        return wxspInfoMapper.insertWxspUserByMap(map);
    }

    @Override
    public int insertWxspUserSearchByMap(Map<String, Object> map) {
        return wxspInfoMapper.insertWxspUserSearchByMap(map);
    }

    @Override
    public int updateWxspUserByMap(Map<String, String> map) {
        return wxspInfoMapper.updateWxspUserByMap(map);
    }

    @Override
    public ArrayList<WxspUserInfo> selectAllUser() {
        return wxspInfoMapper.selectAllUser();
    }

    @Override
    public ArrayList<Map<String, Object>> selectSearchInfoByUserid(long userid) {
        return wxspInfoMapper.selectSearchInfoByUserid(userid);
    }

    @Override
    public int updateUserIds(Map<String, Object> map) {
        return wxspInfoMapper.updateUserIds(map);
    }
}
