package com.aurum.bitcoin.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.aurum.bitcoin.domain.Bitcoin;
import com.aurum.bitcoin.domain.SummaryMovement;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BitcoinRepositoryImpl implements BitcoinRepository {

	@Value("${bitcoin.URL}")
	private String URL;

	/**
	 * 
	 */
	@Override
	public List<Bitcoin> getTrades() {
		ObjectMapper mapper = new ObjectMapper();
		String json = jsonGetRequest(this.URL);
		List<Bitcoin> listBitcoins = new ArrayList<>();
		try {
			listBitcoins = mapper.readValue(json, new TypeReference<List<Bitcoin>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listBitcoins;
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	private static String streamToString(InputStream inputStream) {
		@SuppressWarnings("resource")
		String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
		return text;
	}

	/**
	 * 
	 * @param urlQueryString
	 * @return
	 */
	public static String jsonGetRequest(String urlQueryString) {
		String json = null;
		try {
			URL url = new URL(urlQueryString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			json = streamToString(inStream); 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return json;
	}

}
