package com.mindex.challenge.data;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data bean for Compensation, yes I added employeeId as class variable
 * I couldn't get query to work using employee.employeeId
 */
public class Compensation {
    private String employeeId;
    private Employee employee;
    private Double salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    //public String toString() {
    //    StringBuffer buf = new StringBuffer();
    //    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    //    buf.append("\nCompensation id: " + employee.getEmployeeId() + " name: " + employee.getFirstName() + " " + employee.getLastName() + "\n");
    //    buf.append("   position: " + employee.getPosition() + " dept: " + employee.getDepartment() + "\n");
    //    buf.append(" salary: " + formatter.format(getSalary()) + " effective date: " + simpleDateFormat.format(getEffectiveDate()) + "\n");
    //    return buf.toString();
    //}

}
