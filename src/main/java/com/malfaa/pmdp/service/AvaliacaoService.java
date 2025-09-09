package com.malfaa.pmdp.service;

import com.malfaa.pmdp.model.Avaliacao;
import com.malfaa.pmdp.model.Mentorado;
import com.malfaa.pmdp.model.Sessao;
import com.malfaa.pmdp.model.enums.Agendamento;
import com.malfaa.pmdp.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository repository){
        this.avaliacaoRepository = repository;
    }

    /**
     * Busca uma avaliação utilizando o ID como pesquisa.
     *
     * @param id
     * @return Avaliacao
     */
    public Optional<Avaliacao> buscarPorId(Long id){ return avaliacaoRepository.findById(id);}

    /**
     * Busca uma lista de avaliações utilizando o Mentorado como pesquisa.
     *
     * @param mentorado
     * @return List<Avaliacao>
     */
    public List<Avaliacao> buscarPorMentorado(Mentorado mentorado){ return avaliacaoRepository.findByMentorado(mentorado);}

    /**
     * Busca por uma avaliação utilizando a Sessão como pesquisa.
     *
     * @param sessao
     * @return Avaliacao
     */
    public Optional<Avaliacao> buscarPorSessao(Sessao sessao){ return avaliacaoRepository.findBySessao(sessao);}

    /**
     * Busca por uma lista de avaliações utilizando a nota como pesquisa.
     *
     * @param nota
     * @return List<Avaliacao>
     */
    public List<Avaliacao> buscarPorNota(Integer nota){ return avaliacaoRepository.findByNota(nota);}

    /**
     * Cria uma nova avaliação baseada nas condições presentes.
     *
     * @param novaAvaliacao
     * @return
     */
    @Transactional
    public Avaliacao criarAvaliacao(Avaliacao novaAvaliacao){
        Sessao sessao = novaAvaliacao.getSessao();
        if(sessao == null){
            throw new IllegalArgumentException("A avaliação deve estar associada a uma sessão.");
        }

        //Regra 1 (UC06)
        Optional<Avaliacao> avaliacaoExistente = avaliacaoRepository.findBySessao(sessao);
        if(avaliacaoExistente.isPresent()){
            throw new IllegalStateException("Esta sessão já foi avaliada.");
        }

        //Regra 2 (RN03)
        if (sessao.getStatus() != Agendamento.CONCLUIDA){
            throw new IllegalStateException("Apenas sessões concluídas podem ser avaliadas");
        }

        //Regra 3 (UC06)
        validarNota(novaAvaliacao.getNota());

        return avaliacaoRepository.save(novaAvaliacao);
    }


    public void deletarAvaliacao(Avaliacao avaliacao){ avaliacaoRepository.delete(avaliacao);}

    public void deletarAvaliacaoPorId(Long id){ avaliacaoRepository.deleteById(id);}

    public void deletarTodasAsAvaliacoes(){ avaliacaoRepository.deleteAll();}

    /**
     * Edita uma avaliação já existente.
     *
     * @param id
     * @param novaNota
     * @param novoComentario
     * @return avaliacaoExistente
     */
    @Transactional
    public Avaliacao editarAvaliacao(Long id, Integer novaNota, String novoComentario){
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id).orElseThrow(()-> new RuntimeException("Avaliação com ID "+ id + " não encontrada."));
        validarNota(novaNota);
        avaliacaoExistente.setNota(novaNota);
        avaliacaoExistente.setComentario(novoComentario);
        return avaliacaoRepository.save(avaliacaoExistente);
    }

    private void validarNota(Integer nota) {
        int NOTA_MAXIMA = 5;
        int NOTA_MINIMA = 1;
        if (nota == null || nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota da avaliação é obrigatória e deve ser entre 1 e 5.");
        }
    }

}