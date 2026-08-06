package com.smartbank.loan.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.PaymentStatus;
public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {
	public LoanInstallment findByLoanLoanNumber(String loanNumber);

	public List<LoanInstallment> findByPaymentStatus(PaymentStatus paymentStatus);

	public List<LoanInstallment> findByDueDateBetween(LocalDateTime from,LocalDateTime to  );

	public LoanInstallment findByLoanLoanId(Long loanId);
}
