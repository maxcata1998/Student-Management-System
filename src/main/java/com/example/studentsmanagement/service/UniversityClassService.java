package com.example.studentsmanagement.service;

import com.example.studentsmanagement.dao.UniversityClassDao;
import com.example.studentsmanagement.exceptions.invalidUniversityClassException;
import com.example.studentsmanagement.model.UniversityClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UniversityClassService {

  UniversityClassDao universityClassDao;

  @Autowired
  public UniversityClassService(UniversityClassDao universityClassDao) {
    this.universityClassDao = universityClassDao;
  }

  public List<UniversityClass> getAllClasses(){
    return (List<UniversityClass>) universityClassDao.findAll();
  }

  public UniversityClass addClass(UniversityClass universityClass){
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    if(universityClass.getYear()<currentYear){
      throw new invalidUniversityClassException("Cannot add class in the past");
    }
    if(universityClass.getYear()>currentYear+1){
      throw new invalidUniversityClassException("cannot add class in the future");
    }

    return universityClassDao.save(universityClass);
  }
}
