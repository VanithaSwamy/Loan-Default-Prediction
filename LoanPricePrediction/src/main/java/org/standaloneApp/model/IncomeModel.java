package org.standaloneApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeModel extends BorrowerModel{
	private int income_id;
	private float income;
	private String income_source;
}

