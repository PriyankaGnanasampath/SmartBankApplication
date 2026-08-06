package com.smartbank.loan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.LoanType;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	public Optional<Loan> findByLoanNumber(String loanNumber);

	public List<Loan> findByCustomerCustomerId(Long customerId);

	public List<Loan> findByLoanStatus(LoanStatus loanStatus);

	public List<Loan> findByLoanType(LoanType loanType);

	public List<Loan> findAll();
}
