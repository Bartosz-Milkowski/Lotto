package com.losowanie.lotto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LosowanieKontroler 
{	
	Losowanie los = new Losowanie();
	
	//obsługa strony początkowej
	@RequestMapping("/")
	  public String poczatek(Model model) {
	    return "start";
	  }
	
	//obsługa strony: start
	@RequestMapping("/start")
	  public String start(Model model) {
		los.reset();
	    model.addAttribute("los", los);
	    return "start";
	  }
	
	//obsługa strony: losowanie
	@RequestMapping("/losowanie")
	  public String loswanie(Model model) {
		los.los();
	    model.addAttribute("los", los);
	    return "losowanie";
	  }
	
	//obsługa strony: wyniki
	@RequestMapping("/wyniki")
	  public String raport(Model model) {
		los.kupon();
		los.sprawdz();
	    model.addAttribute("los", los);
	    return "wyniki";
	  }

}
