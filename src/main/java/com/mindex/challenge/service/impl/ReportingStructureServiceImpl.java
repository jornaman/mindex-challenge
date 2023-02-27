package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.dao.ReportingStructureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Concrete implementations for ReportingStructureService methods
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    /**
     * Query in-memory HashMap for a ReportingStructure
     * @param employeeId
     * @return ReportingStructure
     */
    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Creating reporting structure with id [{}]", employeeId);

        ReportingStructure reportingStructure = ReportingStructureRepository.findByEmployeeId(employeeId);

        if (reportingStructure == null) {
            throw new RuntimeException("Invalid reporting structure employeeId: " + employeeId);
        }

        return reportingStructure;
    }
}
