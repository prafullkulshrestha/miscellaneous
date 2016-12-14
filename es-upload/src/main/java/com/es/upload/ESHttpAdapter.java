package com.es.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClients;
import org.apache.hc.client5.http.methods.CloseableHttpResponse;
import org.apache.hc.client5.http.methods.HttpPost;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.entity.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ESHttpAdapter {

	private String elasticSearchHostUrl = "http://localhost:9200";

	private String elasticSearchMasterIndex = "/matching-new/item-data/1?pretty";

	/*
	 * public String getMasterByUPC(String uPC) throws IOException{ String
	 * theResponse = "";
	 * 
	 * try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
	 * ObjectMapper mapper = new ObjectMapper(); String postBody =
	 * "{\"filter\":{\"term\":{\"upcCd\":\""+uPC+"\"}}}"; HttpPost httpPost =
	 * new
	 * HttpPost(elasticSearchHostUrl+"/item-master-no-nesting/item/_search");
	 * HttpEntity httpEntity = EntityBuilder.create().setText(postBody).build();
	 * httpPost.setEntity(httpEntity); try (CloseableHttpResponse response2 =
	 * httpclient.execute(httpPost)) {
	 * System.out.println(response2.getStatusLine()); HttpEntity entity2 =
	 * response2.getEntity(); InputStream inputStream = entity2.getContent();
	 * StringWriter writer = new StringWriter(); IOUtils.copy(inputStream,
	 * writer, "UTF-8"); theResponse = writer.toString();
	 * //System.out.println("********ES result******* " + theResponse);
	 * EntityUtils.consume(entity2); } } return theResponse; }
	 * 
	 * public String getDetailsByIUUID(String iUUID, String chunkSize) throws
	 * IOException {
	 * 
	 * String theResponse = "";
	 * 
	 * try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
	 * ObjectMapper mapper = new ObjectMapper(); String postBody =
	 * "{\"from\":0,\"size\":"+chunkSize+
	 * ",\"query\":{\"match_phrase\":{\"m.iUUID\":\""+iUUID+"\"}}}"; HttpPost
	 * httpPost = new
	 * HttpPost(elasticSearchHostUrl+"/items-prodreplica/items/_search");
	 * HttpEntity httpEntity = EntityBuilder.create().setText(postBody).build();
	 * httpPost.setEntity(httpEntity); try (CloseableHttpResponse response2 =
	 * httpclient.execute(httpPost)) {
	 * System.out.println(response2.getStatusLine()); HttpEntity entity2 =
	 * response2.getEntity(); InputStream inputStream = entity2.getContent();
	 * StringWriter writer = new StringWriter(); IOUtils.copy(inputStream,
	 * writer, "UTF-8"); theResponse = writer.toString();
	 * //System.out.println("********ES Details result******* " + theResponse);
	 * // do something useful with the response body // and ensure it is fully
	 * consumed EntityUtils.consume(entity2); } } return theResponse;
	 * 
	 * }
	 */

	public String getMasterByIUUID(String iUUID) throws IOException {

		String theResponse = "";

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			ObjectMapper mapper = new ObjectMapper();
			String postBody = "{\"filter\":{\"term\":{\"_id\":\"" + iUUID + "\"}}}";
			HttpPost httpPost = new HttpPost(elasticSearchHostUrl + elasticSearchMasterIndex);
			HttpEntity httpEntity = EntityBuilder.create().setText(postBody).build();
			httpPost.setEntity(httpEntity);
			try (CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
				HttpEntity entity2 = response2.getEntity();
				InputStream inputStream = entity2.getContent();
				StringWriter writer = new StringWriter();
				IOUtils.copy(inputStream, writer, "UTF-8");
				theResponse = writer.toString();
				System.out.println("********iUUID result******* " + theResponse);
				EntityUtils.consume(entity2);
			}
		}
		return theResponse;

	}

	public void createData() {
		
		Map<String, Object> postData = new HashMap<>();
		postData.put("id", 1);
		postData.put("itemDescription", "Acid Kuba Kuba Sumatra Robusto Infused 10 Pack");
		postData.put("manufacturer", "Thomson Cigar");
		postData.put("brandFamily", "ACID");
		postData.put("brand", "KUBA KUBA");
		postData.put("colorExtension", "Sumatra");
		postData.put("size", "Robusto");
		postData.put("flavor", "INFUSED");
		postData.put("quantity", 1);
		postData.put("url", "https://www.thompsoncigar.com/product/ACID-KUBA-KUBA-SUMATRA-ROBUSTO-INFUSED-10-PACK/90515.uts");
		postData.put("picture", getPictureData());
		ObjectMapper mapper = new ObjectMapper();
		String bodyString = null;
		try {
			bodyString = mapper.writeValueAsString(postData);
			System.out.println(mapper.writeValueAsString(postData));
			String theResponse = "";

			try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
				HttpPost httpPost = new HttpPost(elasticSearchHostUrl + elasticSearchMasterIndex);
				HttpEntity httpEntity = EntityBuilder.create().setText(bodyString).build();
				httpPost.setEntity(httpEntity);
				try (CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
					HttpEntity entity2 = response2.getEntity();
					InputStream inputStream = entity2.getContent();
					StringWriter writer = new StringWriter();
					IOUtils.copy(inputStream, writer, "UTF-8");
					theResponse = writer.toString();
					System.out.println("********ES call result******* " + theResponse);
					EntityUtils.consume(entity2);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Object getPictureData() {
		ByteArrayOutputStream ba= loadPdf("E:\\personal\\encore\\DL2.jpg");

		//Converting byte[] to base64 string 
		//NOTE: Always remember to encode your base 64 string in utf8 format other wise you may always get problems on browser.

		String pdfBase64String = 
		org.apache.commons.codec.binary.StringUtils.newStringUtf8(org.apache.
		commons.codec.binary.Base64.encodeBase64(ba.toByteArray()));
		System.out.println("Picture data " + pdfBase64String);
		return pdfBase64String;
	}
	private ByteArrayOutputStream loadPdf(String fileName)
	{
	File file = new File(fileName);
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ByteArrayOutputStream bos = new ByteArrayOutputStream();

	byte[] buf = new byte[1024];
	try {
	for (int readNum; (readNum = fis.read(buf)) != -1;) {
	bos.write(buf, 0, readNum); //no doubt here is 0
	}
	} catch (IOException ex) {
	ex.printStackTrace();
	}
	return bos;
	}
}
