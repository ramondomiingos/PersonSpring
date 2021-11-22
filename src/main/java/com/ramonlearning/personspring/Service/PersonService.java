package com.ramonlearning.personspring.Service;

import com.ramonlearning.personspring.dto.request.PersonDTO;
import com.ramonlearning.personspring.dto.response.MessageResponseDTO;
import com.ramonlearning.personspring.entity.Person;
import com.ramonlearning.personspring.exceptions.PersonNotFoundExeception;
import com.ramonlearning.personspring.mapper.PersonMapper;
import com.ramonlearning.personspring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    public MessageResponseDTO createPerson( PersonDTO personDTO){

        Person personToSave  = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("created person with id:" + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeple = personRepository.findAll();
        return allPeple.stream().map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundExeception {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundExeception(id));
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExeception {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }


    public void deleteById(Long id) throws PersonNotFoundExeception  {
       verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExeception  {
        Person person = verifyIfExists(id);
        Person personToUpdate =  personMapper.toModel(personDTO);
        Person UpdateddPerson = personRepository.save(personToUpdate);
        return MessageResponseDTO
                .builder()
                .message("Updated person with id: "+id)
                .build();

    }
}
