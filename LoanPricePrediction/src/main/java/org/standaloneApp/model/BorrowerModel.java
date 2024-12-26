package org.standaloneApp.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BorrowerModel {
	private int bid;
	private String name;
	private java.sql.Date dob;
	private String phno; //note phone number dtype original is int
	private String email;
	private String id_proof;
}