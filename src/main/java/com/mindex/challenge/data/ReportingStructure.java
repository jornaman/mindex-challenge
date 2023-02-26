package com.mindex.challenge.data;

import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee) {
        this.employee = employee;
        setNumberOfReports(this.employee.getDirectReports());
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    private void setNumberOfReports(List<Employee> directReports) {
        if (directReports == null || directReports.size() == 0)
            return;
        else {
            directReports.forEach( (emp) -> { setNumberOfReports(emp.getDirectReports());
                this.numberOfReports++;
            } );
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("ReportingStructure id: " + employee.getEmployeeId() + "  name: " + employee.getFirstName() + " " + employee.getLastName()  + "  nbrOfDirectReports: " + numberOfReports + "\n");
        return buf.toString();
    }
}
