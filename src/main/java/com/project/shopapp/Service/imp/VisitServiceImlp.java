package com.project.shopapp.Service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.VisitService;
import com.project.shopapp.entity.Visit;
import com.project.shopapp.repository.VisitDAO;

/**
 * VisitServiceImlp
 */
@Service
public class VisitServiceImlp implements VisitService {
    @Autowired
    private VisitDAO visitDAO;

    @Override
    public Visit createVisit(Visit visit) {
        return visitDAO.save(visit);
    }

}