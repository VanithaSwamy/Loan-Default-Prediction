package org.standaloneApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanModel extends CreditModel{
	private int loan_id;
	private String loan_type;
	private double loan_amt;
}