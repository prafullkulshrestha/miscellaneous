package com.msa.mdm.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.msa.mdm.entity.ProductData;
@Repository
public interface ProductDataDao  extends ElasticsearchRepository < ProductData, Long > {
    public List< ProductData >  findByItemDescription(String name);


}
