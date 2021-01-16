/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.project.ejb;

import com.project.project.common.JobDetails;
import com.project.project.entity.Job;
import com.project.project.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JobBean {

    private static final Logger LOG = Logger.getLogger(JobBean.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    public JobDetails findById(Integer jobId){
        Job job = em.find(Job.class, jobId);
        return new JobDetails(job.getId(), job.getJobType(), job.getFirmName(), job.getUser().getUsername());
    }
    
    public List<JobDetails> getAllJobs(){
        LOG.info("getAllJobs");
        
        try{
            Query query = em.createQuery("SELECT j FROM Job j");
            List<Job> jobs = (List<Job>) query.getResultList();
            return copyJobsToDetails(jobs);
        } catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    
    private List<JobDetails> copyJobsToDetails (List<Job> jobs){
        List<JobDetails> detailsList = new ArrayList<>();
        for(Job job : jobs){
            JobDetails jobDetails = new JobDetails(job.getId(),
                job.getJobType(),
                job.getFirmName(),
                job.getUser().getUsername());
            detailsList.add(jobDetails);
        }
        return detailsList;
    }
    
    public void createJob(String jobType, String frimName, Integer userId){
        LOG.info("createJob");
        Job job = new Job();
        job.setJobType(jobType);
        job.setFirmName(frimName);
        
        User user = em.find(User.class, userId);
        user.getJobs().add(job);
        job.setUser(user);
        
        em.persist(job);
    }

    public void updateJob(Integer jobId, String jobType, String firmName, Integer userId){
        LOG.info("updateJob");
        Job job = em.find(Job.class, jobId);
        job.setJobType(jobType);
        job.setFirmName(firmName);
        
        User oldUser = job.getUser();
        oldUser.getJobs().remove(job);
        
        User user = em.find(User.class, userId);
        user.getJobs().add(job);
        job.setUser(user);
    }

    public void deleteJobsByIds(List<Integer> ids) {
        LOG.info("deleteJobsByIds");
        for(Integer id : ids){
            Job job = em.find(Job.class, id);
            em.remove(job);
        }
    }
}
