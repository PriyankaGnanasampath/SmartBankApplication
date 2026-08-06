package com.smartbank.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbank.common.constants.SequenceContants;
import com.smartbank.common.repository.SequenceGeneratorRepository;
@Service
public class SequenceGeneratorService {

	@Autowired
	private SequenceGeneratorRepository sequenceRepository;

	public String generateSequenceNumber(String sequenceName) {

		Long sequence;

		switch (sequenceName) {

		case SequenceContants.SAVINGS_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("savings_account_seq");
			return "SB" + sequence;

		case SequenceContants.CURRENT_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("current_account_seq");
			return "CA" + sequence;

		case SequenceContants.LOAN_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("loan_account_seq");
			return "LN" + sequence;

		case SequenceContants.JOINT_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("joint_account_seq");
			return "JT" + sequence;
		case SequenceContants.STUDENT_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("student_account_seq");
			return "ST" + sequence;
		case SequenceContants.NRI_ACCOUNT_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("nri_account_seq");
			return "NR" + sequence;
		case SequenceContants.TRANSACTION_REFERENCE_SEQUENCE:
			sequence = sequenceRepository.getNextSequence("trans_ref_seq");
			return "TRAN" + sequence+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS"));
		case SequenceContants.EDUCATION_LOAN_SEQ:
			sequence = sequenceRepository.getNextSequence("education_loan_seq");
			return "ELN" + sequence;
		case SequenceContants.GOLD_LOAN_SEQ:
			sequence = sequenceRepository.getNextSequence("gold_loan_seq");
			return "GLN" + sequence;
		case SequenceContants.HOME_LOAN_SEQ:
			sequence = sequenceRepository.getNextSequence("home_loan_seq");
			return "HLN" + sequence;
		case SequenceContants.VEHICLE_LOAN_SEQ:
			sequence = sequenceRepository.getNextSequence("vehicle_loan_seq");
			return "VLN" + sequence;
		case SequenceContants.PERSONAL_LOAN_SEQ:
			sequence = sequenceRepository.getNextSequence("personal_loan_seq");
			return "PLN" + sequence;	
			
		default:
			throw new IllegalArgumentException("Invalid Account Type");
		}
	}

}
