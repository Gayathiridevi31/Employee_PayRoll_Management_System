package com.wipro.eps.service;
import java.util.*;
import java.util.ArrayList;
import com.wipro.eps.entity.Employee;
import com.wipro.eps.entity.PayrollRule;
import com.wipro.eps.entity.Payslip;
import com.wipro.eps.util.InvalidEmployeeException;
import com.wipro.eps.util.SalaryComputationException;
import com.wipro.eps.util.PayrollProcessingException;

public class PayrollService {
	 private ArrayList<Employee>employeeList;
	 private PayrollRule payrollRule;
	  private Map<String, Payslip> payslipMap = new HashMap<>();
	 public PayrollService(ArrayList<Employee>employeeList,PayrollRule payrollRule)
	 {
		 this.employeeList=employeeList;
		 this.payrollRule=payrollRule;
	 }
	public boolean validateEmployee(String employeeId)throws InvalidEmployeeException
	{
		if(employeeId==null || employeeId.trim().isEmpty())
		{
			throw new InvalidEmployeeException("employeeid cannot be null");
		}
		for(Employee emp:employeeList)
		{
		if(emp.getEmployeeId().equals(employeeId))
		{
			return true;
		}
		}
		throw  new InvalidEmployeeException("employee not found");
	}
	public Employee findEmployee(String employeeId)throws InvalidEmployeeException
	{

		if(employeeId==null || employeeId.trim().isEmpty())
		{
			throw new InvalidEmployeeException("employeeid cannot be null");
		}
		for(Employee emp:employeeList)
		{
			return emp;
		}
		throw new InvalidEmployeeException("employee not exist");
		
	}
	public double calculateGrossSalary(Employee employee) throws SalaryComputationException
	{
		if(employee==null)
		{
			throw new SalaryComputationException("employee cannot be null");
		}
		double basic=employee.getBasicSalary();
		double hra=employee.getHra();
		double otherAllowances=employee.getOtherAllowance();
		if(basic<0 || hra<0 || otherAllowances<0)
		{
			throw new SalaryComputationException("cannot be negative");
		}
		double grossSalary = basic + hra + otherAllowances;

	    return grossSalary;
	}
	public double calculateTotalDeductions(double gross, Employee emp) 
	        throws SalaryComputationException {

	    if (emp == null) {
	        throw new SalaryComputationException("Employee cannot be null");
	    }

	    if (gross < 0) {
	        throw new SalaryComputationException("Gross salary cannot be negative");
	    }

	    double taxPercent = payrollRule.getTaxPercentage();
	    double pfPercent = payrollRule.getPfPercentage();
	    double otherPercent = payrollRule.getOtherDeductionsPercentage();

	    if (taxPercent < 0 || pfPercent < 0 || otherPercent < 0 ||
	        taxPercent > 100 || pfPercent > 100 || otherPercent > 100) {
	        throw new SalaryComputationException("Invalid deduction percentages");
	    }

	    double tax = (taxPercent / 100.0) * gross;
	    double pf = (pfPercent / 100.0) * gross;
	    double otherDeduction = (otherPercent / 100.0) * gross;

	    double totalDeductions = tax + pf + otherDeduction;

	    if (totalDeductions > gross) {
	        throw new SalaryComputationException("Total deductions exceed gross salary");
	    }

	    return totalDeductions;
	}
	public Payslip generatePayslip(String employeeId, String month) throws Exception {
	    validateEmployee(employeeId);
	    String key = employeeId + "-" + month;
	    if (payslipMap.containsKey(key)) {
	        throw new Exception("Payslip already generated for this employee and month");
	    }
	    Employee emp = findEmployee(employeeId);
	    double gross = calculateGrossSalary(emp);
	    double deductions = calculateTotalDeductions(gross, emp);
	    double netSalary = gross - deductions;
	    String payslipId = "PS-" + employeeId + "-" + month;
	    Payslip payslip = new Payslip(payslipId, employeeId, month, gross, deductions, netSalary);
	    payslipMap.put(key, payslip);
	    return payslip;
	}
	public ArrayList<Payslip> processMonthlyPayroll(ArrayList<String> employeeIds, String month) {
	    ArrayList<Payslip> payslips = new ArrayList<>();
	    for (String empId : employeeIds) {
	        try {
	            Payslip payslip = generatePayslip(empId, month);
	            payslips.add(payslip);
	        } catch (Exception e) {
	            System.out.println("Failed to process payroll for " + empId +"");
	        }
	    }
	    return payslips;
	}}
