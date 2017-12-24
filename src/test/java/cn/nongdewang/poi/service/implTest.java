package cn.nongdewang.poi.service;

import cn.nongdewang.poi.CnNonngdewangPoiDemo3Application;
import cn.nongdewang.poi.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CnNonngdewangPoiDemo3Application.class)
@WebAppConfiguration
public class implTest  {
    @Autowired
    StudentService studentService;
    @Test
    public void test1(){
        studentService.toExcelFile("mystudent");
    }

    @Test
    public void  test2(){
        List<Student> studentList = studentService.findAll();
        for(Student e :studentList){
            System.out.println(e);
        }
    }

}
