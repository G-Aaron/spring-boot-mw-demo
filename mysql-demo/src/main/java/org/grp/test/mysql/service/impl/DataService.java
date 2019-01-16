package org.grp.test.mysql.service.impl;

import org.grp.test.mysql.repository.IHouseDetailRepository;
import org.grp.test.mysql.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-08-13 14:33
 **/
@Service("dataService")
public class DataService implements IDataService {

    @Autowired
    IHouseDetailRepository houseDetailRepository;


}
