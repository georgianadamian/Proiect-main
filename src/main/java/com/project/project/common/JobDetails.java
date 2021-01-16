/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.project.common;

/**
 *
 * @author Stefan
 */
public class JobDetails implements java.io.Serializable {
    
    private Integer id;
    
    private String jobType;
    
    private String firmName;
    
    private String username;

    public JobDetails(Integer id, String jobType, String firmName, String username) {
        this.id = id;
        this.jobType = jobType;
        this.firmName = firmName;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getJobType() {
        return jobType;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getUsername() {
        return username;
    }
    
    
}
