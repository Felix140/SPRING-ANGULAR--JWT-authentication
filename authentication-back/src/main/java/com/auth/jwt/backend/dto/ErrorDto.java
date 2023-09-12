package com.auth.jwt.backend.dto;

//uso il record perche: 
//1- IMMUTABILE
//2- Usato solo per RICEVERE
//3- I campi del record rappresentano un OGGETTO DI DATI, serializzabili

public record ErrorDto(String message) {

}
