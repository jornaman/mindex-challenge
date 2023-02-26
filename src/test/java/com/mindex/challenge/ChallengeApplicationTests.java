package com.mindex.challenge;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeApplicationTests {

	private String employeeUrl;
	private String employeeIdUrl;
	private String reportingStructureUrl;
	private String reportingStructureIdUrl;

	@Autowired
	private EmployeeService employeeService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		employeeUrl = "http://localhost:" + port + "/employee";
		employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
		reportingStructureUrl = "http://localhost:" + port + "/reportingstructure";
		reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
	}

	@Test
	public void contextLoads() {
		Employee employee1 = new Employee();
		employee1.setFirstName("Terry");
		employee1.setLastName("Pegula");
		employee1.setDepartment("Buffalo Bills");
		employee1.setPosition("Owner/CEO");
		// Create checks
		Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, employee1, Employee.class).getBody();

		Employee employee2 = new Employee();
		employee2.setFirstName("Sean");
		employee2.setLastName("McDermott");
		employee2.setDepartment("Coaches");
		employee2.setPosition("Head Coach");
		// Create checks
		Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, employee2, Employee.class).getBody();

		Employee employee3 = new Employee();
		employee3.setFirstName("Leslie");
		employee3.setLastName("Frazier");
		employee3.setDepartment("Coaches");
		employee3.setPosition("Defensive Coordinator");
		// Create checks
		Employee createdEmployee3 = restTemplate.postForEntity(employeeUrl, employee3, Employee.class).getBody();

		Employee employee4 = new Employee();
		employee4.setFirstName("Ken");
		employee4.setLastName("Dorsey");
		employee4.setDepartment("Coaches");
		employee4.setPosition("Offensive Coordinator");
		// Create checks
		Employee createdEmployee4 = restTemplate.postForEntity(employeeUrl, employee4, Employee.class).getBody();

		Employee employee5 = new Employee();
		employee5.setFirstName("Matthew");
		employee5.setLastName("Smiley");
		employee5.setDepartment("Coaches");
		employee5.setPosition("Special Teams Coordinator");
		// Create checks
		Employee createdEmployee5 = restTemplate.postForEntity(employeeUrl, employee5, Employee.class).getBody();

		Employee employee6 = new Employee();
		employee6.setFirstName("Joe");
		employee6.setLastName("Brady");
		employee6.setDepartment("Coaches");
		employee6.setPosition("Quarterbacks");
		// Create checks
		Employee createdEmployee6 = restTemplate.postForEntity(employeeUrl, employee6, Employee.class).getBody();

		List<Employee>  ce4 = new ArrayList<Employee>();
		ce4.add(createdEmployee6);
		createdEmployee4.setDirectReports(ce4);

		List<Employee>  ce2 = new ArrayList<Employee>();
		ce2.add(createdEmployee3);
		ce2.add(createdEmployee4);
		ce2.add(createdEmployee5);
		createdEmployee2.setDirectReports(ce2);

		List<Employee>  ce1 = new ArrayList<Employee>();
		ce1.add(createdEmployee2);
		createdEmployee1.setDirectReports(ce1);

		ReportingStructure rs1 = new ReportingStructure(createdEmployee1);
		assertEquals(5, rs1.getNumberOfReports());
		System.out.println("rs1: " + rs1.toString());

		ReportingStructure rs2 = new ReportingStructure(createdEmployee2);
		assertEquals(4, rs2.getNumberOfReports());
		System.out.println("rs2: " + rs2.toString());

		ReportingStructure rs5 = new ReportingStructure(createdEmployee5);
		assertEquals(0, rs5.getNumberOfReports());
		System.out.println("rs5: " + rs5.toString());

	}

}
