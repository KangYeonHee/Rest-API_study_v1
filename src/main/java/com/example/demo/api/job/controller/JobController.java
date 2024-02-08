package com.example.demo.api.job.controller;

import ch.qos.logback.core.status.Status;
import com.example.demo.api.job.service.JobService;
import com.example.demo.api.response.status.StatusCode;
import com.example.demo.api.response.status.StatusEntity;
import com.example.demo.api.job.vo.JobVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class JobController {
    private JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping ("/jobs")
    public StatusEntity<List<JobVO>> getJobs(
            @RequestParam(value = "name") String name
    ) {
        List<JobVO> list = service.getJobList(name);
        return new StatusEntity<>(list, StatusCode.OK);
    }

    @GetMapping ("/jobs/{id}")
    public StatusEntity<JobVO> getJobById(@PathVariable int id) {
        JobVO vo = service.getJobById(id);
        StatusEntity<JobVO> result = new StatusEntity<>(vo, StatusCode.OK);
        return result;
    }

    @PostMapping("/jobs")
    public StatusEntity<Integer> insertJob(@RequestBody JobVO job) {
        int result = service.insertJob(job);
        StatusCode code = result > 0 ? StatusCode.OK : StatusCode.OVERLAP;
        return new StatusEntity<>(result, code);
    }

    @PutMapping("/jobs/{id}")
    public StatusEntity<JobVO> updateJob(@PathVariable int id, @RequestParam String name, @RequestParam int income) {
        JobVO vo = service.updateJob(id, name, income);
        StatusCode code = vo != null? StatusCode.OK : StatusCode.OVERLAP;
        return new StatusEntity<>(vo, code);
    }

    @DeleteMapping("/jobs/{id}")
    public StatusEntity<Integer> deleteJob(@PathVariable int id) {
        int result = service.deleteJob(id);
        return new StatusEntity<>(result, StatusCode.OK);
    }
}
