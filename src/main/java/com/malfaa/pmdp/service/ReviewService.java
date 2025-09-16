package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.Review;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import com.malfaa.pmdp.model.enums.Scheduling;
import com.malfaa.pmdp.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository repository){
        this.reviewRepository = repository;
    }

    /**
     * Busca uma avaliação utilizando o ID como pesquisa.
     *
     * @param id
     * @return Avaliacao
     */
    @Transactional(readOnly = true)
    public Optional<Review> searchById(Long id){ return reviewRepository.findById(id);}

    /**
     * Busca uma lista de avaliações utilizando o Mentorado como pesquisa.
     *
     * @param mentee
     * @return List<Avaliacao>
     */
    @Transactional(readOnly = true)
    public List<Review> searchByMentee(Mentee mentee){ return reviewRepository.findByMentee(mentee);}

    /**
     * Busca por uma avaliação utilizando a Sessão como pesquisa.
     *
     * @param session
     * @return Avaliacao
     */
    @Transactional(readOnly = true)
    public Optional<Review> searchBySession(Session session){ return reviewRepository.findBySession(session);}

    /**
     * Busca por uma lista de avaliações utilizando a nota como pesquisa.
     *
     * @param nota
     * @return List<Avaliacao>
     */
    @Transactional(readOnly = true)
    public List<Review> searchByGrade(Integer nota){ return reviewRepository.findByGrade(nota);}

    /**
     * Cria uma nova avaliação baseada nas condições presentes.
     *
     * @param novaReview
     * @return
     */
    @Transactional
    public Review createReview(Review novaReview){
        Session session = novaReview.getSession();
        if(session == null){
            throw new IllegalArgumentException("A avaliação deve estar associada a uma sessão.");
        }

        //Regra 1 (UC06)
        Optional<Review> avaliacaoExistente = reviewRepository.findBySession(session);
        if(avaliacaoExistente.isPresent()){
            throw new IllegalStateException("Esta sessão já foi avaliada.");
        }

        //Regra 2 (RN03)
        if (session.getStatus() != Scheduling.CONCLUIDA){
            throw new IllegalStateException("Apenas sessões concluídas podem ser avaliadas");
        }

        //Regra 3 (UC06)
        validateGrade(novaReview.getGrade());

        return reviewRepository.save(novaReview);
    }

    @Transactional
    public void deleteReview(Review review){ reviewRepository.delete(review);}

    @Transactional
    public void deleteReviewById(Long id){ reviewRepository.deleteById(id);}

    @Transactional
    public void deleteAllReviews(){ reviewRepository.deleteAll();}

    /**
     * Edita uma avaliação já existente.
     *
     * @param id
     * @param novaNota
     * @param novoComentario
     * @return avaliacaoExistente
     */
    @Transactional
    public Review editReview(Long id, Integer novaNota, String novoComentario){
        Review reviewExistente = reviewRepository.findById(id).orElseThrow(()-> new RuntimeException("Avaliação com ID "+ id + " não encontrada."));
        validateGrade(novaNota);
        reviewExistente.setGrade(novaNota);
        reviewExistente.setComment(novoComentario);
        return reviewRepository.save(reviewExistente);
    }

    private void validateGrade(Integer nota) {
        int NOTA_MAXIMA = 5;
        int NOTA_MINIMA = 1;
        if (nota == null || nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota da avaliação é obrigatória e deve ser entre 1 e 5.");
        }
    }

}