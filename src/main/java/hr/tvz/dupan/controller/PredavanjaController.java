package hr.tvz.dupan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import hr.tvz.dupan.data.PredavanjeRepository;
import hr.tvz.dupan.dodklase.Predavac;
import hr.tvz.dupan.dodklase.Predavanje;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@ControllerAdvice
@RequestMapping("/predavanja")
@SessionAttributes({"vrste", "pozicije","listaPredavanja"})
public class PredavanjaController {

	private final PredavanjeRepository predavanjeRepository;
	
	@Autowired
	public PredavanjaController(PredavanjeRepository predavanjeRepository) {
		this.predavanjeRepository = predavanjeRepository;
	}
	
	@GetMapping("/novo")
	public String showForm(Model model) {
		log.info("Punim podatke za formu i njen prikaz");
		
		model.addAttribute("predavanje", new Predavanje());
		model.addAttribute("vrste",Predavanje.Vrsta.values());
		model.addAttribute("pozicije",Predavac.Pozicija.values());
		
		return "predloziPredavanje";
	}
	
	@PostMapping("/novo")
	public String processForm(@Valid Predavanje predavanje,Errors errors,Model model) {
		log.info("Procesuiram predavanje:"+predavanje);
		
		if(errors.hasErrors()) {
			log.info("Predavanja ima grešku. Prekida se slanje");
			
			return "predloziPredavanje"; 
		}
		
		predavanjeRepository.save(predavanje);
		log.info("Predavanje je zaprimljeno!");
		
		@SuppressWarnings({ "unchecked" })
		//model.addAttribute("predavanje",predavanje);
		ArrayList<Predavanje> listaPredavanja=(ArrayList<Predavanje>) model.asMap().get("listaPredavanja");
		listaPredavanja.add(predavanje);
			
		return "prihvacenoPredavanje";
	}
	

	
	@ModelAttribute("listaPredavanja")
	public ArrayList<Predavanje> setlistaPredavanja(){
		return new ArrayList<Predavanje>();
	}
	
	@GetMapping("/resetirajBrojac")
	public String resetBrojac(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/predavanja/novo";
	}
	
	@GetMapping("/unesenaPredavanja")
	public String showClasses(Model model) {
		log.info("Prikazujem sva predavanja");
		
		model.addAttribute("popisPredavanja", predavanjeRepository.findAll());
		
		return "unesenaPredavanja";
	}
	@GetMapping("/pretraga")
	public String pretragaPredavanja(Model model) {
		model.addAttribute("predavanje", new Predavanje());
		
		return "pretragaPredavanja";
	}	
	
	@PostMapping("/pretraga")
	public String pretragaPredavanja(Predavanje predavanje, Model model) {
		List<Predavanje> dohvacenaPredavanja=predavanjeRepository.findByTema(predavanje.getTema());
		model.addAttribute("dohvacenaPredavanja", dohvacenaPredavanja);
		
		return "pretragaPredavanja";
	}
	
}
