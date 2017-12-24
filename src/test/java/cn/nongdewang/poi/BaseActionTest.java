package cn.nongdewang.poi;

import cn.nongdewang.poi.action.Write2ExcelAction;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by nongdewang on 2017-12-11.
 */
@RunWith(JUnit38ClassRunner.class)
@SpringBootTest(classes = CnNonngdewangPoiDemo3Application.class)
@WebAppConfiguration
public class  BaseActionTest<T> {

    @Autowired
    private Write2ExcelAction write2ExcelAction;

    private MockMvc mockMvc;

    @org.junit.Before
    public void init(){
        mockMvc= MockMvcBuilders.standaloneSetup(write2ExcelAction).build();
    }

    @Test
    public void testExcelExport(){

    }
}
