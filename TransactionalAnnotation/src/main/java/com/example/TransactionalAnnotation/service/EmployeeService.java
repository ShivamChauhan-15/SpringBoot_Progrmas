package com.example.TransactionalAnnotation.service;

import com.example.TransactionalAnnotation.dto.EmployeeAddressDto;
import com.example.TransactionalAnnotation.model.Address;
import com.example.TransactionalAnnotation.model.Employee;
import com.example.TransactionalAnnotation.repository.EmployeeRepository;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager;
    //adding employees in employee table
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee addEmployee(){
        Employee emp1=new Employee();
        emp1.setName("Rahul");
        emp1.setAge(24);


        //2nd Employee
        Employee emp2=new Employee();
        emp2.setName("Mohit");
        emp2.setAge(28);

        //creating address instance
        Address address1=new Address();
        address1.setCity("Noida");
        address1.setState("UP");
        address1.setEmployee(emp1);
        emp1.setAddress(address1);
        employeeRepository.save(emp1);

        //create 2nd address instance
        Address address2=new Address();
        address2.setCity("Ghaziabad");
        address2.setState("UP");
        address2.setEmployee(emp2);
        emp2.setAddress(address2);
        employeeRepository.save(emp2);

        return null;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateAge(String id){
        Employee emp = employeeRepository.getById(Integer.valueOf(id));
        emp.setAge(45);
        this. getAge( id);
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        this.entityManager.detach(emp);
//        employeeRepository.save(emp);
        return emp.getAge();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public int getAge(String id){
        Employee emp = employeeRepository.getById(Integer.valueOf(id));
        System.out.println("age= "+emp.getAge());
        return emp.getAge();
    }

    @Transactional
    public String remove(String id){
        Optional<Employee> byId = employeeRepository.findById(Integer.valueOf(id));
        if(byId.isPresent()){
            Employee employee = byId.get();
            employeeRepository.delete(employee);
            return "Deleted Successfully";
        }
        return "Not Found";
    }

    public List<?> fetchData(){
        //Using Native Query
        String query = "SELECT e.name AS name, e.age AS age, a.city AS city, a.state AS state " +
                "FROM employee e " +
                "JOIN address a ON e.id = a.employee_id";
        Query query1 = entityManager.createNativeQuery(query)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(EmployeeAddressDto.class));
        List<EmployeeAddressDto> resultList = query1.list();
        System.out.println(resultList);
        for(EmployeeAddressDto emp:resultList){
            System.out.println(emp.getName()+" "+emp.getAge()+" "+emp.getCity()+" "+emp.getState());
        }

        //Using Named Query
//        Query query2 = entityManager.createNamedQuery("Employee.findAll")
//                .unwrap(org.hibernate.query.Query.class)
//                .setResultTransformer(ToListResultTransformer.INSTANCE);
//        List<?> resultList = query2.list();
//        System.out.println(resultList);

//        for (Employee e: list){
//            System.out.println(o.getClass().getName());
//            if(o instanceof Employee){
//                Employee e=(Employee)o;
//                System.out.println(e.getId()+" "+e.getName()+" "+e.getAge());
//                }

        return resultList;

    }

}
