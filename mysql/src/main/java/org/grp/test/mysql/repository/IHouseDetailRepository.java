package org.grp.test.mysql.repository;


import org.grp.test.mysql.domain.HouseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Title: IHouseDetailRepository</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-08-14 14:43
 **/
public interface IHouseDetailRepository extends JpaRepository<HouseDetail, Long> {

}
