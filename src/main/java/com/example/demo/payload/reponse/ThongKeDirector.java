package com.example.demo.payload.reponse;

import com.example.demo.entity.User;

import java.time.LocalDate;
import java.util.Date;

public interface ThongKeDirector{
     LocalDate getEnd() ;
     LocalDate getStart();
     String getName();
     Long getHost_id();
     Long getTotalPrice();
     String getName_user_detail();
     String getEmail();


}