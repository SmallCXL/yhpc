package com.arthur.dao;

import com.arthur.mapper.TravelMapper;
import com.arthur.pojo.Travel;
import com.arthur.pojo.TravelExample;
import com.arthur.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
@Repository
public class TravelDAO {
    private TravelMapper travelMapper;

    @Autowired
    public TravelDAO(TravelMapper travelMapper) {
        this.travelMapper = travelMapper;
    }

    //增加数据操作，每次都要执行方法体，同时缓存结果
    @CachePut(value = "webCache", key = "'user:'+#user.phone_+'_travelList'")
    public List<Travel> insert(User user, Travel record) {
        List<Travel> list = user.getTravelList();
        travelMapper.insert(record);
        list.add(record);
        return list;
    }

    //暂未使用
    public int insertSelective(Travel record) {
        return travelMapper.insertSelective(record);
    }

    //读取操作，不需要每次执行方法体，只需读取缓存即可
    //cache   user:123456_travelList
    @Cacheable(value = "webCache", key = "'user:'+#user.phone_+'_travelList'", condition = "#user != null")
    public List<Travel> selectTravelListOfUser(User user) {
        TravelExample example = new TravelExample();
        example.createCriteria().andUidEqualTo(user.getId());
        System.out.println("selecting travel info from database...");
        return travelMapper.selectByExample(example);
    }

    //搜索功能，需要每次都执行数据库搜索，不使用缓存数据
    public List<Travel> queryWithKey(String departure, String arrival, int offset, int length, String type) {
        return travelMapper.queryWithKey(departure, arrival, offset, length, type);
    }

    //删除操作，每次都要执行方法体，同时缓存结果
    @CachePut(value = "webCache", key = "'user:'+#user.phone_+'_travelList'")
    public List<Travel> deleteById(User user, long id) {
        List<Travel> list = user.getTravelList();
        travelMapper.deleteById(id);
        for (Travel t : list) {
            if (t.getId().equals(id)) {
                list.remove(t);
                break;
            }
        }
        return list;
    }

    //搜索结果行数统计，需要每次都实行数据库搜索，不使用缓存数据
    public int countResult(String departure, String arrival, String type) {
        return travelMapper.countResult(departure, arrival, type);
    }
}
