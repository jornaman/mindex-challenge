package com.mindex.challenge.data;

import java.io.Serializable;
import java.util.List;

/**
 * Data bean for ReportingStructure, yes I added employeeId as class variable
 * I couldn't get query to work using employee.employeeId
 */
public class ReportingStructure implements Serializable {
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public ReportingStructure(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employee = employee;
    }

    public String getEmployeeId() { return employeeId; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        this.numberOfReports = 0;
        if (employee != null) { setNumberOfReports(employee.getDirectReports()); }
        return numberOfReports;
    }

    /**
     * recursively traverses the directReports to derive the numberOfReports
     * @param directReports
     */
    private void setNumberOfReports(List<Employee> directReports) {
        if (directReports == null || directReports.size() == 0)
            return;
        else {
            directReports.forEach( (emp) -> { setNumberOfReports(emp.getDirectReports());
                this.numberOfReports++;
            } );
        }
    }

    /**
     * overriding equals to be ReportingStructures having the same employeeId
     * and numberOfReports
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ReportingStructure) {
            ReportingStructure rs = (ReportingStructure) obj;
            if (this.employeeId == rs.employeeId &&
                this.getNumberOfReports() == rs.getNumberOfReports()) {
                result = true;
            }
        }
        return result;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("ReportingStructure id: " + employee.getEmployeeId() + "  name: " + employee.getFirstName() + " " + employee.getLastName()  + "  nbrOfDirectReports: " + numberOfReports + "\n");
        return buf.toString();
    }
}
