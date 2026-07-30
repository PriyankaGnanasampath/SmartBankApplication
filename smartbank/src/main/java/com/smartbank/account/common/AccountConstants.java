package com.smartbank.account.common;

import java.math.BigDecimal;

public class AccountConstants {
public static final BigDecimal SAVINGS_MIN_BALANCE = new BigDecimal("1000");

public static final BigDecimal CURRENT_MIN_BALANCE = new BigDecimal("5000");

public static final BigDecimal STUDENT_MIN_BALANCE = new BigDecimal("500");

public static final BigDecimal LOAN_BALANCE = new BigDecimal("0");

public static final BigDecimal NRI_MIN_BALANCE = new BigDecimal("10000");

public static final BigDecimal JOINT_MIN_BALANCE = new BigDecimal("5000");
public static final String MODIFIED_USER_NAME="Priya";

public static final int STUDENT_MAX_AGE = 25;
public static final String SAVINGS_ACCOUNT_PREFIX="SB";
public static final String CURRENT_ACCOUNT_PREFIX="CA";
public static final String JOINT_ACCOUNT_PREFIX="JT";
public static final String NRI_ACCOUNT_PREFIX="NR";
public static final String STUDENT_ACCOUNT_PREFIX="ST";
public static final String LOAN_ACCOUNT_PREFIX="LN";
}
