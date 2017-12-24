package cn.nongdewang.poi.action;

import cn.nongdewang.poi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nongdewang on 2017-12-10.
 */
@RestController
public class Write2ExcelAction {
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	Object index() {
		return "我的poidemo主页";
	}

	@GetMapping("getScoreByExcel")
	public void getExcelFile() {
		String title = "student_score";
		studentService.toExcelFile(title);
	}

}
