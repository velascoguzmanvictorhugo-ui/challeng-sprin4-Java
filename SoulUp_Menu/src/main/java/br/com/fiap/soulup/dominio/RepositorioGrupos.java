package br.com.fiap.soulup.dominio;
import java.util.ArrayList;

public interface RepositorioGrupos {
    void adicionar(Grupo grupo);
    ArrayList<Grupo> listarTodos() ;

}
