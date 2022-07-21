package io.github.imateus.sorterapi.service;

import io.github.imateus.sorterapi.entity.Person;
import io.github.imateus.sorterapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public Person save(Person person){
        person.setId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));
        return repository.save(person);
    }

    public Optional<Person> findById(Integer id) {
        return repository.findById(id);
    }

    public Page<Person> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
