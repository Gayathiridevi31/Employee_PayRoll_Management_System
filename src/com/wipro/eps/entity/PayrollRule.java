package com.wipro.eps.entity;

public class PayrollRule {
       private double taxPercentage;
       private double pfPercentage;
       private double otherDeductionsPercentage;
       public PayrollRule() {
    	   
       }
       public PayrollRule(double taxPercentage,double pfPercentage,double otherDeductionsPercentage)
       {
    	   this.taxPercentage=taxPercentage;
    	   this.pfPercentage=pfPercentage;
    	   this.otherDeductionsPercentage=otherDeductionsPercentage;
    	   
       }
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public double getPfPercentage() {
		return pfPercentage;
	}
	public void setPfPercentage(double pfPercentage) {
		this.pfPercentage = pfPercentage;
	}
	public double getOtherDeductionsPercentage() {
		return otherDeductionsPercentage;
	}
	public void setOtherDeductionsPercentage(double otherDeductionsPercentage) {
		this.otherDeductionsPercentage = otherDeductionsPercentage;
	}
	@Override
	public String toString() {
		return "PayrollRule [taxPercentage=" + taxPercentage + ", pfPercentage=" + pfPercentage
				+ ", otherDeductionsPercentage=" + otherDeductionsPercentage + "]";
	}
       
       
}
