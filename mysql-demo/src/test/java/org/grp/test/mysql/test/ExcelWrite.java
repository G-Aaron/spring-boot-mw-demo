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
    public class ExcelWrite  {
    private static HSSFWorkbook workbook = null;

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