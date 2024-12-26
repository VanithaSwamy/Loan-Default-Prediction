package org.standaloneApp.repository;
import java.util.*;

public interface LoanRepository {
	public Optional<Map<Integer,String>> getLoanType();//to get all avail loan types
}
