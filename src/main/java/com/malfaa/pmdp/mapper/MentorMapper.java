package com.malfaa.pmdp.mapper;

import com.malfaa.pmdp.dto.mentorDTO.MentorResponseDTO;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Session;
import com.malfaa.pmdp.model.enums.Scheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MentorMapper { //FIXME alterar aqui o mapper junto dos DTOs
    public MentorResponseDTO toDto(Mentor mentor){
        if (mentor == null){return null;}

        List<LocalDateTime> availableSlots = new ArrayList<>();

        if(mentor.getCalendar() != null){
            availableSlots = mentor.getCalendar().getSessions().stream()
                    .filter(
                            sessao -> sessao.getStatus() == Scheduling.DISPONIVEL
                    ).map(Session::getDateTime)
                    .sorted().toList();
        }
        return new MentorResponseDTO(
                mentor.getId(), mentor.getName(), mentor.getEmail(), mentor.getProfileDescription(),
                mentor.getAcademicFormation(), mentor.getProfessionalExperience(), mentor.getCategories(),
                availableSlots);
    }

    public List<MentorResponseDTO> listToDto(List<Mentor> mentors){
        if (mentors == null){return null;}
        return mentors.stream().map(this::toDto).collect(Collectors.toList());
    }
}
