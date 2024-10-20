package br.com.alura.screenmatchWeb.controller;

import br.com.alura.screenmatchWeb.dto.EpisodioDTO;
import br.com.alura.screenmatchWeb.dto.SerieDTO;
import br.com.alura.screenmatchWeb.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterSeriesTop5() {
        return service.obterSeriesTop5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterSerieLancamentos() {
        return service.obterLancamento();
    }

    @GetMapping("/{id}")
    public SerieDTO obterSeriePorId(@PathVariable Long id){
        return service.obterSeriePorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasAsTemporadas(@PathVariable Long id){
        return service.obterTodasAsTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNmuero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeCategoria){
        return service.obterSeriesPorCategoria(nomeCategoria);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterMelhgoresEpisodios(@PathVariable Long id){
        return service.obterMelhoresEpisodiosPorSerie(id);
    }





}
