package com.anurag.currencyexchange.pojo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity(name = "currency_exchange")
public class CurrencyExchangePojo {

	public CurrencyExchangePojo() {

	}

	public CurrencyExchangePojo(long id, String to, String from, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.to = to;
		this.from = from;
		this.conversionMultiple = conversionMultiple;
	}

	@Id
	@Column(name = "conversion_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "conversion_to")
	private String to;

	@Column(name = "conversion_from")
	private String from;

	@Column(name = "conversion_value")
	private BigDecimal conversionMultiple;

	@Transient
	private BigDecimal inputValue;

	@Transient
	private BigDecimal usdToInrValue;

	@Transient
	private BigDecimal eurToInrValue;

	@Transient
	private BigDecimal audToInrValue;

	@Transient
	private BigDecimal gbpToInrValue;

	@Transient
	private BigDecimal kwdToInrValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public BigDecimal getInputValue() {
		return inputValue;
	}

	public void setInputValue(BigDecimal inputValue) {
		this.inputValue = inputValue;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public BigDecimal getUsdToInrValue() {
		return usdToInrValue;
	}

	public void setUsdToInrValue(BigDecimal usdToInrValue) {
		this.usdToInrValue = usdToInrValue;
	}

	public BigDecimal getEurToInrValue() {
		return eurToInrValue;
	}

	public void setEurToInrValue(BigDecimal eurToInrValue) {
		this.eurToInrValue = eurToInrValue;
	}

	public BigDecimal getAudToInrValue() {
		return audToInrValue;
	}

	public void setAudToInrValue(BigDecimal audToInrValue) {
		this.audToInrValue = audToInrValue;
	}

	public BigDecimal getGbpToInrValue() {
		return gbpToInrValue;
	}

	public void setGbpToInrValue(BigDecimal gbpToInrValue) {
		this.gbpToInrValue = gbpToInrValue;
	}

	public BigDecimal getKwdToInrValue() {
		return kwdToInrValue;
	}

	public void setKwdToInrValue(BigDecimal kwdToInrValue) {
		this.kwdToInrValue = kwdToInrValue;
	}

	@Override
	public String toString() {
		return "CurrencyExchangePojo [id=" + id + ", to=" + to + ", from=" + from + ", conversionMultiple="
				+ conversionMultiple + ", inputValue=" + inputValue + ", usdToInrValue=" + usdToInrValue
				+ ", eurToInrValue=" + eurToInrValue + ", audToInrValue=" + audToInrValue + ", gbpToInrValue="
				+ gbpToInrValue + ", kwdToInrValue=" + kwdToInrValue + "]";
	}
}
