package com.aurum.bitcoin;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aurum.bitcoin.controller.BitcoinController;
import com.aurum.bitcoin.domain.Bitcoin;
import com.aurum.bitcoin.domain.SummaryMovement;

@RunWith(SpringRunner.class)
@WebMvcTest(BitcoinApplicationTests.class)
public class BitcoinApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BitcoinController bitcoinController;

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getSummaryMovement() throws Exception {
		SummaryMovement summaryMovement = new SummaryMovement();

		given(bitcoinController.summary()).willReturn(summaryMovement);

		mvc.perform(get("http://localhost:8080/api/bitcoins/summary/")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getQuoteById() throws Exception {
		Bitcoin bitcoin = new Bitcoin();
		int tid = 739725;

		given(bitcoinController.getTradeById(tid)).willReturn(bitcoin);

		mvc.perform(get("http://localhost:8080/api/bitcoins/trade/" + tid)
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
