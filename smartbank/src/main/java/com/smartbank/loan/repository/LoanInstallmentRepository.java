package com.smartbank.loan.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.PaymentStatus;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {
	public List<LoanInstallment> findByLoanLoanNumber(String loanNumber);

	public List<LoanInstallment> findByPaymentStatus(PaymentStatus paymentStatus);

	public List<LoanInstallment> findByDueDateBetween(LocalDateTime from, LocalDateTime to);

	public List<LoanInstallment> findByLoanLoanId(Long loanId);

	public Optional<LoanInstallment> findFirstByLoanLoanIdAndPaymentStatusOrderByInstallmentNumber(Long loanId,PaymentStatus paymentStatus);
}
