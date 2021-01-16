/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.project.ejb;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Stefan
 */
@Stateful
@SessionScoped
public class InvoiceBean {

    private Set<Integer> userIds = new HashSet<Integer>();
    
    public Set<Integer> getUserIds(){
        return userIds;
    }
    
    public void setUserIds(Set<Integer> userIds){
        this.userIds = userIds;
    }
}
