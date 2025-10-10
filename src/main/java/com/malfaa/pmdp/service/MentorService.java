package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Set;

import com.malfaa.pmdp.dto.mentorDTO.MentorResponseDTO;
import com.malfaa.pmdp.mapper.MentorMapper;
import org.springframework.stereotype.Service;

import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.repository.MentorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final MentorMapper mentorMapper;

    public MentorService(MentorRepository repository, MentorMapper mMapper){
        this.mentorRepository = repository;
        this.mentorMapper = mMapper;
    }

    @Transactional(readOnly = true)
    public MentorResponseDTO searchById(Long id){
        return mentorMapper.toDto(mentorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Mentor com id: "+id+" não encontrado.")
        ));
    }

    @Transactional(readOnly = true)
    public List<MentorResponseDTO> searchByAcademicFormation(String term){
        List<Mentor> mentors = mentorRepository.findByAcademicFormationContainingIgnoreCase(term);
        return mentorMapper.listToDto(mentors);
    }

    @Transactional(readOnly = true)
    public List<MentorResponseDTO> searchByProfessionalExperience(String term){
        List<Mentor> mentors = mentorRepository.findByProfessionalExperienceContainingIgnoreCase(term);
        return mentorMapper.listToDto(mentors);
    }

    @Transactional(readOnly = true)
    public List<MentorResponseDTO> searchByCategories(Set<Category> categories){
        List<Mentor> mentors = mentorRepository.findByCategoriesIn(categories);
        return mentorMapper.listToDto(mentors);
    }

    @Transactional(readOnly = true)
    public List<MentorResponseDTO> searchAll(){
        return mentorMapper.listToDto(mentorRepository.findAll());
    }

    @Transactional
    public MentorResponseDTO createMentor(Mentor mentor){

    }


    @Transactional
    public void deleteMentor(Mentor mentor){ mentorRepository.delete(mentor);}

    @Transactional
    public void deleteById(Long id){
        if (!mentorRepository.existsById(id)) {
            throw new RuntimeException("Mentor com id: " + id + " não encontrado.");
        }
        mentorRepository.deleteById(id);}

    @Transactional
    public void deleteAllMentors(){
        List<Mentor> allMentors = mentorRepository.findAll();
        mentorRepository.deleteAll(allMentors);
    }
    

}
