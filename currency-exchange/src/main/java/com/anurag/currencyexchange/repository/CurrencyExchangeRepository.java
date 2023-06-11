package com.anurag.currencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.currencyexchange.pojo.CurrencyExchangePojo;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangePojo, Long> {

	CurrencyExchangePojo findByFromAndTo(String from, String to);
}
