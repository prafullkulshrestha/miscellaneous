package com.msa.mdm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.mdm.dao.ProductDataDao;
import com.msa.mdm.entity.ProductData;

@Service
public class MDMServiceImpl implements MDMService{
	
	@Autowired
	ProductDataDao productDataDao;
	
	public List < ProductData >  getByItemDescription(String description) {
		List<ProductData> data = productDataDao.findByItemDescription(description);
        return data;
    }
}
