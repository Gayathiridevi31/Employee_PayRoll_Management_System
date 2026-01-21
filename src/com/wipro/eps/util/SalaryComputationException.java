package com.wipro.eps.util;

public class SalaryComputationException extends Exception {
	  public SalaryComputationException(String message) {
	        super(message);
	    }
	@Override
	public String toString() {
	    return "SalaryComputationException: Invalid salary components detected. "
	         + "Basic salary, HRA, or allowances may be missing, negative, or inconsistent, "
	         + "or deduction percentages exceed allowable limits.";
	}


}
