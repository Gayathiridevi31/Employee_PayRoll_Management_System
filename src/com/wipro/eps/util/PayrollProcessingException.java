package com.wipro.eps.util;

public class PayrollProcessingException extends Exception {
	public  PayrollProcessingException(String message)
	{
		super(message);
	}
	@Override
	public String toString() {
	    return "PayrollProcessingException: Payroll generation failed due to invalid processing logic, "
	         + "such as duplicate payslips for a month, invalid month selection, "
	         + "or mismatched employee and payslip details.";
	}


}
