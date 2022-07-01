package it.polito.tdp.Yelp.model;

import java.time.LocalDate;

public class Review {
	
	//ATTRIBUTI:
	//specifici della Rewiew. Ossia non chiavi esterne.
	
	//Se la classe Review avesse come possibili attributi
	//solo le chiavi esterne corrispondenti a Business e User,
	//non bisognerebbe crearla!!
	
	private String reviewId;
	private Business business;  // private String businessId
	private User user;
	private double stars;
	private LocalDate reviewDate;
	private int votesFunny;
	private int votesUseful;
	private int votesCool;
	private String reviewText;

	
	
}
