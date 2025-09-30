package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.FeedbackDTO;
import com.malfaa.pmdp.model.Feedback;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Session;
import com.malfaa.pmdp.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService (FeedbackRepository repository){
        this.feedbackRepository = repository;
    }

    @Transactional(readOnly = true)
    public FeedbackDTO searchById(Long id){
        Feedback fb = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado"));
        return convertToDto(fb);
    }

    @Transactional(readOnly = true)
    public List<FeedbackDTO> searchByMentor(Mentor mentor){
        return feedbackRepository.findByMentor(mentor)
                .stream().map(this::convertToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FeedbackDTO> searchByMentee(Mentee mentee){
        return feedbackRepository.findByMentee(mentee)
                .stream().map(this::convertToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public FeedbackDTO searchBySession(Session session){
        Feedback result = feedbackRepository.findBySession(session).orElseThrow(
                ()-> new RuntimeException("Session não encontrada.")
        );
        return convertToDto(result);
    }

    @Transactional
    public FeedbackDTO createFeedback(Feedback feedback){
        if (feedback.getSession() == null || feedback.getSession().getId() == null) {
            throw new IllegalArgumentException("Feedback deve estar associado a uma sessão existente.");
        }

        Optional<Feedback> feedbackExist = feedbackRepository.findBySession(feedback.getSession());
        if(feedbackExist.isPresent()){
            throw new IllegalArgumentException("Feedback já existe");
        }

        return convertToDto(feedbackRepository.save(feedback));
    }

    @Transactional
    public FeedbackDTO editFeedback(Long oldId, String newComment){
        Feedback feedback = feedbackRepository.findById(oldId)
                .orElseThrow(() -> new IllegalStateException("Feedback não existe!"));

        if (newComment == null || newComment.isBlank()) {
            throw new IllegalArgumentException("O comentário não pode ser vazio.");
        }

        feedback.setComment(newComment);

        return convertToDto(feedbackRepository.save(feedback));
    }

    @Transactional
    public void deleteById(Long id){
        feedbackRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllFeedback(){
        feedbackRepository.deleteAll();
    }

    private FeedbackDTO convertToDto(Feedback fb){
        return new FeedbackDTO(fb.getId(), fb.getComment());
    }

}
