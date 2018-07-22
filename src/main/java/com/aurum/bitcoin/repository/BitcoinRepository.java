package com.aurum.bitcoin.repository;

import java.util.List;

import com.aurum.bitcoin.domain.Bitcoin;

public interface BitcoinRepository {

	public List<Bitcoin> getTrades();

}
