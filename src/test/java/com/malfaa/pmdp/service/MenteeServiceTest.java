package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.MenteeDTO;
import com.malfaa.pmdp.dto.ReviewDTO;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.repository.MenteeRepository;
import com.oracle.svm.core.annotate.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenteeServiceTest {

    @Mock
    MenteeRepository menteeRepository;

    @InjectMocks
    MenteeService menteeService;

    @Test
    void findMenteeByIdAndThenReturnTrue(){
        MenteeDTO menteeDto = new MenteeDTO(
                null, "40",
                Set.of("Java", "Python"),
                Set.of(
                        new ReviewDTO(1L, 3, "Comentário1"),
                        new ReviewDTO(2L, 5, "Comentário2")
                ));

        Mentee comparison = new Mentee();
        comparison.setId(1L);
        comparison.setProgress(menteeDto.progress());
        comparison.setInterests(menteeDto.interests());

        when(menteeRepository.findById(1L)).thenReturn(Optional.of(comparison));

        MenteeDTO result = menteeService.searchById(comparison.getId());

    }

}
