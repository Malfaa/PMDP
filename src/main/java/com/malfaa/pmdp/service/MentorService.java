package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.repository.MentorRepository;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository repository){
        this.mentorRepository = repository;
    }

    public Optional<Mentor> buscaPorId(Long id){
        return mentorRepository.findById(id);
    }

    public List<Mentor> buscaPorFormacaoAcademica(String formacao){
        //todo adicionar algum tipo de filtro, mesmo que escreve pela metade ou algo do tipo
        return mentorRepository.findByAcademicFormation(formacao);
    }

    public List<Mentor> buscaPorExperienciaProfissional(String experiencia){
        return mentorRepository.findByProfessionalExperience(experiencia);
    }

    public List<Mentor> buscaPorCategoria(Category categoria){
        return mentorRepository.findByCategories(categoria);
    }

    public List<Mentor> buscaPorTodos(){
        return mentorRepository.findAll();
    }


    public void deletarMentor(Mentor mentor){ mentorRepository.delete(mentor);}
    public void deletarPorId(Long id){ mentorRepository. deleteById(id);}
    public void deletarTodosMentores(){ mentorRepository.deleteAll();}
    

}
