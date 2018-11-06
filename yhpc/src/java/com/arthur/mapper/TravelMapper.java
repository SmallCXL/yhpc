package com.arthur.mapper;

import com.arthur.pojo.Travel;
import com.arthur.pojo.TravelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelMapper {
    int insert(Travel record);

    int insertSelective(Travel record);

    List<Travel> selectByExample(TravelExample example);

    List<Travel> queryWithKey(@Param("departure_") String departure, @Param("arrival_") String arrival,
                                  @Param("offset") int offset, @Param("length") int length, @Param("type_") String type);

    int deleteById(long id);

    int countResult(@Param("departure_") String departure, @Param("arrival_") String arrival, @Param("type_")String type);

    void doQuartzMission(@Param("today")String today);

}