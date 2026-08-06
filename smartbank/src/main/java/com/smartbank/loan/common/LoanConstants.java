package com.smartbank.loan.common;

import java.math.BigDecimal;

public class LoanConstants {
	public static final String CREATED_USER_NAME = "PRIYA";
	public static final String APPROVED_USER_NAME = "MALI";
	public static final int MAX_ACTIVE_LOAN = 3;

	public static final BigDecimal ZERO=BigDecimal.ZERO;
	
	public static final BigDecimal MIN_HOME_LOAN_AMOUNT = BigDecimal.valueOf(500000);
	public static final BigDecimal MAX_HOME_LOAN_AMOUNT = BigDecimal.valueOf(50000000);

	public static final BigDecimal MIN_PERSONAL_LOAN = BigDecimal.valueOf(50000);
	public static final BigDecimal MAX_PERSONAL_LOAN = BigDecimal.valueOf(4000000);

	public static final BigDecimal MIN_GOLD_LOAN = BigDecimal.valueOf(50000);
	public static final BigDecimal MAX_GOLD_LOAN = BigDecimal.valueOf(10000);

	public static final BigDecimal MIN_VEHICLE_LOAN = BigDecimal.valueOf(20000000);
	public static final BigDecimal MAX_VEHICLE_LOAN = BigDecimal.valueOf(10000000);

	public static final BigDecimal MIN_EDUCATION_LOAN = BigDecimal.valueOf(500000);
	public static final BigDecimal MAX_EDUCATION_LOAN = BigDecimal.valueOf(7500000);

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
