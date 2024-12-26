package org.standaloneApp.service;
import org.standaloneApp.repository.*;
import java.util.*;
public interface AdminService {
	boolean adminLogin(String name,String password);
	Optional<Map<Integer,String>> getLoanType();
}