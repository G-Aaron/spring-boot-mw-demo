package org.grp.test.mysql.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-11-27 13:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {
    private String apply_id;
    private String area;
    private String address;
    private String build_finish_year;
    private String community_name;
    private String community_id;
    private String city_code;
    private String city_name;
    private String floor;
    private String total_floor;
    private String house_type;
    private String hall_count;
    private String room_count;
    private String toilet_count;
    private String orientation;
    private String renovation;
    private String tags;
    private String district_code;
    private String building_type;
    private String d_welling_structure;
    private List<String> apis;
}
