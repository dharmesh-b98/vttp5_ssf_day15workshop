package sg.edu.nus.iss.vttp5_ssf_day15workshop.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.model.Person;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.service.DataService;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.service.PersonService;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    DataService dataservice;

    @Autowired
    PersonService personservice;


    @GetMapping("")
    public String getHome(Model model) {
        //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" + dataDir);
        //System.out.println("\n\n\n\n\n\n\n\n");
        return "home";
    }
    
    @GetMapping("/contact")
    public String getContact(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "personcreate";
    }

    @PostMapping("/contact")
    public String postContact(@Valid @ModelAttribute Person person, BindingResult result, Model model) throws IOException {

        if(result.hasErrors()){
            return "personcreate";
        }

        //Global error example
        /* if (dataservice.isAgeWithinRange(person.getDateOfBirth()) == false){
            ObjectError err = new ObjectError("globalError", "Age needs to be between 10 and 100 years old");
            result.addError(err);
            return "personcreate";
        } */

        personservice.addPerson(person);
        System.out.println ("person created");
        return "redirect:/";
        //return HttpStatus.CREATED + "Person " + person.getName() + " is successfully created";
    }


    @GetMapping("/contact/{id}")
    public String getContactPerson(@PathVariable String id, Model model) throws IOException {
        
        if (personservice.hasKey(id)){
            Person person = personservice.getPerson(id);
            model.addAttribute("person", person);
            return "personinfo";
        }
        else{
            //return HttpStatus.NOT_FOUND + ": could not find file !! "; //returning HTTPS_STATUS 
            return "cannotfind";
        }
        
    }

    @GetMapping("/contacts")
    public String getContacts( Model model) throws IOException {
        List<String> idList = personservice.loadIds();
        model.addAttribute("idList", idList);
        return "contacts";
        
    }
    
    
    

    
}


