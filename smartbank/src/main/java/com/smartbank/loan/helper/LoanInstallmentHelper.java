package com.smartbank.loan.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartbank.common.service.SequenceGeneratorService;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.repository.CustomerRepository;
import com.smartbank.loan.dto.ApproveLoanRequestDto;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.exception.LoanInstallmentNotFoundException;
import com.smartbank.loan.exception.LoanNotFoundException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.LoanType;
import com.smartbank.loan.model.PaymentStatus;
import com.smartbank.loan.repository.LoanInstallmentRepository;
import com.smartbank.loan.repository.LoanRepository;

@Component
public class LoanInstallmentHelper {
	private final LoanRepository loanRepository;
	private final LoanInstallmentRepository loanInstallmentRepository;
	private final CustomerRepository customerRepository;
	private final SequenceGeneratorService sequenceGeneratorService;

	public LoanInstallmentHelper(LoanRepository loanRepository, LoanInstallmentRepository loanInstallmentRepository,
			CustomerRepository customerRepository, SequenceGeneratorService sequenceGeneratorService) {
		this.loanRepository = loanRepository;
		this.loanInstallmentRepository = loanInstallmentRepository;
		this.customerRepository = customerRepository;
		this.sequenceGeneratorService = sequenceGeneratorService;
	}

	public LoanInstallment getFirstByLoanLoanIdAndPaymentStatusOrderByInstallmentNumber(Long loanId,
			PaymentStatus paymentStatus) {
		LoanInstallment existingLoanInstallment = loanInstallmentRepository
				.findFirstByLoanLoanIdAndPaymentStatusOrderByInstallmentNumber(loanId, paymentStatus)
				.orElseThrow(() -> new LoanInstallmentNotFoundException("Loan Installment not found for this loanId: "
						+ loanId + " and PaymentStatus is " + paymentStatus));
		return existingLoanInstallment;

	}

	public List<LoanInstallment> getExistingLoanInstallments(Long loanId) {
		List<LoanInstallment> existingLoanInstallment = loanInstallmentRepository.findByLoanLoanId(loanId);
		return existingLoanInstallment;
	}

}
