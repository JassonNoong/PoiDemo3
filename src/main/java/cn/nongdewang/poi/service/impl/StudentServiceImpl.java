package cn.nongdewang.poi.service.impl;

import cn.nongdewang.poi.commonConst.CommonConst;
import cn.nongdewang.poi.dao.StudentMapper2;
import cn.nongdewang.poi.entity.Student;
import cn.nongdewang.poi.service.StudentService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nongdewang on 2017-12-09.
 */

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentMapper2 studentMapper;

    @Resource
    private HttpServletResponse response;

    @Override
    public void save(Student student) {
        studentMapper.insertSelective(student);
    }

    @Override
    public List<Student> findAll() {
        List<Student> all = studentMapper.findAll();
        return all;
    }



    @Override
    public void toExcelFile(String title) {
        //获得所有学生信息
        List<Student>  studentList=studentMapper.findAll();
        String[] clumnNames= CommonConst.STUDENT_SOURCE_DATA;
        //将数据库数据转化为行形式
        if(studentList==null){
            System.out.println("用户信息获取失败!");
        }
        List<Object[]> dataList=export2List(studentList,clumnNames);
        try {
            //创建工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet(title);

            //产生表格数据列名
            HSSFRow rowRowName = sheet.createRow(0);
            //将列头设置在sheet的单元格中
            int columnNum = clumnNames.length;
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellColumnName = rowRowName.createCell(n);
                cellColumnName.setCellValue(new HSSFRichTextString(clumnNames[n]));
            }

            //装查询出的数据设置到sheet对应的单元格中
            for (int i =0;i<66350;i++) {
                //遍历每个对象
                if(dataList!=null){
                    Object[] obj = dataList.get(i);
                    //创建所需的行数
                    HSSFRow newRow = sheet.createRow( i);
                    for (int j = 0; j <obj.length; j++) {
                        //设置单元格的数据类型
                        HSSFCell cell = null;
                        cell = newRow.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!StringUtils.isEmpty(obj[j])) {
                            cell.setCellValue(obj[j].toString());
                        }
                    }
                }
            }

            if (!StringUtils.isEmpty(workbook)) {
                try {
                    String fileName = "excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
                    String headStr = "attachment; filename=\"" + fileName + "\"";
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.setHeader("Content-Disposition", headStr);
                    ServletOutputStream outputStream = response.getOutputStream();
                    workbook.write(outputStream);
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //讲所有学生的列表转换成对应的excel表格的列的信息
    private List<Object[]> export2List(List<Student> studentList,String[] columns) {
        List<String[]> list=new ArrayList<>();
        for(Student s :studentList){
            String[] strArr=new String[columns.length];
            // "编号", "姓名", "年龄", "语文", "英语", "数学", "物理"
            strArr[0]=String.valueOf(s.getId());
            strArr[1]=s.getName();
            strArr[2]=String.valueOf(s.getAge());
            strArr[3]=String.valueOf(s.getChinese());
            strArr[4]=String.valueOf(s.getEnglish());
            strArr[5]=String.valueOf(s.getMath());
            strArr[6]=String.valueOf(s.getPhysics());
            list.add(strArr);
        }


    return null;
    }
}