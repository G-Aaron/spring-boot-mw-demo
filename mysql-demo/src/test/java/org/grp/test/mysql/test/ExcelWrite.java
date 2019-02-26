package org.grp.test.mysql.test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.grp.test.mysql.domain.HouseEvaluateInfo;
import org.grp.test.mysql.pojo.HouseInfo;
import org.grp.test.mysql.pojo.HousePrice;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 2003版，最大行数是65536行
 * 2007以上版本，最大行数是1048576行
 * 超过大小 记得新建sheet
 */
public class ExcelWrite {
    private static HSSFWorkbook workbook = null;

    /**
     * 判断文件是否存在.
     * @param fileDir  文件路径
     * @return
     */
    public static boolean fileExist(String fileDir){
        File file = new File(fileDir);
        return file.exists();
    }
    /**
     * 判断文件的sheet是否存在.
     * @param fileDir   文件路径
     * @param sheetName  表格索引名
     * @return
     */
    public static boolean sheetExist(String fileDir,String sheetName) throws Exception{
        boolean flag = false;
        File file = new File(fileDir);
        if(file.exists()){    //文件存在
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if(sheet!=null)
                    flag = true;
            } catch (Exception e) {
                throw e;
            }

        }else{    //文件不存在
            flag = false;
        }
        return flag;
    }
    /**
     * 创建新excel.
     * @param fileDir  excel的路径
     * @param sheetName 要创建的表格索引
     * @param titleRow excel的第一行即表格头
     */
    public static void createExcel(String fileDir,String sheetName,String titleRow[]) throws Exception{
        //创建workbook
        workbook = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFSheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头
            HSSFRow row = workbook.getSheet(sheetName).createRow(0);    //创建第一行
            for(short i = 0;i < titleRow.length;i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 删除文件.
     * @param fileDir  文件路径
     */
    public static boolean deleteExcel(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
    /**
     * 往excel中写入(已存在的数据无法写入).
     * @param fileDir    文件路径
     * @param sheetName  表格索引
     * @throws Exception
     */
    public static void writeToExcel(String fileDir,String sheetName,List<HouseEvaluateInfo> list) throws Exception{
        write(fileDir, sheetName, list);
    }

    private static void write(String fileDir, String sheetName, List<HouseEvaluateInfo> list) throws IOException {
        //创建workbook
        createWb(fileDir);
        //流
        out(fileDir, sheetName, list);
    }

    private static void out(String fileDir, String sheetName, List<HouseEvaluateInfo> list) throws IOException {
        FileOutputStream out = null;
        HSSFSheet sheet = workbook.getSheet(sheetName);
        // 获取表头的列数
        try {
            if(!CollectionUtils.isEmpty(list)){
                for(int rowId=0;rowId<list.size();rowId++){
                    insertRow(list, sheet, rowId);
                }
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertRow(List<HouseEvaluateInfo> list, HSSFSheet sheet, int rowId) {
        HouseEvaluateInfo detail = list.get(rowId);
        String content = detail.getResult();
        if(StringUtils.isNotBlank(content)){
            String info = detail.getPawnInfo();
            HouseInfo houseInfo = JSONObject.parseObject(info, HouseInfo.class);
            List<HousePrice> housePrices = JSONObject.parseObject(content, new TypeReference<List<HousePrice>>(){});
            HSSFRow newRow=sheet.createRow(rowId+1);
            HSSFCell cell = newRow.createCell(0);
            cell.setCellValue(houseInfo.getAddress());
            HSSFCell cell10 = newRow.createCell(10);
            cell10.setCellValue(houseInfo.getCommunity_name());
            HSSFCell cell11 = newRow.createCell(11);
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(strDateFormat);
            cell11.setCellValue(format.format(detail.getCreateTime()));
            if(!CollectionUtils.isEmpty(housePrices)){
                setCellInfo(housePrices, newRow);
            }
        }
    }

    private static void setCellInfo(List<HousePrice> housePrices, HSSFRow newRow) {
        for(HousePrice price: housePrices){
            if(StringUtils.equals("lianJia", price.getSource())){
                HSSFCell cell1 = newRow.createCell(1);
                cell1.setCellValue(price.getSource());
                HSSFCell cell2 = newRow.createCell(2);
                cell2.setCellValue(price.getCommunityName());
                HSSFCell cell3 = newRow.createCell(3);
                cell3.setCellValue(price.getPrice());
            }
            if(StringUtils.equals("yunFang", price.getSource())){
                HSSFCell cell4 = newRow.createCell(4);
                cell4.setCellValue(price.getSource());
                HSSFCell cell5 = newRow.createCell(5);
                cell5.setCellValue(price.getCommunityName());
                HSSFCell cell6 = newRow.createCell(6);
                cell6.setCellValue(price.getPrice());
            }
            if(StringUtils.equals("guoXinDa", price.getSource())){
                HSSFCell cell7 = newRow.createCell(7);
                cell7.setCellValue(price.getSource());
                HSSFCell cell8 = newRow.createCell(8);
                cell8.setCellValue(price.getCommunityName());
                HSSFCell cell9 = newRow.createCell(9);
                cell9.setCellValue(price.getPrice());
            }
        }
    }

    private static void createWb(String fileDir) {
        File file = new File(fileDir);
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}