package cn.nongdewang.poi.util;


class PoUtil {

    public static void toXlsx(String title,Object[] objArr,String[] columnTitles ){
        if(objArr.length!=columnTitles.length){
            throw new RuntimeException("列标题个数和数据列数不同,请修改!");
        }

    }
}
