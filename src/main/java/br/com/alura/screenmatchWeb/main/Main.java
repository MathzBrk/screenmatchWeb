package br.com.alura.screenmatchWeb.main;

import br.com.alura.screenmatchWeb.model.*;
import br.com.alura.screenmatchWeb.repository.SerieRepository;
import br.com.alura.screenmatchWeb.service.ConsumoApi;
import br.com.alura.screenmatchWeb.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi  = new ConsumoApi();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=256f3114";
    private ConverteDados converteDados = new ConverteDados();
    private List<DadosSerie> dadosSerie = new ArrayList<>();

    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();

    public Main(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por titulo
                    5 - Buscar série por ator
                    6 - Buscar as top 5 séries
                    7 - Buscar séries por categoria
                    8 - Buscar séries por total de temporada

                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 5:
                    buscarSeriePorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriePorCategoria();
                    break;
                case 8:
                    buscarSeriePorTotalTemporada();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSeriePorTotalTemporada() {
        System.out.println("Digite o numero minimo de temporadas que deseja para a busca: ");
        var totalTemporada = scanner.nextInt();

        if (totalTemporada < 0) {
            System.out.println("O número de temporadas deve ser positivo.");
            return;
        }

        scanner.nextLine();
        System.out.println("Digite a avaliação minima: ");
        var entradaAvaliacao = scanner.nextLine().replace('.', ',');

        double avaliacaoMinima;

        try {
            avaliacaoMinima = Double.parseDouble(entradaAvaliacao.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Avaliação inválida! Por favor, insira um número.");
            return;
        }
        List<Serie> seriePorTotalTemporada = repositorio.findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(totalTemporada, avaliacaoMinima);
        if (seriePorTotalTemporada.isEmpty()) {
            System.out.println("Nenhuma série encontrada!");
        } else {
            System.out.println("Séries encontradas: ");
            seriePorTotalTemporada.forEach(s -> System.out.println(s.getTitulo()));
        }


    }

    private void buscarSeriePorCategoria() {
        System.out.println("Deseja buscar séries de qual categoria? ");
        var nomeGenero = scanner.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesCategoria = repositorio.findByGenero(categoria);
        System.out.println("Séries da categoria " + nomeGenero);
        seriesCategoria.forEach(System.out::println);

    }

    private void buscarTop5Series() {
        List<Serie> seriesTop5 = repositorio.findTop5ByOrderByAvaliacaoDesc();
        seriesTop5.forEach(s -> System.out.println(s.getTitulo() + " / avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriePorAtor() {
        System.out.println("Qual o nome para busca? ");
        var nomeAtor = scanner.nextLine();
        System.out.println("Avalições a partir de qual valor? ");
        var entradaAvaliacao = scanner.nextLine().replace('.', ',');

        double avaliacao;

        try {
            avaliacao = Double.parseDouble(entradaAvaliacao.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Avaliação inválida! Por favor, insira um número.");
            return;
        }

        List<Serie> seriesEncontrada = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,avaliacao);
        System.out.println("Séries em que o " + nomeAtor + " trabalhou");
        seriesEncontrada.forEach(s -> System.out.println(s.getTitulo() + " / avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = scanner.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
        if (serieBuscada.isPresent()) {
            System.out.println("Dados da série: " + serieBuscada.get());
        } else{
            System.out.println("Série não encontrada");
        }
    }

    private void buscarSerieWeb() {
            DadosSerie dados = getDadosSerie();
            Serie serie = new Serie(dados);
            //dadosSerie.add(dados);
            repositorio.save(serie);

        }

        private DadosSerie getDadosSerie() {
            System.out.println("Digite o nome da série para busca");
            var nomeSerie = scanner.nextLine();
            var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
            DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
            return dados;
        }
    private void buscarEpisodioPorSerie(){
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = scanner.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
        if (serie.isPresent()) {

            var serieEncontrada = serie.get();

            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        } else{
            System.out.println("Série não encotrada!");
        }
    }

    private void listarSeriesBuscadas() {
        series =  series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);

    }
}

