package com.malfaa.pmdp.dto;

import com.malfaa.pmdp.model.Review;

import java.util.Set;

public record MenteeDTO(
        Long id,
        String name,
        String email,
        String progress,
        Set<String> interests,
        Set<ReviewDTO> reviews
) {}
