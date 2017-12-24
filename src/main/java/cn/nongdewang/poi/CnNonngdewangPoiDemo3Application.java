package cn.nongdewang.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("cn.nongdewang.**.dao")
public class CnNonngdewangPoiDemo3Application {

	public static void main(String[] args) {
		SpringApplication.run(CnNonngdewangPoiDemo3Application.class, args);
	}



}
