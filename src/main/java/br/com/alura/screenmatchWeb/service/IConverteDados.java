package br.com.alura.screenmatchWeb.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
