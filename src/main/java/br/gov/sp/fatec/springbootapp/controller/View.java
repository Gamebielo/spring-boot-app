package br.gov.sp.fatec.springbootapp.controller;

public class View {
    
    public static class UsuarioResumo {}
    public static class UsuarioCompleto extends UsuarioResumo {} // com o extends eu pego o que já está mapeado na outra classe...

    public static class AutorizacaoResumo {}
}