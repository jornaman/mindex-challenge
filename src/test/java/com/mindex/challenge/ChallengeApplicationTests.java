package com.mindex.challenge;

import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.impl.EmployeeServiceImplTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeApplicationTests {

	private String employeeUrl;
	private String employeeIdUrl;
	private String reportingStructureUrl;
	private String reportingStructureIdUrl;
	private String compensationUrl;
	private String compensationIdUrl;

	@Autowired
	private EmployeeService employeeService;
	private ReportingStructureService reportingStructureService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		employeeUrl = "http://localhost:" + port + "/employee";
		employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
		reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
		compensationUrl = "http://localhost:" + port + "/compensation";
		compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
	}

	@Test
	public void contextLoads() {
		Employee employee1 = new Employee();
		employee1.setFirstName("Terry");
		employee1.setLastName("Pegula");
		employee1.setDepartment("Buffalo Bills");
		employee1.setPosition("Owner/CEO");
		// Create employee
		Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, employee1, Employee.class).getBody();

		Employee employee2 = new Employee();
		employee2.setFirstName("Sean");
		employee2.setLastName("McDermott");
		employee2.setDepartment("Coaches");
		employee2.setPosition("Head Coach");
		// Create employee
		Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, employee2, Employee.class).getBody();

		Employee employee3 = new Employee();
		employee3.setFirstName("Leslie");
		employee3.setLastName("Frazier");
		employee3.setDepartment("Coaches");
		employee3.setPosition("Defensive Coordinator");
		// Create employee
		Employee createdEmployee3 = restTemplate.postForEntity(employeeUrl, employee3, Employee.class).getBody();

		Employee employee4 = new Employee();
		employee4.setFirstName("Ken");
		employee4.setLastName("Dorsey");
		employee4.setDepartment("Coaches");
		employee4.setPosition("Offensive Coordinator");
		// Create employee
		Employee createdEmployee4 = restTemplate.postForEntity(employeeUrl, employee4, Employee.class).getBody();

		Employee employee5 = new Employee();
		employee5.setFirstName("Matthew");
		employee5.setLastName("Smiley");
		employee5.setDepartment("Coaches");
		employee5.setPosition("Special Teams Coordinator");
		// Create employee
		Employee createdEmployee5 = restTemplate.postForEntity(employeeUrl, employee5, Employee.class).getBody();

		Employee employee6 = new Employee();
		employee6.setFirstName("Joe");
		employee6.setLastName("Brady");
		employee6.setDepartment("Coaches");
		employee6.setPosition("Quarterbacks");
		// Create employee
		Employee createdEmployee6 = restTemplate.postForEntity(employeeUrl, employee6, Employee.class).getBody();

		// Create directReports list
		List<Employee>  ce4 = new ArrayList<Employee>();
		ce4.add(createdEmployee6);
		createdEmployee4.setDirectReports(ce4);

		// Create directReports list
		List<Employee>  ce2 = new ArrayList<Employee>();
		ce2.add(createdEmployee3);
		ce2.add(createdEmployee4);
		ce2.add(createdEmployee5);
		createdEmployee2.setDirectReports(ce2);

		// Create directReports list
		List<Employee>  ce1 = new ArrayList<Employee>();
		ce1.add(createdEmployee2);
		createdEmployee1.setDirectReports(ce1);

		// Create ReportingStructure, verify number of reports count
		ReportingStructure rs1 = new ReportingStructure(createdEmployee1);
		assertEquals(5, rs1.getNumberOfReports());
		System.out.println("rs1: " + rs1.toString());
		ReportingStructureRepository.addReportingStructure(rs1);

		// Create ReportingStructure, verify number of reports count
		ReportingStructure rs2 = new ReportingStructure(createdEmployee2);
		assertEquals(4, rs2.getNumberOfReports());
		System.out.println("rs2: " + rs2.toString());
		ReportingStructureRepository.addReportingStructure(rs2);

		// Create ReportingStructure, verify number of reports count
		ReportingStructure rs5 = new ReportingStructure(createdEmployee5);
		assertEquals(0, rs5.getNumberOfReports());
		System.out.println("rs5: " + rs5.toString());
		ReportingStructureRepository.addReportingStructure(rs5);

		Compensation compensation1 = new Compensation();
		compensation1.setEmployee(createdEmployee1);
		compensation1.setEmployeeId(createdEmployee1.getEmployeeId());
		compensation1.setSalary(3500555.00);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String effDate = "08/17/1964";
		Date date = null;
		try {
			date = simpleDateFormat.parse(effDate);
		} catch(java.text.ParseException pe) {
			throw new RuntimeException("Invalid Compensation effective date: " + createdEmployee1.getEmployeeId() + "  " + effDate);
		}
		compensation1.setEffectiveDate(date);

		// Create compensations
		Compensation createdCompensation1 = restTemplate.postForEntity(compensationUrl, compensation1, Compensation.class).getBody();
		//System.out.println("compensation1: " + createdCompensation1.toString());

		Compensation compensation2 = new Compensation();
		compensation2.setEmployee(createdEmployee4);
		compensation2.setEmployeeId(createdEmployee4.getEmployeeId());
		compensation2.setSalary(1250000.00);
		effDate = "05/24/2014";
		date = null;
		try {
			date = simpleDateFormat.parse(effDate);
		} catch(java.text.ParseException pe) {
			throw new RuntimeException("Invalid Compensation effective date: " + createdEmployee4.getEmployeeId() + "  " + effDate);
		}
		compensation2.setEffectiveDate(date);

		// Create compensations
		Compensation createdCompensation2 = restTemplate.postForEntity(compensationUrl, compensation2, Compensation.class).getBody();
		//System.out.println("compensation2: " + createdCompensation2.toString());

		assertNotNull(createdCompensation2.getEmployeeId());

		// Read checks
		Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation2.getEmployeeId()).getBody();
		assertEquals(createdCompensation2.getEmployeeId(), readCompensation.getEmployeeId());
		assertCompensationEquivalence(createdCompensation2, readCompensation);

		// Read checks
		//ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, rs2.getEmployeeId()).getBody();
		//ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, rs2.getEmployee().getEmployeeId()).getBody();
		//assertEquals(rs5.getEmployeeId(), readReportingStructure.getEmployeeId());
		//assertEmployeeEquivalence(rs2.getEmployee(), readReportingStructure.getEmployee());
		//assertEquals(rs2.getNumberOfReports(), readReportingStructure.getNumberOfReports());

	}

	private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getDepartment(), actual.getDepartment());
		assertEquals(expected.getPosition(), actual.getPosition());
	}

	private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
		assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
		assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
		assertEquals(expected.getSalary(), actual.getSalary());
		assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
	}

}
