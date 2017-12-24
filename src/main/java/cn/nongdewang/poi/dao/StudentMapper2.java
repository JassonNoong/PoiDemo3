package cn.nongdewang.poi.dao;

import cn.nongdewang.poi.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentMapper2 {

    public abstract int deleteByPrimaryKey(Integer id);

    public abstract int insert(Student record);

    public abstract int insertSelective(Student record);

    public abstract Student selectByPrimaryKey(Integer id);

    public abstract int updateByPrimaryKeySelective(Student record);

    public abstract int updateByPrimaryKey(Student record);

    public abstract List<Student> findAll();
}
