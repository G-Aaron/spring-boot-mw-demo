package org.grp.test.mysql.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: gaorenpeng
 * @date: 2018-05-31 09:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousePrice {
    private String address;
    private String communityName;
    private String communityAlias;
    private String communityId;
    private String name;
    private String source;
    @Builder.Default()
    private Double price = 0d;
    @Builder.Default()
    private Double totalPrice = 0d;
    @Builder.Default()
    private Double score= 0d;

    public HousePrice(String name){
        this.name = name;
        this.price = 0d;
        this.totalPrice = 0d;
    }

    public HousePrice(Double price, Double totalPrice){
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
