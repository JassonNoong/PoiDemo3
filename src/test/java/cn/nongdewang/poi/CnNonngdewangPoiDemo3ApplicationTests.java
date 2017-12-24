package cn.nongdewang.poi;

import cn.nongdewang.poi.entity.Student;
import cn.nongdewang.poi.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CnNonngdewangPoiDemo3ApplicationTests {
	@Autowired
	StudentService studentService;

	private Student setStudent(){
		double score=Math.random()*100;
		Student student =new Student();
		student.setName("mark"+score);
		student.setAge(20);
		student.setGender("1");
		student.setChinese(score);
		student.setMath(score);
		student.setPhysics(score);
		student.setEnglish(score);
		return student;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void studentServiceTest(){
		for(int i =0;i<1000;i++){
			Student aStudent = setStudent();
			studentService.save(aStudent);
		}
	}

	@Test
	public void testFindAll(){
		List<Student> all = studentService.findAll();
		for(Student s :all){
			//测试如何将获得的数据写入到xml文件中

			System.out.println(s);
		}
	}

	private void write2Excel(){



	}
}
