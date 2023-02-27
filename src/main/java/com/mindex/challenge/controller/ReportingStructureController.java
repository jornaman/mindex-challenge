package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.dao.ReportingStructureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling reportingstructure rest calls
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);
    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * read method to pull a ReportingStructure from in-memory ReportingStructure HashMap
     * @param employeeId
     * @return ReportingStructure
     */
    @GetMapping("/reportingstructure/{employeeId}")
    public ReportingStructure read(@PathVariable String employeeId) {
        LOG.debug("Received reporting strucure read request for id [{}]", employeeId);

        return ReportingStructureRepository.findByEmployeeId(employeeId);
    }

}
