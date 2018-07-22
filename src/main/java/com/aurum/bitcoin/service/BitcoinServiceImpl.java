package com.aurum.bitcoin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurum.bitcoin.domain.Bitcoin;
import com.aurum.bitcoin.domain.SummaryMovement;
import com.aurum.bitcoin.repository.BitcoinRepository;

@Service
public class BitcoinServiceImpl implements BitcoinService {

	@Autowired
	private BitcoinRepository bitcoinRepository;

	private static SummaryMovement summaryMovement = null;
	private final static String SELL = "sell";
	private final static String BUY = "buy";

	/**
	 * 
	 */
	@Override
	public SummaryMovement summary() {
		List<Bitcoin> listBitcoin = this.bitcoinRepository.getTrades();
		Collections.sort(listBitcoin, Collections.reverseOrder());

		return generateSummary(listBitcoin);
	}

	/**
	 * 
	 */
	@Override
	public Bitcoin getQuoteById(int id) {
		Bitcoin localBitcoin = null;
		boolean isFind = false;

		for (Bitcoin bitcoin : summaryMovement.getSales_higher()) {
			if (id == bitcoin.getTid()) {
				localBitcoin = bitcoin;
				isFind = true;
				break;
			}
		}

		if (!isFind) {
			for (Bitcoin bitcoin : summaryMovement.getBuyings_higher()) {
				if (id == bitcoin.getTid()) {
					localBitcoin = bitcoin;
					isFind = true;
					break;
				}
			}
		}

		return localBitcoin;
	}

	/**
	 * 
	 * @param listBitcoin
	 * @return
	 */
	public SummaryMovement generateSummary(List<Bitcoin> listBitcoin) {
		SummaryMovement summaryMovement = new SummaryMovement();

		List<Bitcoin> listSales = listBitcoin.stream().filter(bit -> SELL.equalsIgnoreCase(bit.getType()))
				.collect(Collectors.toList());

		List<Bitcoin> listBuyings = listBitcoin.stream().filter(bit -> BUY.equalsIgnoreCase(bit.getType()))
				.collect(Collectors.toList());

		summaryMovement.setSales_higher(getHigher(listSales));
		summaryMovement.setBuyings_higher(getHigher(listBuyings));
		summaryMovement.setSales_average(getAverage(listSales));
		summaryMovement.setBuyings_average(getAverage(listBuyings));
		summaryMovement.setSale_median(getMedian(listSales));
		summaryMovement.setBuying_median(getMedian(listBuyings));
		summaryMovement.setStandard_desviation_sale(getDesvation(listSales, summaryMovement.getSales_average()));
		summaryMovement.setStandard_desviation_buying(getDesvation(listBuyings, summaryMovement.getBuyings_average()));

		this.summaryMovement = summaryMovement;
		return summaryMovement;
	}

	/**
	 * 
	 * @param listBitcoin
	 * @return
	 */
	private List<Bitcoin> getHigher(List<Bitcoin> listBitcoin) {
		List<Bitcoin> listFirstFiveHigher = new ArrayList<>();
		Bitcoin bitcoinTemp = new Bitcoin();

		int initial = 0;
		for (Bitcoin sale : listBitcoin) {
			if ((initial == 0) || (sale.getPrice() < bitcoinTemp.getPrice())) {
				listFirstFiveHigher.add(sale);
				bitcoinTemp = sale;
			}

			if (listFirstFiveHigher.size() > 4) {
				break;
			}

			initial++;
		}

		return listFirstFiveHigher;
	}

	/**
	 * 
	 * @param listBitcoin
	 * @return
	 */
	private float getAverage(List<Bitcoin> listBitcoin) {
		float priceAverage = 0.0f;
		float total = 0.0f;

		for (Bitcoin trade : listBitcoin) {
			total += trade.getPrice();
		}

		priceAverage = total / listBitcoin.size();

		return priceAverage;
	}

	/**
	 * 
	 * @param listBitcoin
	 * @return
	 */
	private float getMedian(List<Bitcoin> listBitcoin) {
		float priceMedian = 0.0f;

		if (listBitcoin.size() % 2 == 0) {
			float subTotal = listBitcoin.get(listBitcoin.size() / 2).getPrice()
					+ listBitcoin.get((listBitcoin.size() / 2) - 1).getPrice();
			priceMedian = subTotal / 2;
		} else {
			priceMedian = listBitcoin.get(listBitcoin.size() / 2).getPrice();
		}

		return priceMedian;
	}

	/**
	 * 
	 * @param listBitcoin
	 * @param priceAverage
	 * @return
	 */
	private float getDesvation(List<Bitcoin> listBitcoin, float priceAverage) {
		float summaryMedianSquare = 0.0f;
		float desvation = 0.0f;

		for (Bitcoin trade : listBitcoin) {
			summaryMedianSquare += (float) Math.pow(Math.abs(trade.getPrice() - priceAverage), 2);
		}

		desvation = (float) Math.sqrt(summaryMedianSquare / listBitcoin.size());

		return desvation;
	}

}
