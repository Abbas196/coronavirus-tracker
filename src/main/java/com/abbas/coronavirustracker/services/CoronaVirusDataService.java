package com.abbas.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.abbas.coronavirustracker.models.LocationsStat;

// when we want to use a class as a spring service then we have to make use of the annotation @service.
@Service
public class CoronaVirusDataService {
private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/datasets/covid-19/main/data/worldwide-aggregate.csv";
private List<LocationsStat> statistics = new ArrayList<>();
public List<LocationsStat> getStatistics() {
	return statistics;
}

public void setStatistics(List<LocationsStat> statistics) {
	this.statistics = statistics;
}
@PostConstruct
@Scheduled(fixedDelay = 30000)
public void fetchVirusData() throws IOException, InterruptedException,NullPointerException {
	List<LocationsStat> newstats = new ArrayList<>();
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
	HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
    StringReader csvBodyReader = new StringReader(response.body());
	Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
	for (CSVRecord record : records) {
		LocationsStat stat = new LocationsStat();
		stat.setCountry(record.get("Date"));
		stat.setLatestCases(record.get(record.size()-4));
		System.out.println(stat);
		newstats.add(stat);
	}
	this.statistics = newstats;
}
}