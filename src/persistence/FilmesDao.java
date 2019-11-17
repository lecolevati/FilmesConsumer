package persistence;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Filme;

public class FilmesDao implements IFilmesDao {

	private String HTTP_URL = "http://localhost:8080/FilmesRestWebService/rest/filmes";

	@Override
	public List<Filme> consultaFilmes() throws IOException {
		StringBuffer sb = new StringBuffer();
		URL url = new URL(HTTP_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "APPLICATION/JSON");
		conn.setRequestProperty("token", "1234");
		
		int codigo = conn.getResponseCode();
		
		if (codigo == 200){
			
			InputStream fluxo = conn.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null){
				sb.append(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Conexão Indisponível - Código:"+codigo);
		}
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Filme>>(){}.getType();
		List<Filme>listaFilmes = gson.fromJson(sb.toString(), listType);
		
		return listaFilmes;
	}

	@Override
	public String adicionaFilme(Filme f) throws IOException {
		Gson gson = new Gson();
		String filme = gson.toJson(f);

		URL url = new URL(HTTP_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(filme.length()));
		conn.setRequestProperty("token", "1234");
		conn.setUseCaches(false);

		OutputStream fluxoSaida = conn.getOutputStream();
		DataOutputStream data = new DataOutputStream(fluxoSaida);
		data.write(filme.getBytes("UTF-8"));
		data.flush();

		conn.getInputStream();
		int codigo = conn.getResponseCode();

		if (codigo == 200) {
			return "Filme inserido com sucesso";
		} else {
			return "Falha na inserção";
		}
	}

}