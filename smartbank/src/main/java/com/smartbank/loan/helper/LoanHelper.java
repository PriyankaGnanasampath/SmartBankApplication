package com.smartbank.loan.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartbank.common.service.SequenceGeneratorService;
import com.smartbank.customer.exception.CustomerNotFoundException;
import com.smartbank.customer.model.Customer;
import com.smartbank.customer.repository.CustomerRepository;
import com.smartbank.loan.dto.ApproveLoanRequest;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.exception.LoanNotFoundException;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.LoanType;
import com.smartbank.loan.repository.LoanInstallmentRepository;
import com.smartbank.loan.repository.LoanRepository;

@Component
public class LoanHelper {
	private final LoanRepository loanRepository;
	private final LoanInstallmentRepository loanInstallmentRepository;
	private final CustomerRepository customerRepository;
	private final SequenceGeneratorService sequenceGeneratorService;

	public LoanHelper(LoanRepository loanRepository, LoanInstallmentRepository loanInstallmentRepository,
			CustomerRepository customerRepository, SequenceGeneratorService sequenceGeneratorService) {
		this.loanRepository = loanRepository;
		this.loanInstallmentRepository = loanInstallmentRepository;
		this.customerRepository = customerRepository;
		this.sequenceGeneratorService = sequenceGeneratorService;

	}

	public Loan getExistingLoanByLoanId(Long loanId) {

		Loan existingLoanById = loanRepository.findById(loanId)
				.orElseThrow(() -> new LoanNotFoundException("LoanId not found. LoanId is " + loanId));
		return existingLoanById;
	}

	public Loan getExistingLoanByLoanNumber(String loanNumber) {
		Loan existingLoanByNumber = loanRepository.findByLoanNumber(loanNumber)
				.orElseThrow(() -> new LoanNotFoundException(
						"LoanNumber is not present in the  database. LoanNumber is " + loanNumber));

		return existingLoanByNumber;
	}

	public Customer getExistingCustomer(Long customerId) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Please provide the valid customerId"));
		return existingCustomer;

	}

	public List<Loan> getExistingLoansForCustomer(Long customerId) {

		List<Loan> existingLoans = loanRepository.findByCustomerCustomerId(customerId);
		return existingLoans;

	}

	public String getLoanNumber(String loanType) {
		String loanNummber = sequenceGeneratorService.generateSequenceNumber(loanType);
		return loanNummber;

	}

}
