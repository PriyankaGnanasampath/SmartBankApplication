package com.smartbank.loan.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.smartbank.customer.model.Customer;
import com.smartbank.loan.common.LoanConstants;
import com.smartbank.loan.dto.ApplyLoanRequestDto;
import com.smartbank.loan.dto.ApplyLoanResponseDto;
import com.smartbank.loan.dto.ApproveLoanRequestDto;
import com.smartbank.loan.dto.ApproveLoanResponseDto;
import com.smartbank.loan.dto.DisburseLoanRequestDto;
import com.smartbank.loan.dto.LoanRepaymentRequestDto;
import com.smartbank.loan.dto.RejectLoanRequestDto;
import com.smartbank.loan.exception.InvalidLoanRequestException;
import com.smartbank.loan.helper.ApplyLoanHelper;
import com.smartbank.loan.helper.ApproveLoanHelper;
import com.smartbank.loan.helper.DisburseLoanHelper;
import com.smartbank.loan.helper.LoanHelper;
import com.smartbank.loan.helper.LoanInstallmentHelper;
import com.smartbank.loan.helper.LoanRepaymentHelper;
import com.smartbank.loan.model.Loan;
import com.smartbank.loan.model.LoanInstallment;
import com.smartbank.loan.model.LoanStatus;
import com.smartbank.loan.model.PaymentStatus;
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
	private final LoanRepaymentHelper loanRepaymentHelper;
	private final LoanInstallmentHelper loanInstallmentHelper;

	public LoanServiceImpl(LoanRepository loanRepository, LoanInstallmentRepository loanInstallmentRepository,
			LoanHelper loanHelper, ApplyLoanHelper applyLoanHelper, ApproveLoanHelper approveLoanHelper,
			DisburseLoanHelper disburseLoanHelper, LoanRepaymentHelper loanRepaymentHelper,
			LoanInstallmentHelper loanInstallmentHelper) {
		this.loanRepository = loanRepository;
		this.loanInstallmentRepository = loanInstallmentRepository;
		this.loanHelper = loanHelper;
		this.applyLoanHelper = applyLoanHelper;
		this.approveLoanHelper = approveLoanHelper;
		this.disburseLoanHelper = disburseLoanHelper;
		this.loanRepaymentHelper = loanRepaymentHelper;
		this.loanInstallmentHelper = loanInstallmentHelper;

	}

	@Override
	public ApplyLoanResponseDto applyLoan(ApplyLoanRequestDto applyLoanRequest) {
		// TODO Auto-generated method stub
		Customer existingCustomer = loanHelper.getExistingCustomer(applyLoanRequest.getCustomerId());
		applyLoanHelper.validateApplyLoanRequest(existingCustomer, applyLoanRequest);
		Loan updatedLoanDetails = applyLoanHelper.buildApplyLoanDetails(applyLoanRequest, existingCustomer);
		loanRepository.save(updatedLoanDetails);
		ApplyLoanResponseDto applyLoanResponse = applyLoanHelper.populateLoanResponse(updatedLoanDetails);
		return applyLoanResponse;
	}

	@Override
	public ApproveLoanResponseDto approveLoan(Long loanId, ApproveLoanRequestDto approveLoanRequest) {
		// TODO Auto-generated method stub
		ApproveLoanResponseDto approveLoanResponse = new ApproveLoanResponseDto();
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(loanId);
		if (approveLoanHelper.validateLoanForApproval(existingLoan)) {
			Loan updateLoanInfo = approveLoanHelper.buildApproveDetails(approveLoanRequest, existingLoan);
			loanRepository.save(updateLoanInfo);
			approveLoanResponse = approveLoanHelper.populateLoanResponse(updateLoanInfo);
		}

		return approveLoanResponse;
	}

	@Override
	public Loan rejectLoan(Long loanId, RejectLoanRequestDto rejectLoanRequestDto) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(loanId);
		if (null == rejectLoanRequestDto.getRemarks()) {
			throw new InvalidLoanRequestException("Reason should not be Null or empty");
		}
		if (existingLoan.getLoanStatus().equals(LoanStatus.PENDING)) {
			existingLoan.setLoanStatus(LoanStatus.REJECTED);
			existingLoan.setRemarks(rejectLoanRequestDto.getRemarks());
			existingLoan.setLastModifiedDate(LocalDateTime.now());
			existingLoan.setModifiedBy(rejectLoanRequestDto.getRejectedBy());
			loanRepository.save(existingLoan);
		} else {
			throw new InvalidLoanRequestException(
					"Rejection can be done only for Pending loans. The given Loan status is "
							+ existingLoan.getLoanStatus() + " and the Loan Id is " + loanId);
		}

		return existingLoan;
	}

	@Transactional
	@Override
	public Loan disburseLoan(Long loanId, DisburseLoanRequestDto disburseLoanRequest) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanId(loanId);
		disburseLoanHelper.validateLoanStatus(existingLoan.getLoanStatus());
		disburseLoanHelper.validateLoanDisburse(existingLoan);
		BigDecimal totalIntrestAmount = disburseLoanHelper.calculateTotalIntrestAmount(existingLoan.getLoanAmount(),
				existingLoan.getInterestRate(), existingLoan.getTenureMonths());
		BigDecimal totalRepaymentAmount = disburseLoanHelper.calculateTotalRepaymentAmount(existingLoan.getLoanAmount(),
				totalIntrestAmount);
		BigDecimal emiAmount = disburseLoanHelper.calculateEMI(totalRepaymentAmount, existingLoan.getTenureMonths());
		existingLoan.setLoanStatus(LoanStatus.ACTIVE);
		existingLoan.setDisbursementDate(LocalDate.now());
		existingLoan.setLoanStartDate(LocalDate.now());
		existingLoan.setLoanEndDate(disburseLoanHelper.calculateLoanEndDate(existingLoan.getTenureMonths()));
		existingLoan.setEmiAmount(emiAmount);
		existingLoan.setTotalInterest(totalIntrestAmount);
		existingLoan.setTotalRepaymentAmount(totalRepaymentAmount);
		existingLoan.setOutstandingAmount(existingLoan.getTotalRepaymentAmount());
		existingLoan.setLastModifiedDate(LocalDateTime.now());
		disburseLoanHelper.updateLoanInstallment(existingLoan);
		loanRepository.save(existingLoan);

		return existingLoan;
	}

	@Override
	public Loan payEMI(LoanRepaymentRequestDto loanPaymentRequest) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanNumber(loanPaymentRequest.getLoanNumber());

		loanRepaymentHelper.validateLoanStatus(existingLoan.getLoanStatus());
		loanHelper.validateCustomerStatus(existingLoan.getCustomer().getCustomerId());
		loanRepaymentHelper.validateOutstandingAmount(existingLoan.getOutstandingAmount());
		LoanInstallment existingLoanInstallment = loanInstallmentHelper
				.getFirstByLoanLoanIdAndPaymentStatusOrderByInstallmentNumber(existingLoan.getLoanId(),
						PaymentStatus.PENDING);
		loanRepaymentHelper.validateEMIAmount(loanPaymentRequest.getAmount(), existingLoanInstallment.getEmiAmount());
		loanRepaymentHelper.updateLoanInstallment(loanPaymentRequest, existingLoanInstallment);
		existingLoan.setOutstandingAmount(loanRepaymentHelper.calculateOutstandingAmount(
				existingLoan.getOutstandingAmount(), existingLoanInstallment.getEmiAmount()));
		existingLoan.setLoanStatus(loanRepaymentHelper.getLoanStatus(existingLoan.getOutstandingAmount()));
		loanRepository.save(existingLoan);
		return existingLoan;
	}

	@Override
	public Loan closeLoan(String loanNumber) {
		// TODO Auto-generated method stub
		Loan existingLoan = loanHelper.getExistingLoanByLoanNumber(loanNumber);

		// if (LoanStatus.ACTIVE.equals(existingLoan.getLoanStatus())) {
		if (existingLoan.getOutstandingAmount().compareTo(BigDecimal.ZERO) == 0) {
			existingLoan.setLoanEndDate(LocalDate.now());
		//	existingLoan.setOutstandingAmount(BigDecimal.ZERO);
			existingLoan.setLastModifiedDate(LocalDateTime.now());
			existingLoan.setModifiedBy(LoanConstants.APPROVED_USER_NAME);
			existingLoan.setLoanStatus(LoanStatus.CLOSED);
			existingLoan.setRemarks("Loan Repayment completed successfully");
		} else {
			throw new InvalidLoanRequestException("Loan status is not Active. LoanNumber is " + loanNumber);
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