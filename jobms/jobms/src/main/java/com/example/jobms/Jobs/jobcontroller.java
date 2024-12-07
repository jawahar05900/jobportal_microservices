package com.example.jobms.Jobs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class jobcontroller {
    Jobservice jobservice;

    public jobcontroller(Jobservice jobservice) {
        this.jobservice = jobservice;
    }

    @GetMapping("/job")
    public ResponseEntity<List<Job>> findall(){
        return ResponseEntity.ok(jobservice.find());
    }

    @PostMapping("/job")
    public ResponseEntity<String> createall(@RequestBody Job job){
        jobservice.create(job);
        return new ResponseEntity<>("jobadded success", HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getjobbyid(@PathVariable Long id){
        Job job=jobservice.getjobbyid(id);
        if(job!=null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deletejobbyid(@PathVariable long id){
        boolean delete=jobservice.deletejob(id);
        if(delete)
            return new ResponseEntity<>("job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("error",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/job/{id}")
    public ResponseEntity<String> update(@PathVariable long id,@RequestBody Job updatedjob){
        boolean update=jobservice.updatejobbyid(id,updatedjob);
        if(update)
            return new ResponseEntity<>("job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>("job not updated successfully",HttpStatus.NOT_FOUND);
    }

}