package com.malfaa.pmdp.mapper;

import com.malfaa.pmdp.dto.FeedbackDTO;
import com.malfaa.pmdp.model.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {
    public FeedbackDTO toDto(Feedback feedback){
        if (feedback == null){return null;}

        return new FeedbackDTO(feedback.getId(), feedback.getComment());
    }

    public Feedback toEntity(FeedbackDTO dto){
        if (dto == null){return null;}
        Feedback fb = new Feedback();
        fb.setId(dto.id());
        fb.setComment(dto.comment());
        return fb;
    }

    public List<FeedbackDTO> listToDto(List<Feedback> feedbacks){
        if (feedbacks == null){return null;}
        return feedbacks.stream().map(this::toDto).collect(Collectors.toList());
    }
}
