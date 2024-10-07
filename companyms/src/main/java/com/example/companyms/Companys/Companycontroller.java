package com.example.companyms.Companys;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class Companycontroller {
    private Companyservice companyservice;

    public Companycontroller(Companyservice companyservice) {
        this.companyservice = companyservice;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getallcompanies(){
        return ResponseEntity.ok(companyservice.getallcompanies());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatecompany(@RequestBody Company company,@PathVariable Long id) {
        companyservice.updatecompany(company, id);
        return new ResponseEntity<>("updated successfully",HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> Createcompany(@RequestBody Company company){
        companyservice.createcompany(company);
        return new ResponseEntity<>("company added successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id){
        boolean isdeleted=companyservice.deletecompanybyid(id);
        if(isdeleted){
            return new ResponseEntity<>("company deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getcompany(@PathVariable Long id){
        Company company=companyservice.getcompanybyid(id);
        if(company!=null)
            return new ResponseEntity<>(company,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
