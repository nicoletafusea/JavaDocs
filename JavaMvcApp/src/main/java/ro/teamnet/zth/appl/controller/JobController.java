package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.DepartmentService;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by nicoleta on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    private final JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name = "id") String id) {
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/create", methodType = "POST")
    public void addJob(@MyRequestObject Job job) {
        jobService.addJob(job);
    }

    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteJob(@MyRequestParam(name = "id") String id) {
        jobService.deleteJob(id);
    }


}
