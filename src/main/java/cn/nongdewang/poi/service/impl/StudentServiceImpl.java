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
        List<Student>  studentList=studentMapper.findAll();
        String[] clumnNames= CommonConst.STUDENT_SOURCE_DATA;
        List<Object[]> dataList=export2List(studentList,clumnNames.length);
        try {
            //创建工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet(title);

            //产生表格标题行
            HSSFRow firstRow = sheet.createRow(0);
            HSSFCell cellTitle = firstRow.createCell(0);
            //在表格第一行填入标题
            cellTitle.setCellValue(title);

            //定义所需要的列数
            int columnNum = clumnNames.length;
            HSSFRow rowRowName = sheet.createRow(1);

            //将列头设置在sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellColumnName = rowRowName.createCell(n);
                HSSFRichTextString text = new HSSFRichTextString(clumnNames[n]);
                cellColumnName.setCellValue(text);
            }

            //装查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                //遍历每个对象
                Object[] obj = dataList.get(i);
                //创建所需的行数
                HSSFRow newRow = sheet.createRow(i + 1);
                for (int j = 0; j < obj.length; j++) {
                    //设置单元格的数据类型
                    HSSFCell cell = null;
                    cell = newRow.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!StringUtils.isEmpty(obj[j])) {
                        cell.setCellValue(obj[j].toString());
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
    private List<Object[]> export2List(List<Student> studentList,int columns) {
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            for (int j=0;j<columns;j++){
                Object[] objArr=new Object[columns];
                objArr[0]=student.getId();
                objArr[1]=student.getName();
                objArr[2]=student.getAge();
                objArr[3]=student.getGender();
                objArr[4]=student.getChinese();
                objArr[5]=student.getEnglish();
                objArr[6]=student.getMath();
                objArr[7]=student.getPhysics();
                list.add(objArr);
            }
        }

        return list;

    }
}