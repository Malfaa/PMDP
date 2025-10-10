package com.malfaa.pmdp.mapper;

import com.malfaa.pmdp.dto.ReviewDTO;
import com.malfaa.pmdp.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public ReviewDTO toDto(Review review){
        if (review == null){return null;}
        return new ReviewDTO(review.getId(), review.getGrade(), review.getComment());
    }

    public Review toEntity(ReviewDTO dto){
        if (dto == null){return null;}
        Review rv = new Review();
        rv.setId(dto.id());
        rv.setGrade(dto.grade());
        rv.setComment(dto.comment());
        return rv;
    }

    public List<ReviewDTO> listToDTO (List<Review> reviews){
        if (reviews == null){return null;}
        return reviews.stream().map(this::toDto).collect(Collectors.toList());
    }
}
