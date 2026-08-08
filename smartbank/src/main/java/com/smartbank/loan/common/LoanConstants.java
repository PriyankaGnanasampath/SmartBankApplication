package com.smartbank.loan.common;

import java.math.BigDecimal;

public class LoanConstants {
	public static final String CREATED_USER_NAME = "ANITHA";
	public static final String APPROVED_USER_NAME = "KAVITHA";
	public static final String MODIFIED_USER_NAME = "SAVITHA";
	public static final int MAX_ACTIVE_LOAN = 3;
	public static final BigDecimal HUNDRED=BigDecimal.valueOf(100);
	public static final BigDecimal TWELVE=BigDecimal.valueOf(12);
	public static final BigDecimal ZERO=BigDecimal.ZERO;
	
	public static final BigDecimal MIN_HOME_LOAN_AMOUNT = BigDecimal.valueOf(100000);
	public static final BigDecimal MAX_HOME_LOAN_AMOUNT = BigDecimal.valueOf(5000000);

	public static final BigDecimal MIN_PERSONAL_LOAN = BigDecimal.valueOf(50000);
	public static final BigDecimal MAX_PERSONAL_LOAN = BigDecimal.valueOf(500000);

	public static final BigDecimal MIN_GOLD_LOAN = BigDecimal.valueOf(10000);
	public static final BigDecimal MAX_GOLD_LOAN = BigDecimal.valueOf(500000);

	public static final BigDecimal MIN_VEHICLE_LOAN = BigDecimal.valueOf(25000);
	public static final BigDecimal MAX_VEHICLE_LOAN = BigDecimal.valueOf(1000000);

	public static final BigDecimal MIN_EDUCATION_LOAN = BigDecimal.valueOf(50000);
	public static final BigDecimal MAX_EDUCATION_LOAN = BigDecimal.valueOf(500000);

	public static final int DEFAULT_HOME_LOAN_RATE = 6;

	public static final int DEFAULT_PERSONAL_LOAN_RATE = 14;

	public static final int DEFAULT_VEHICLE_LOAN_RATE = 8;

	public static final int DEFAULT_GOLD_LOAN_RATE = 15;

	public static final int DEFAULT_EDUCATION_LOAN_RATE = 11;

	public static final int MIN_HOME_LOAN_TENURE_MONTHS = 60;
	public static final int MAX_HOME_LOAN_TENURE_MONTHS = 360;

	public static final int MIN_GOLD_LOAN_TENURE_MONTHS = 6;
	public static final int MAX_GOLD_LOAN_TENURE_MONTHS = 36;

	public static final int MIN_VEHICLE_LOAN_TENURE_MONTHS = 12;
	public static final int MAX_VEHICLE__LOAN_TENURE_MONTHS = 84;

	public static final int MIN_PERSONAL_LOAN_TENURE_MONTHS = 12;
	public static final int MAX_PERSONAL_LOAN_TENURE_MONTHS = 60;

	public static final int MIN_EDUCATION_LOAN_TENURE_MONTHS = 12;
	public static final int MAX_EDUCATION_TENURE_MONTHS = 180;

}
