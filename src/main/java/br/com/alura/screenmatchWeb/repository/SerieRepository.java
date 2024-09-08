package br.com.alura.screenmatchWeb.repository;

import br.com.alura.screenmatchWeb.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
