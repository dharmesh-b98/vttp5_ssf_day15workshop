package sg.edu.nus.iss.vttp5_ssf_day15workshop.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5_ssf_day15workshop.model.Person;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.repo.HashRepo;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.constant.*;

@Service
public class PersonService {
    
    @Autowired
    HashRepo personRepo;

    @Autowired
    DataService dataService;

    public Map<String,Person> getPersonMap() {
        Map<String,String> personStringMap = personRepo.entries(Constants.redisListName);
        Map<String, Person> personMap = new HashMap<>();
        for (Map.Entry<String,String> personStringEntry : personStringMap.entrySet()){
            String[] personStringSplit = personStringEntry.getValue().split(",");
            String name = personStringSplit[0];
            String email = personStringSplit[1];
            Integer phoneNumber = Integer.parseInt(personStringSplit[2]);
            Date dateOfBirth = new Date(Long.parseLong(personStringSplit[3]));
            Person person = new Person(name,email,phoneNumber,dateOfBirth);
            personMap.put(personStringEntry.getKey(),person);
        }

        return personMap;
    }


    public List<String> loadIds(){
        Map<String,String> personStringMap = personRepo.entries(Constants.redisListName);
        List<String> idList = new ArrayList<String>(personStringMap.keySet());
        return idList;
    }

    public void addPerson(Person person){
        String personString = person.toString();
        String hexCode = dataService.getHexCode();
        personRepo.put(Constants.redisListName, hexCode, personString);
    }

    public Boolean hasKey(String key){
        return personRepo.hasKey(Constants.redisListName, key);
    }

    public Person getPerson(String key){
        String[] personStringSplit = ((String)personRepo.get(Constants.redisListName, key)).split(",");
        String name = personStringSplit[0];
        String email = personStringSplit[1];
        Integer phoneNumber = Integer.parseInt(personStringSplit[2]);
        Date dateOfBirth = new Date(Long.parseLong(personStringSplit[3]));
        Person person = new Person(name,email,phoneNumber,dateOfBirth);
        return person;
    }
    
} 
