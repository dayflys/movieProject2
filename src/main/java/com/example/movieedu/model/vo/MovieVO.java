package com.example.movieedu.model.vo;

import java.io.Serializable;

public class MovieVO implements Serializable{
	private String movieNM;
    private String subtitle;
    private Integer rank;
	private Double userRating;
    private Integer pubDate;
    private String director;
    private String actor;
    private String imgUrl;
    
    
    public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
    public String getMovieNM() {
		return movieNM;
	}
	public void setMovieNM(String movieNM) {
		this.movieNM = movieNM;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Double getUserRating() {
		return userRating;
	}
	public void setUserRating(Double userRating) {
		this.userRating = userRating;
	}
	public Integer getPubDate() {
		return pubDate;
	}
	public void setPubDate(Integer pubDate) {
		this.pubDate = pubDate;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
    
}
