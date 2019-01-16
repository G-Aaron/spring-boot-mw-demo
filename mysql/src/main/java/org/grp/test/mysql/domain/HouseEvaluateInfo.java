package org.grp.test.mysql.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-11-27 16:26
 **/
@Entity
@Data
@Table(name = "tbl_eva_house_info")
public class HouseEvaluateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniqueId;

    /**
     * 申请号
     */
    @Lob
    @Column(columnDefinition = "varchar(36) comment '申请号'")
    private String applyId;

    /**
     * 抵押物信息
     */
    @Lob
    @Column(columnDefinition = "TEXT comment '抵押物信息'")
    private String pawnInfo;

    /**
     * 评估结果
     */
    @Lob
    @Column(columnDefinition = "TEXT comment '评估结果'")
    private String result;

    /**
     * 创建时间
     */
    @Column(insertable = false, updatable = false,
            columnDefinition = "timestamp default current_timestamp comment '创建时间'")
    private Date createTime;

    public static HouseEvaluateInfo build(String info, String result, String applyId){
        HouseEvaluateInfo houseEvaluateInfo = new HouseEvaluateInfo();
        houseEvaluateInfo.setPawnInfo(info);
        houseEvaluateInfo.setResult(result);
        houseEvaluateInfo.setApplyId(applyId);
        return houseEvaluateInfo;
    }
}
