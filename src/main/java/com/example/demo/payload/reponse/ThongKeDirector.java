package com.example.demo.payload.reponse;

import java.time.LocalDate;

public interface ThongKeDirector{
     LocalDate getEnd() ;
     LocalDate getStart();
     String getName();
     Long getHost_id();
     Long getTotalPrice();
     String getName_user_detail();
     String getEmail();

}