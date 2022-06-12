package com.abbas.coronavirustracker.models;

public class LocationsStat {

	 private String country;
	 private String latestTotalCases;
	 
	 public void setCountry(String country) {
		 this.country = country;
	 }
	 public String getCountry() {
		 return country;
	 }
	 public String getLatestCases() {
		 return latestTotalCases;
	 }
	 public void setLatestCases(String latestTotalCases) {
		 this.latestTotalCases = latestTotalCases;
	 }
@Override
public String toString() {
	return "LocationsStat [country=" + country + ", latestTotalCases=" + latestTotalCases + "]";
} 
}
	
