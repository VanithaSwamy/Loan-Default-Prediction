package org.standaloneApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoanEvaluationModel extends BorrowerModel{
	private double income;
	private String income_source;
	private int cred_score;
	private String loan_type;
	private double loan_amt;
}
