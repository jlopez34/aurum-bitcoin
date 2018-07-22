package com.aurum.bitcoin.service;

import com.aurum.bitcoin.domain.Bitcoin;
import com.aurum.bitcoin.domain.SummaryMovement;

public interface BitcoinService {

	public SummaryMovement summary();

	public Bitcoin getQuoteById(int id);
}
