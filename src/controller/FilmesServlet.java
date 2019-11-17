package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Filme;
import model.Genero;
import persistence.FilmesDao;
import persistence.IFilmesDao;

@WebServlet("/filmes")
public class FilmesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FilmesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		String erro = "";

		try {
			IFilmesDao fDao = new FilmesDao();

			if (acao.equals("insert")) {
				Filme f = new Filme();
				f.setIdFilme(Integer.parseInt(request.getParameter("idFilme")));
				f.setNomeFilme(request.getParameter("nomeFilme"));
				Genero g = new Genero();
				g.setCodGenero(Integer.parseInt(request.getParameter("codGenero")));
				g.setTipoGenero(request.getParameter("tipoGenero"));
				f.setGeneroFilme(g);

				String retorno = insereFilme(f, fDao);
				request.setAttribute("retorno", retorno);
			}
			if (acao.equals("getAllFilmes")) {
				List<Filme> lista = buscaTodosFilmes(fDao);
				request.setAttribute("lista", lista);
			}
		} catch (IOException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}

	}

	private String insereFilme(Filme f, IFilmesDao fDao) throws IOException {
		return fDao.adicionaFilme(f);
	}

	private List<Filme> buscaTodosFilmes(IFilmesDao fDao) throws IOException {
		return fDao.consultaFilmes();
	}

}
