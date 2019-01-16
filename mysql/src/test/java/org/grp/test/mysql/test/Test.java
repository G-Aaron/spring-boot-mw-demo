package org.grp.test.mysql.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.grp.test.mysql.MysqlApplication;
import org.grp.test.mysql.domain.HouseDetail;
import org.grp.test.mysql.domain.HouseEvaluateInfo;
import org.grp.test.mysql.repository.IHouseDetailRepository;
import org.grp.test.mysql.service.IDataService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-08-13 14:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MysqlApplication.class)
@Slf4j
public class Test {
    @Autowired
    private IDataService dataService;

    @Autowired
    private IHouseDetailRepository houseDetailRepository;
@org.junit.Test
public void test(){
        List<HouseDetail> list = houseDetailRepository.findAll();
        for(HouseDetail detail: list){
            String content = detail.getDetail();
            if(StringUtils.isNotBlank(content)){
                try{
                    JSONObject json = JSONObject.parseObject(content);
                    JSONObject mo = json.getJSONArray("mortgages").getJSONObject(0);
                    String communityName = mo.getJSONObject("content").getString("communityName");
                    String pawnAddress = mo.getJSONObject("content").getString("pawnAddress");
                    detail.setAddress(pawnAddress);
                    detail.setName(communityName);
//                    houseDetailRepository.save(detail);
                    System.out.println("communityName:"+communityName);
                }catch (Exception e){
                    houseDetailRepository.delete(detail);
                    log.info("出错：{} {}",content, e.getMessage());
                }
            }
        }
    }

    @org.junit.Test
    public void test1() throws Exception {
        //创建文件
        ExcelWrite.fileExist("E:/小区估价统计.xls");
        String title[] = {"小区地址","数据源1","小区1","价格1","数据源2","小区2","价格2","数据源3","小区3","价格3","小区名称","时间"};
        ExcelWrite.createExcel("E:/小区估价统计.xls","sheet1",title);
        List<HouseEvaluateInfo> list = null;
        ExcelWrite.writeToExcel("E:/小区估价统计.xls","sheet1",list);
    }
}
