package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.repository.MenteeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenteeService {
    private final MenteeRepository menteeRepository;

    public MenteeService(MenteeRepository repository){
        this.menteeRepository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Mentee> searchById(Long id){
        return menteeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Mentee> searchByInterests(Set<String> interests){
        return menteeRepository.findByInterests(interests);
    }

    @Transactional
    public void deleteById(Long id){
        if (!menteeRepository.existsById(id)) {
            throw new RuntimeException("Mentorado com ID " + id + " não encontrado.");
        }
        menteeRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(){
        menteeRepository.deleteAll();
    }

}
