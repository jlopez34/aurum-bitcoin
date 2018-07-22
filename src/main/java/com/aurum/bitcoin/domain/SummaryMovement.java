package com.aurum.bitcoin.domain;

import java.util.List;

import lombok.Data;
/**
 * 
 * @author momo
 *
 */
@Data
public class SummaryMovement {

	private List<Bitcoin> sales_higher;
	private List<Bitcoin> buyings_higher;
	private float buyings_average;
	private float sales_average;
	private float sale_median;
	private float buying_median;
	private float standard_desviation_sale;
	private float standard_desviation_buying;

	public SummaryMovement() {
	}

	public List<Bitcoin> getSales_higher() {
		return sales_higher;
	}

	public void setSales_higher(List<Bitcoin> sales_higher) {
		this.sales_higher = sales_higher;
	}

	public List<Bitcoin> getBuyings_higher() {
		return buyings_higher;
	}

	public void setBuyings_higher(List<Bitcoin> buyings_higher) {
		this.buyings_higher = buyings_higher;
	}

	public float getBuyings_average() {
		return buyings_average;
	}

	public void setBuyings_average(float buyings_average) {
		this.buyings_average = buyings_average;
	}

	public float getSales_average() {
		return sales_average;
	}

	public void setSales_average(float sales_average) {
		this.sales_average = sales_average;
	}

	public float getSale_median() {
		return sale_median;
	}

	public void setSale_median(float sale_median) {
		this.sale_median = sale_median;
	}

	public float getBuying_median() {
		return buying_median;
	}

	public void setBuying_median(float buying_median) {
		this.buying_median = buying_median;
	}

	public float getStandard_desviation_sale() {
		return standard_desviation_sale;
	}

	public void setStandard_desviation_sale(float standard_desviation_sale) {
		this.standard_desviation_sale = standard_desviation_sale;
	}

	public float getStandard_desviation_buying() {
		return standard_desviation_buying;
	}

	public void setStandard_desviation_buying(float standard_desviation_buying) {
		this.standard_desviation_buying = standard_desviation_buying;
	}

	@Override
	public String toString() {
		return "SummaryMovement [sales_higher=" + sales_higher + ", buyings_higher=" + buyings_higher
				+ ", buyings_average=" + buyings_average + ", sales_average=" + sales_average + ", sale_median="
				+ sale_median + ", buying_median=" + buying_median + ", standard_desviation_sale="
				+ standard_desviation_sale + ", standard_desviation_buying=" + standard_desviation_buying + "]";
	}

}
