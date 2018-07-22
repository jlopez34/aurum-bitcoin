package com.aurum.bitcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.bitcoin.domain.Bitcoin;
import com.aurum.bitcoin.domain.SummaryMovement;
import com.aurum.bitcoin.service.BitcoinService;

@RestController
@RequestMapping("/api/bitcoins")
public class BitcoinController {

	@Autowired
	private BitcoinService bitcoinService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/summary/" }, method = RequestMethod.GET)
	public SummaryMovement summary() {
		return this.bitcoinService.summary();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/trade/{id}", method = RequestMethod.GET)
	public Bitcoin getTradeById(@PathVariable int id) {
		return this.bitcoinService.getQuoteById(id);
	}

}
