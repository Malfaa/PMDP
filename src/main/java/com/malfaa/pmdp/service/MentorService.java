package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.repository.MentorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository repository){
        this.mentorRepository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Mentor> searchById(Long id){
        return mentorRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Mentor> searchByAcademicFormation(String formacao){
        //todo adicionar algum tipo de filtro, mesmo que escreve pela metade ou algo do tipo
        return mentorRepository.findByAcademicFormation(formacao);
    }

    @Transactional(readOnly = true)
    public List<Mentor> searchByProfessionalExperience(String experiencia){
        return mentorRepository.findByProfessionalExperience(experiencia);
    }

    @Transactional(readOnly = true)
    public List<Mentor> searchByCategories(Set<Category> categorias){
        return mentorRepository.findByCategories(categorias);
    }

    @Transactional(readOnly = true)
    public List<Mentor> searchAll(){
        return mentorRepository.findAll();
    }


    @Transactional
    public void deleteMentor(Mentor mentor){ mentorRepository.delete(mentor);}

    @Transactional
    public void deleteById(Long id){ mentorRepository. deleteById(id);}

    @Transactional
    public void deleteAllMentors(){ mentorRepository.deleteAll();}
    

}
