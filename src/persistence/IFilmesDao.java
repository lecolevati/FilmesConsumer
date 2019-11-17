package persistence;

import java.io.IOException;
import java.util.List;

import model.Filme;

public interface IFilmesDao {

	public List<Filme> consultaFilmes() throws IOException;
	public String adicionaFilme(Filme f) throws IOException;
	
}
