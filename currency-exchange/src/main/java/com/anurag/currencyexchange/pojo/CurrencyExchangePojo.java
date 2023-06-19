package com.anurag.currencyexchange.pojo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "currency_exchange")
public class CurrencyExchangePojo {

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
}
