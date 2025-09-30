package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.FeedbackDTO;
import com.malfaa.pmdp.model.Feedback;
import com.malfaa.pmdp.model.Session;
import com.malfaa.pmdp.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackService feedbackService;

    @Test
    void findFeedbackByIdAndThenReturnTrue(){
        FeedbackDTO feedbackDTO = new FeedbackDTO(null, "Comentário Mock");

        Feedback fb = new Feedback();
        fb.setId(1L);
        fb.setComment(feedbackDTO.comment());

        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(fb));

        FeedbackDTO result = feedbackService.searchById(fb.getId());

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Comentário Mock", result.comment());

        verify(feedbackRepository, times(1)).findById(1L);
    }
}
