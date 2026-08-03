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

		default:
			throw new IllegalArgumentException("Invalid Account Type");
		}
	}

}
