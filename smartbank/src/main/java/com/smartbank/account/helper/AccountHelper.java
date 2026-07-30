package com.smartbank.account.helper;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
	public int CalculateAgeFromDOB(LocalDate date) {

		return Period.between(date, LocalDate.now()).getYears();

	}
}
