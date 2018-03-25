package test;

import org.apache.ibatis.annotations.Param;
import test.UserThumb;
import test.UserThumbExample;

public interface UserThumbMapper {
    long countByExample(UserThumbExample example);

    int deleteByExample(UserThumbExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserThumb record);

    int insertSelective(UserThumb record);

    UserThumb selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserThumb record, @Param("example") UserThumbExample example);

    int updateByExample(@Param("record") UserThumb record, @Param("example") UserThumbExample example);

    int updateByPrimaryKeySelective(UserThumb record);

    int updateByPrimaryKey(UserThumb record);
}