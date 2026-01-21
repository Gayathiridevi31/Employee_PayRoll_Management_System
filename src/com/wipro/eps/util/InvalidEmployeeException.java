package com.wipro.eps.util;

public class InvalidEmployeeException  extends Exception{
	public InvalidEmployeeException(String message)
	{
		super(message);
	}
	@Override
	public String toString() {
	    return "InvalidEmployeeException: Employee ID is missing, malformed, or not registered. "
	         + "Payroll processing cannot continue for a nonexistent employee.";
	}


}
