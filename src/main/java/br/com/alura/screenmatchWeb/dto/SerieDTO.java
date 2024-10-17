package br.com.alura.screenmatchWeb.dto;

import br.com.alura.screenmatchWeb.model.Categoria;

public record SerieDTO(Long id, String titulo, Integer totalTemporadas, Double avaliacao,
Categoria genero, String atores,
String poster, String sinopse
) {
}
