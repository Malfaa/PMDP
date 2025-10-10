package com.malfaa.pmdp.dto.mentorDTO;

import com.malfaa.pmdp.dto.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record MentorResponseDTO(
        Long id,
        String name,
        String email,

        String profileDescription,
        String academicFormation,
        String professionalExperience,

        Set<CategoryDTO> categories,
        List<LocalDateTime> availableSlots
){}
