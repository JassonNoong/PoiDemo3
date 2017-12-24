package cn.nongdewang.poi.service;

import cn.nongdewang.poi.entity.Student;

import java.util.List;

/**
 * Created by nongdewang on 2017-12-09.
 */
public interface StudentService {

    void save(Student student);
    List<Student> findAll();
    void toExcelFile(String title);
}
