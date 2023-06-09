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

	public CurrencyExchangePojo(long id, String to, String from, BigDecimal conversionMutliple) {
		super();
		this.id = id;
		this.to = to;
		this.from = from;
		this.conversionMutliple = conversionMutliple;
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
	private BigDecimal conversionMutliple;

	@Transient
	private BigDecimal inputValue;
	@Transient
	private BigDecimal outputValue;

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

	public BigDecimal getConversionMutliple() {
		return conversionMutliple;
	}

	public void setConversionMutliple(BigDecimal conversionMutliple) {
		this.conversionMutliple = conversionMutliple;
	}

	public BigDecimal getInputValue() {
		return inputValue;
	}

	public void setInputValue(BigDecimal inputValue) {
		this.inputValue = inputValue;
	}

	public BigDecimal getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(BigDecimal outputValue) {
		this.outputValue = outputValue;
	}

	@Override
	public String toString() {
		return "CurrencyExchangePojo [id=" + id + ", to=" + to + ", from=" + from + ", conversionMutliple="
				+ conversionMutliple + ", inputValue=" + inputValue + ", outputValue=" + outputValue + "]";
	}
}
