package com.msa.mdm.services;

import java.util.List;

import com.msa.mdm.entity.ProductData;

public interface MDMService {

	List < ProductData >  getByItemDescription(String description);
}
