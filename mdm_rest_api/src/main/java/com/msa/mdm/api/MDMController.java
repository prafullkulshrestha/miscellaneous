package com.msa.mdm.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msa.mdm.entity.ItemData;
import com.msa.mdm.services.MDMService;

@RestController
@RequestMapping(value="/mdc")
public class MDMController {

	@Autowired
	MDMService mDMService;
	
	@RequestMapping(method = RequestMethod.GET, value="/protected/items/{description}")
    public @ResponseBody List<ItemData> testUnprotected(@PathVariable  String description) throws JsonParseException, JsonMappingException, IOException {
		List<ItemData> data = mDMService.getByItemDescription(description);
        return data;
    }
}
