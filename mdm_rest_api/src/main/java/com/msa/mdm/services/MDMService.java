package com.msa.mdm.services;

import java.util.List;

import com.msa.mdm.entity.ItemData;

public interface MDMService {

	List < ItemData >  getByItemDescription(String description);
}
