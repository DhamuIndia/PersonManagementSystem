package com.person.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.person.management.component.PersonManagementComponent;
import com.person.management.component.UIData;
import com.person.management.entity.Person;

@Controller
public class HomeController {
	
	 PersonManagementComponent personManagmentComponent;
	
	 @Autowired
	 public HomeController(PersonManagementComponent personManagmentComponent)
	 {
	   this.personManagmentComponent = personManagmentComponent;
	 }
	
	 //Home Screen Controller
	 @RequestMapping(value="/", method=RequestMethod.GET)
	 public String home(Model model) {
	   	UIData uiData = new UIData();
	    model.addAttribute("uidata",uiData );
	    return "home";
	 }   
	 
	 //Create Entry Screen
     @RequestMapping(value="/create-entry", method=RequestMethod.GET)
	 public String createEntry(@ModelAttribute UIData uiData, Model model) {	
	    model.addAttribute("uidata",uiData);
	    return "create";
	  }

      //Search Person Records
	  @RequestMapping(value="/search", method=RequestMethod.POST)
	  public String search(@ModelAttribute UIData uiData, Model model) {
		Person person= personManagmentComponent.searchPerson(uiData.getSearchQuery().getId(),uiData.getSearchQuery().getFirstName(), uiData.getSearchQuery().getLastName(), uiData.getSearchQuery().getAge());
		if (person ==null)
		{
			model.addAttribute("message", "No Data Found");
			return "confirm";
		}
		uiData.setPerson(person);
		model.addAttribute("uidata", uiData);
	    return "result";
	   }
	  
  	    //save Records in H2 Database
	 	@RequestMapping(value="/save", method=RequestMethod.POST)
	     public String save(@ModelAttribute UIData uiData, Model model) {
			Person person= new Person(uiData.getPerson().getFirstName(), uiData.getPerson().getLastName(), uiData.getPerson().getAge(), uiData.getPerson().getFavoriteColor(),
			uiData.getPerson().getHobby());
			//Check if duplicate record exists with same name and age.
			if(!personManagmentComponent.checkDuplicatePerson(person))
			{
			Long id = personManagmentComponent.savePerson(person);
			model.addAttribute("message", "Person Data Saved Successfully. Id Number is "+ id);
			}
			else
			{
				model.addAttribute("message", "Person Record Already Exist");
			}
	        return "confirm";
	   }
	 	
	 	//Update Person Record
	 	@RequestMapping(value="/update", method=RequestMethod.POST)
	    public String updatePerson(
	    	   @ModelAttribute UIData uidata,
	 		   Model model) {
	 		   personManagmentComponent.updatePerson(uidata.getPerson().getId(),uidata.getPerson().getFirstName(),
	 				   uidata.getPerson().getLastName(),uidata.getPerson().getAge(),uidata.getPerson().getFavoriteColor(),uidata.getPerson().getHobby());
	    	   model.addAttribute("message","Update Successfull");
	        return "confirm"; 
	    }
	 	
	 	//Delete Record
	 	@RequestMapping(value="/delete", method=RequestMethod.POST)
	    public String deletePerson(
	    	 @ModelAttribute UIData uidata,
	 		   Model model) {
	 		   personManagmentComponent.deletePerson(uidata.getPerson().getId());
	    	   model.addAttribute("message","Deleted Successfully");
	        return "confirm"; 
	    }

}
