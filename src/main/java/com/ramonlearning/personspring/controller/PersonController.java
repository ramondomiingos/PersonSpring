package com.ramonlearning.personspring.controller;
import com.ramonlearning.personspring.Service.PersonService;
import com.ramonlearning.personspring.dto.request.PersonDTO;
import com.ramonlearning.personspring.dto.response.MessageResponseDTO;

import com.ramonlearning.personspring.exceptions.PersonNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {

        return personService.createPerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> getAll(){
       return  personService.listAll();
    }

    @RequestMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundExeception {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO )throws PersonNotFoundExeception {
        return personService.updateById(id,personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID(@PathVariable Long id) throws PersonNotFoundExeception {
        personService.deleteById(id);
    }

}
