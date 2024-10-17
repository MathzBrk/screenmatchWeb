package br.com.alura.screenmatchWeb.service;

import br.com.alura.screenmatchWeb.dto.SerieDTO;
import br.com.alura.screenmatchWeb.model.Serie;
import br.com.alura.screenmatchWeb.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterSeriesTop5() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());

    }

    private List<SerieDTO> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getGenero(),s.getAtores(),s.getPoster(),s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterLancamento() {
        return converteDados(repository.encontrarEpisodiosMaisRecentes());
    }

    public SerieDTO obterSeriePorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getGenero(),s.getAtores(),s.getPoster(),s.getSinopse());
        }
        return null;
    }
}
