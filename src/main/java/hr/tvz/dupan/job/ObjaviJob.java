package hr.tvz.dupan.job;

import java.time.LocalDateTime;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import hr.tvz.dupan.data.PredavanjeRepository;
import hr.tvz.dupan.dodklase.Predavanje;

public class ObjaviJob extends QuartzJobBean{

	@Autowired
	PredavanjeRepository predavanjeRepository;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Iterable<Predavanje> predavanja = predavanjeRepository.findAll();
		
		StreamSupport.stream(predavanja.spliterator(), false)
			.filter(ObjaviJob::isNovoObjavljeno)
			.collect(Collectors.toList())
			.forEach(ObjaviJob::objaviPredavanje);
		
	}
	
	private static boolean isNovoObjavljeno(Predavanje predavanje) {
		
		return predavanje.isObjavljeno() && predavanje.getDatumUpisa()
												.isAfter(LocalDateTime.now()
												.minusSeconds(10));
	}
	
	private static void objaviPredavanje(Predavanje predavanje) {
		System.out.println("Uneseno je novo predavanje:" + predavanje.getTema() + ".");
	}
}
