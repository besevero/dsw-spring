package br.unirio.dsw.selecaoppgi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

@Controller
public class ProvaAlinhamentoController
{
//	/edital/alinhamento/presenca
//	/edital/alinhamento/notas
//	/edital/alinhamento/encerramento
  public void encerrarAlianhamentoDeCandidato(List<InscricaoEdital> ListaDeCanditados) {
    //List<InscricaoEdital> candidatos = new ArrayList<InscricaoEdital>();
    
    for (InscricaoEdital candidato : ListaDeCanditados) {
      for (AvaliacaoProvaEscrita provaEscrita : candidato.getAvaliacoesProvasEscritas()) {
        int idCandidato = candidato.getIdCandidato();
        //marcaAprovadoProvasEscritas(idCandidato) -> identifica estado de aprovação do candidato
        if(provaEscrita.getProvaEscrita() != null && candidato.exigeProvaOral()){
       // TODO: mover edital para estado de finalizado, calcular nota do candidato e salvar no banco de dados
        }
        else {
          //List<candidato.getNomeCandidato> candidatos = new ArrayList<getNomeCandidato>();
          candidato.getNomeCandidato();

        }
      }
    }
  }
}
