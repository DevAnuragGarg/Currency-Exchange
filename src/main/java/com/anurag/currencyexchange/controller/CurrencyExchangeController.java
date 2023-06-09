package com.anurag.currencyexchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.currencyexchange.pojo.CurrencyExchangePojo;
import com.anurag.currencyexchange.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	CurrencyExchangeRepository repository;

	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}/value/{amount}")
	public CurrencyExchangePojo getCurrencyExchange(@PathVariable("fromCurrency") String from,
			@PathVariable("toCurrency") String to, @PathVariable("amount") BigDecimal conversionAmount) {

		// get the data from the repository
		CurrencyExchangePojo currencyExchange = repository.findByFromAndTo(from, to);
		currencyExchange.setInputValue(conversionAmount);
		currencyExchange.setOutputValue(conversionAmount.multiply(currencyExchange.getConversionMutliple()));
		return currencyExchange;
	}
}
