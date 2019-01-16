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
 * @date: 2018-08-14 14:44
 **/
@Data
@Entity
@Table(name = "tbl_detail_pieces")
public class HouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 数据源
     */
    @Column(columnDefinition = "bigint(20) NOT NULL")
    private String pieceId;
    /**
     * 查询key 查询条件md5
     */
    @Column(columnDefinition = "longtext DEFAULT NULL")
    private String detail;
    /**
     * 输入
     */
    @Column(columnDefinition = "int(11) DEFAULT NULL")
    private int isDelete;

    /**
     * 结果
     */
    @Column(columnDefinition = "varchar(10) DEFAULT NULL COMMENT '产品代码'")
    private String productCode;


    /**
     * 接口代码
     */
    @Column(columnDefinition = "varchar(500) comment '地址'")
    private String address;

    /**
     * 接口代码
     */
    @Column(columnDefinition = "varchar(100) comment '小区名'")
    private String name;

    /**
     * 查询key 查询条件md5
     */
    @Column(columnDefinition = "text DEFAULT NULL comment '地址获取云房小区列表'")
    private String list;

    /**
     * 小区名获取云房小区列表
     */
    @Column(columnDefinition = "text DEFAULT NULL comment '小区名获取云房小区列表'")
    private String list2;

    /**
     * 查询key 查询条件md5
     */
    @Column(columnDefinition = "text DEFAULT NULL comment '地址评估结果'")
    private String result;

    /**
     * 查询key 查询条件md5
     */
    @Column(columnDefinition = "text DEFAULT NULL comment '小区ID评估结果'")
    private String resul1;
    /**
     * 创建时间
     */
    @Column(insertable = false, updatable = false,
            columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date gmtUpdate;

}
