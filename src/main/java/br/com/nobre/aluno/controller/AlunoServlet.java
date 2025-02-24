package br.com.nobre.aluno.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;

import br.com.nobre.aluno.service.AlunoCreateService;
import br.com.nobre.aluno.service.AlunoFindService;

@WebServlet(urlPatterns = "/aluno/*")
public class AlunoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AlunoFindService alunoFindService;
	private AlunoCreateService alunoCreateService;
	
	public AlunoServlet() {
		alunoFindService = new AlunoFindService();
		alunoCreateService = new AlunoCreateService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String pathInfo = req.getPathInfo(); 
			
			if (pathInfo == null || pathInfo.isEmpty()) {
				//exception path parameter obrigatorio
			} 

			String idPath = pathInfo.substring(1);
			int alunoId = Integer.valueOf(idPath);
			
			getResponseHeaders(resp);
			String response = alunoFindService.findAlunoById(alunoId);			
			
			resp.getWriter().write(response);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo(); 
		
		if (pathInfo != null) {
			//exception path parameter não pode ser passado
		} 

        try {
        	
        	getResponseHeaders(resp);
            String response = alunoCreateService.createAluno(req);
            
			resp.setStatus(HttpURLConnection.HTTP_CREATED);
            resp.getWriter().write(response);

        } catch (JSONException e) {

            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"status\":\"erro\",\"mensagem\":\"Erro ao processar JSON\"}");
       
	    }
			
	}
	
	private void getResponseHeaders(HttpServletResponse resp) {
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
	}
	
	
}
