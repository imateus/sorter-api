package io.github.imateus.sorterapi.controller;

import io.github.imateus.sorterapi.entity.Person;
import io.github.imateus.sorterapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@RequestBody Person person){
        return repository.save(person);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping
    public Page<Person> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return repository.findAll(pageRequest);
    }

    @PatchMapping("{id}/active")
    public void favorite(@PathVariable Integer id){
        Optional<Person> person = repository.findById(id);
        person.ifPresent(c -> {
            boolean active = c.getActive() == Boolean.TRUE;
            c.setActive(!active);
            repository.save(c);
        });
    }

    @PutMapping("{id}/picture")
    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("picture") Part file){
        Optional<Person> person = repository.findById(id);
        return person.map(c->{
            try {
                InputStream is = file.getInputStream();
                byte[] bytes = new byte[(int)file.getSize()];
                IOUtils.readFully(is, bytes);
                c.setPicture(bytes);
                repository.save(c);
                is.close();
                return bytes;
            }catch (IOException e){
                return null;
            }
        }).orElse(null);
    }
}
