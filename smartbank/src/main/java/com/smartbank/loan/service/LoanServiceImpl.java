package com.smartbank.loan.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smartbank.customer.model.Customer;
import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.dto.ApplyLoanRequest;
import com.smartbank.loan.dto.ApplyLoanResponse;
import com.smartbank.loan.dto.ApproveLoanRequest;
import com.smartbank.loan.dto.ApproveLoanResponse;
import com.smartbank.loan.dto.DisburseLoanRequest;
import com.smartbank.loan.dto.LoanPaymentRequest;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.exception.LoanNotFoundException;
import com.smartbank.loan.helper.ApplyLoanHelper;
import com.smartbank.loan.helper.ApproveLoanHelper;
import com.smartbank.loan.helper.DisburseLoanHelper;
import com.smartbank.loan.helper.LoanHelper;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.repository.LoanInstallmentRepository;
import com.smartbank.loan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	private final LoanRepository loanRepository;
	private final LoanInstallmentRepository loanInstallmentRepository;
	private final LoanHelper loanHelper;
	private final ApplyLoanHelper applyLoanHelper;
	private final ApproveLoanHelper approveLoanHelper;
	private final DisburseLoanHelper disburseLoanHelper;

	public LoanServiceImpl(LoanRepository loanRepository, LoanInstallmentRepository loanInstallmentRepository,
			LoanHelper loanHelper, ApplyLoanHelper applyLoanHelper, ApproveLoanHelper approveLoanHelper,
			DisburseLoanHelper disburseLoanHelper) {
		this.loanRepository = loanRepository;
		this.loanInstallmentRepository = loanInstallmentRepository;
		this.loanHelper = loanHelper;
		this.applyLoanHelper = applyLoanHelper;
		this.approveLoanHelper = approveLoanHelper;
		this.disburseLoanHelper = disburseLoanHelper;

	}

	@Override
	public ApplyLoanResponse applyLoan(ApplyLoanRequest applyLoanRequest) {
		// TODO Auto-generated method stub
		Customer existingCustomer = loanHelper.getExistingCustomer(applyLoanRequest.getCustomerId());
		applyLoanHelper.validateApplyLoanRequest(existingCustomer, applyLoanRequest);
		Loan updatedLoanDetails = applyLoanHelper.buildApplyLoanDetails(applyLoanRequest, existingCustomer);
		loanRepository.save(updatedLoanDetails);
		ApplyLoanResponse applyLoanResponse = applyLoanHelper.populateLoanResponse(updatedLoanDetails);
		return applyLoanResponse;
	}

	@Override
	public ApproveLoanResponse approveLoan(Long loanId, ApproveLoanRequest approveLoanRequest) {
		// TODO Auto-generated method stub
		ApproveLoanResponse approveLoanResponse = new ApproveLoanResponse();
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(approveLoanRequest.getLoanId());
		if (approveLoanHelper.validateLoanForApproval(existingLoan)) {
			Loan updateLoanInfo = approveLoanHelper.buildApproveDetails(approveLoanRequest);
			loanRepository.save(updateLoanInfo);
			approveLoanResponse = approveLoanHelper.populateLoanResponse(updateLoanInfo);
		}

		return approveLoanResponse;
	}

	@Override
	public Loan rejectLoan(Long loanId, String remarks) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(loanId);
		if (null == remarks) {
			throw new InvalidLoanRequestException("Reason should not be Null or empty");
		}
		if (existingLoan.getLoanStatus().equals(LoanStatus.PENDING)) {
			existingLoan.setLoanStatus(LoanStatus.REJECTED);
			existingLoan.setRemarks(remarks);
			existingLoan.setLastModifiedDate(LocalDateTime.now());
			existingLoan.setModifiedBy(LoanConstants.APPROVED_USER_NAME);
			loanRepository.save(existingLoan);
		} else {
			throw new InvalidLoanRequestException(
					"Rejection can be done only for Pending loans. The given Loan status is "
							+ existingLoan.getLoanStatus() + " and the Loan Id is " + loanId);
		}

		return existingLoan;
	}

	@Override
	public Loan disburseLoan(Long loanId, DisburseLoanRequest disburseLoanRequest) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(loanId);
		disburseLoanHelper.validateLoanDisburse(existingLoan);
		disburseLoanHelper.validateLoanStatus(existingLoan.getLoanStatus());
		BigDecimal intrestAmount = disburseLoanHelper.calculateTotalIntrestAmount(existingLoan.getLoanAmount(),
				existingLoan.getInterestRate(), existingLoan.getTenureMonths());
		BigDecimal repaymentAmount = disburseLoanHelper.calculateTotalRepaymentAmount(intrestAmount,
				existingLoan.getLoanAmount());
		BigDecimal emiAmount = disburseLoanHelper.calculateEMI(repaymentAmount, existingLoan.getTenureMonths());
		BigDecimal outstandingAmount = disburseLoanHelper.calculateOutstandingAmount(repaymentAmount);
		existingLoan.setLoanStartDate(LocalDate.now());
		existingLoan.setLoanStatus(LoanStatus.DISBURSED);
		existingLoan.setLoanEndDate(disburseLoanHelper.calculateLoanEndDate(existingLoan.getTenureMonths()));
		existingLoan.setDisbursementDate(LocalDate.now());
		existingLoan.setOutstandingAmount(outstandingAmount);
		existingLoan.setEmiAmount(emiAmount);

		return existingLoan;
	}

	@Override
	public Loan payEMI(LoanPaymentRequest loanPaymentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan closeLoan(String loanNumber) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanNumber(loanNumber);

		if(LoanStatus.ACTIVE.equals(existingLoan.getLoanStatus()))
		{
			existingLoan.setLoanEndDate(LocalDate.now());
			existingLoan.setOutstandingAmount(BigDecimal.ZERO);
			existingLoan.setLastModifiedDate(LocalDateTime.now());
			existingLoan.setModifiedBy(LoanConstants.APPROVED_USER_NAME);
			existingLoan.setLoanStatus(LoanStatus.CLOSED);
			existingLoan.setRemarks("Loan Repayment completed successfully");
		}
		else {
			throw new InvalidLoanRequestException("Loan status is not Active. LoanNumber is "+loanNumber);
		}
		return existingLoan;
	}

	@Override
	public Loan getLoan(String loanNumber) {
		// TODO Auto-generated method stub

		Loan existingLoan = loanHelper.getExistingLoanByLoanNumber(loanNumber);
		return existingLoan;
	}

	@Override
	public List<Loan> getCustomerLoans(Long customeId) {
		// TODO Auto-generated method stub
		return loanRepository.findByCustomerCustomerId(customeId);
	}

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepository.findAll();
	}
}