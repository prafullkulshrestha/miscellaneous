package com.msa.mdm.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.msa.mdm.entity.ItemData;
@Repository
public interface ProductDataDao  extends ElasticsearchRepository < ItemData, Long > {
    public List< ItemData >  findByItemDescription(String name);


}
