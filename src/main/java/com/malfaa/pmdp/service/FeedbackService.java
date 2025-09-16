package com.malfaa.pmdp.service;

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
    public Optional<Feedback> searchById(Long id){
        return feedbackRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Feedback> searchByMentor(Mentor mentor){
        return feedbackRepository.findByMentor(mentor);
    }

    @Transactional(readOnly = true)
    public List<Feedback> searchByMentee(Mentee mentee){
        return feedbackRepository.findByMentee(mentee);
    }

    @Transactional(readOnly = true)
    public Optional<Feedback> searchBySession(Session session){
        return feedbackRepository.findBySession(session);
    }

    @Transactional
    public Feedback createFeedback(Feedback feedback){
        if (feedback.getSession() == null || feedback.getSession().getId() == null) {
            throw new IllegalArgumentException("Feedback deve estar associado a uma sessão existente.");
        }

        Optional<Feedback> feedbackExist = feedbackRepository.findBySession(feedback.getSession());
        if(feedbackExist.isPresent()){
            throw new IllegalArgumentException("Feedback já existe");
        }

        return feedbackRepository.save(feedback);
    }

    @Transactional
    public Feedback editFeedback(Long oldId, String newComment){
        Feedback feedback = feedbackRepository.findById(oldId)
                .orElseThrow(() -> new IllegalStateException("Feedback não existe!"));

        if (newComment == null || newComment.isBlank()) {
            throw new IllegalArgumentException("O comentário não pode ser vazio.");
        }

        feedback.setComment(newComment);

        return feedbackRepository.save(feedback);
    }

    @Transactional
    public void deleteById(Long id){
        feedbackRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllFeedback(){
        feedbackRepository.deleteAll();
    }

}
