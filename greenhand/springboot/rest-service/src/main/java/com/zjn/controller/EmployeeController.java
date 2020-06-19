package com.zjn.controller;

import com.zjn.component.assembler.EmployeeModelAssembler;
import com.zjn.dao.EmployeeRepository;
import com.zjn.exception.EmployeeNotFoundException;
import com.zjn.pojo.po.Employee;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.controller
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-18 11:48
 * @Description:
 */

@RestController
public class EmployeeController {
    private  final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;
    public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    //@GetMapping("/employees")
    //public CollectionModel<EntityModel<Employee>> all(){
    //
    //    List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
    //            .map(employee -> EntityModel.of(employee,
    //                    linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
    //                    linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
    //            .collect(Collectors.toList());
    //    return CollectionModel.of(employees,
    //            linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    //}
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all(){

        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> employeeEntityModel = employeeModelAssembler.toModel(employeeRepository.save(newEmployee));
        return ResponseEntity.created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

   // @GetMapping("/employees/{id}")
   //public EntityModel<Employee> one(@PathVariable Long id) {
   //
   //     Employee employee = employeeRepository.findById(id)
   //             .orElseThrow(() -> new EmployeeNotFoundException(id));
   //
   //     return EntityModel.of(employee,
   //             linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
   //             linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
   //     );
   // }
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeModelAssembler.toModel(employee);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeRepository.findById(id).ifPresentOrElse(
                x->employeeRepository.delete(x),
                ()->{throw new EmployeeNotFoundException(id);}
         );
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/employees/{id}")
    public  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee,@PathVariable Long id){
        Employee updateEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    //employee.setName(newEmployee.getName());
                    //employee.setRole(newEmployee.getRole());
                    BeanUtils.copyProperties(newEmployee,employee);
                    employee.setId(id);
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
        EntityModel<Employee> employeeEntityModel = employeeModelAssembler.toModel(updateEmployee);
        return ResponseEntity
                .created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

}
