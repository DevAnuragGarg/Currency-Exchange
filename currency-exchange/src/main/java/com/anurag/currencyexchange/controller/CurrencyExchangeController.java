package com.anurag.currencyexchange.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.currencyexchange.pojo.CurrencyExchangePojo;
import com.anurag.currencyexchange.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	CurrencyExchangeRepository repository;

	@GetMapping("/test")
	public String getHelloWorld() {
		return "Currency Exchange service is working fine";
	}

	@PostMapping("/currency-exchange")
	public List<CurrencyExchangePojo> saveCurrencyExchange(@RequestBody List<CurrencyExchangePojo> currencyExchange) {

		// save the data in repository
		return repository.saveAll(currencyExchange);
	}

	@GetMapping("/currency-exchange/indian-ruppee/for/{amount}")
	public CurrencyExchangePojo getCurrencyExchange(@PathVariable("amount") BigDecimal conversionAmount) {

		CurrencyExchangePojo responseCurrencyExchange = new CurrencyExchangePojo();
		responseCurrencyExchange.setInputValue(conversionAmount);

		// get the data from the repository
		Iterable<CurrencyExchangePojo> currencyExchanges = repository.findAll();

		// process data and add to the response
		currencyExchanges.forEach((currencyExchange) -> {
			if (currencyExchange.getFrom().equalsIgnoreCase("USD")) {
				responseCurrencyExchange
						.setUsdToInrValue(conversionAmount.multiply(currencyExchange.getConversionMultiple()));
			} else if (currencyExchange.getFrom().equalsIgnoreCase("EUR")) {
				responseCurrencyExchange
						.setEurToInrValue(conversionAmount.multiply(currencyExchange.getConversionMultiple()));
			} else if (currencyExchange.getFrom().equalsIgnoreCase("AUD")) {
				responseCurrencyExchange
						.setAudToInrValue(conversionAmount.multiply(currencyExchange.getConversionMultiple()));
			} else if (currencyExchange.getFrom().equalsIgnoreCase("GBP")) {
				responseCurrencyExchange
						.setGbpToInrValue(conversionAmount.multiply(currencyExchange.getConversionMultiple()));
			} else if (currencyExchange.getFrom().equalsIgnoreCase("KWD")) {
				responseCurrencyExchange
						.setKwdToInrValue(conversionAmount.multiply(currencyExchange.getConversionMultiple()));
			}
		});
		return responseCurrencyExchange;
	}
}
