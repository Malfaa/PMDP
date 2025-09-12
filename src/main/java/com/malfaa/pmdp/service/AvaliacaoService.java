package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.Assessment;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import com.malfaa.pmdp.model.enums.Scheduling;
import com.malfaa.pmdp.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final ReviewRepository reviewRepository;

    public AvaliacaoService(ReviewRepository repository){
        this.reviewRepository = repository;
    }

    /**
     * Busca uma avaliação utilizando o ID como pesquisa.
     *
     * @param id
     * @return Avaliacao
     */
    public Optional<Assessment> buscarPorId(Long id){ return reviewRepository.findById(id);}

    /**
     * Busca uma lista de avaliações utilizando o Mentorado como pesquisa.
     *
     * @param mentee
     * @return List<Avaliacao>
     */
    public List<Assessment> buscarPorMentorado(Mentee mentee){ return reviewRepository.findByMentee(mentee);}

    /**
     * Busca por uma avaliação utilizando a Sessão como pesquisa.
     *
     * @param session
     * @return Avaliacao
     */
    public Optional<Assessment> buscarPorSessao(Session session){ return reviewRepository.findBySession(session);}

    /**
     * Busca por uma lista de avaliações utilizando a nota como pesquisa.
     *
     * @param nota
     * @return List<Avaliacao>
     */
    public List<Assessment> buscarPorNota(Integer nota){ return reviewRepository.findByGrade(nota);}

    /**
     * Cria uma nova avaliação baseada nas condições presentes.
     *
     * @param novaAssessment
     * @return
     */
    @Transactional
    public Assessment criarAvaliacao(Assessment novaAssessment){
        Session session = novaAssessment.getSession();
        if(session == null){
            throw new IllegalArgumentException("A avaliação deve estar associada a uma sessão.");
        }

        //Regra 1 (UC06)
        Optional<Assessment> avaliacaoExistente = reviewRepository.findBySession(session);
        if(avaliacaoExistente.isPresent()){
            throw new IllegalStateException("Esta sessão já foi avaliada.");
        }

        //Regra 2 (RN03)
        if (session.getStatus() != Scheduling.CONCLUIDA){
            throw new IllegalStateException("Apenas sessões concluídas podem ser avaliadas");
        }

        //Regra 3 (UC06)
        validarNota(novaAssessment.getGrade());

        return reviewRepository.save(novaAssessment);
    }


    public void deletarAvaliacao(Assessment assessment){ reviewRepository.delete(assessment);}

    public void deletarAvaliacaoPorId(Long id){ reviewRepository.deleteById(id);}

    public void deletarTodasAsAvaliacoes(){ reviewRepository.deleteAll();}

    /**
     * Edita uma avaliação já existente.
     *
     * @param id
     * @param novaNota
     * @param novoComentario
     * @return avaliacaoExistente
     */
    @Transactional
    public Assessment editarAvaliacao(Long id, Integer novaNota, String novoComentario){
        Assessment assessmentExistente = reviewRepository.findById(id).orElseThrow(()-> new RuntimeException("Avaliação com ID "+ id + " não encontrada."));
        validarNota(novaNota);
        assessmentExistente.setGrade(novaNota);
        assessmentExistente.setComment(novoComentario);
        return reviewRepository.save(assessmentExistente);
    }

    private void validarNota(Integer nota) {
        int NOTA_MAXIMA = 5;
        int NOTA_MINIMA = 1;
        if (nota == null || nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota da avaliação é obrigatória e deve ser entre 1 e 5.");
        }
    }

}