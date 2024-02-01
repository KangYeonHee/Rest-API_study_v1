package com.example.demo.config.api.job.controller;

import com.example.demo.config.api.job.vo.JobVO;
import com.example.demo.config.api.response.status.StatusCode;
import com.example.demo.config.api.response.status.StatusEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class JobController {
    private List<JobVO> jobs = new ArrayList<JobVO>();

    public JobController() {
        jobs.add(new JobVO(0, "PD", 7800));
        jobs.add(new JobVO(6, "ACTOR", 10000));
        jobs.add(new JobVO(7, "POLICE", 4000));
    }

    @GetMapping ("/jobs")
    public List<JobVO> getJobs() { return jobs;}

    @GetMapping ("/jobs/{id}")
    public StatusEntity<JobVO> getJobById(@PathVariable int id) {
        return jobs.stream()
              .filter(x -> x.getId() == id)
              .map(x -> new StatusEntity<>(x, StatusCode.OK))
              .findAny()
              .orElseGet(() -> new StatusEntity<>(StatusCode.NO_DATA));
    }

    @PostMapping("/jobs")
    public StatusEntity<JobVO> postJob(@RequestBody JobVO job) throws Exception {
        for(JobVO j : jobs) {
            if(job.getId() == j.getId()) {
                throw new Exception();
            }
        }

        jobs.add(job);
        return new StatusEntity<>(job, StatusCode.OK);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobVO> putJob(@PathVariable int id, @RequestBody JobVO job) throws Exception {
        for(JobVO j : jobs) {
            if(j.getId() == id) {
                int jobIndex = jobs.indexOf(j);
                jobs.set(jobIndex, new JobVO(id, job.getName(), job.getIncome()));
                JobVO resultJob = jobs.get(jobIndex);
                return new ResponseEntity<>(
                    resultJob, HttpStatus.OK
                );
            }
        }

        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable int id) throws Exception {
        for(JobVO j : jobs) {
            if(j.getId() == id) {
                jobs.remove(j);
                return "Delete Success";
            }
        }

        throw new Exception();
    }
}
