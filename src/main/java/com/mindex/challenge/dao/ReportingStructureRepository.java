package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

/**
 * ReportingStructure is not persisted, creates in-memory HashMap of ReportingStructure
 */
@Repository
public class ReportingStructureRepository {
    //private static Map<String, JSON> reportingStructures = new HashMap<String, JSON>();
    static Map<String, ReportingStructure> reportingStructures = new HashMap<String, ReportingStructure>();

    /**
     * try and retrieve a ReportingStructure from HashMap
     * @param employeeId
     * @return ReportingStructure
     */
    public static ReportingStructure findByEmployeeId(String employeeId) {
        ReportingStructure reportingStructure = (ReportingStructure)reportingStructures.get(employeeId);

        if (reportingStructure == null) {
            throw new RuntimeException("Invalid reporting structure employeeId: " + employeeId);
        }
        return reportingStructure;
    }

    /**
     * add a ReportingStructure from HashMap
     * @param reportingstructure
     */
    public static void addReportingStructure(ReportingStructure reportingstructure) { reportingStructures.put(reportingstructure.getEmployeeId(), reportingstructure); }

    /**
     * delete a ReportingStructure from HashMap
     * @param reportingstructure
     */
    public static void deleteReportingStructure(ReportingStructure reportingstructure) { reportingStructures.remove(reportingstructure.getEmployeeId()); }

}
