package org.standaloneApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditModel extends IncomeModel {
	private int cred_id;
	private int cred_score;
}

