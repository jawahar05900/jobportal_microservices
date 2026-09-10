package com.example.jobms.Jobs.impl;

import com.example.jobms.Jobs.Job;
import com.example.jobms.Jobs.JobRepository;
import com.example.jobms.Jobs.Jobservice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class jobserviceimpl implements Jobservice {

    JobRepository jobRepository;

    public jobserviceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> find() {
        return jobRepository.findAll();
    }
    @Override
    public void create(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getjobbyid(long id) {
        return jobRepository.findById(id).orElse(null);
    }


    public boolean deletejob(long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public boolean updatejobbyid(long id, Job updatedjob) {

        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job jobs=jobOptional.get();
            jobs.setTitle(updatedjob.getTitle());
            jobs.setDescription(updatedjob.getDescription());
            jobs.setMinsalary(updatedjob.getMinsalary());
            jobs.setMaxsalary(updatedjob.getMaxsalary());
            jobs.setLocation(updatedjob.getLocation());
            jobRepository.save(jobs);
            return true;


        }
        return false;
    }
}



