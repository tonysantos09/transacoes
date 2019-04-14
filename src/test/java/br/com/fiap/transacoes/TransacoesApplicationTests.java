package br.com.fiap.transacoes;

import br.com.fiap.transacoes.Repository.todasTransacoesDAO;
import br.com.fiap.transacoes.Repository.novaTransacaoDAO;
import br.com.fiap.transacoes.Controller.TransacaoController;
import br.com.fiap.transacoes.Domain.todasTransacoes;
import br.com.fiap.transacoes.Domain.novaTransacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransacaoController.class)
public class TransacoesApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private todasTransacoesDAO repositoryTodasTransacoes;
	private novaTransacaoDAO repositoryNovaTransacao;

	@Test
	public void SalvarTransacao() throws Exception {

		repositoryNovaTransacao = mock(novaTransacaoDAO.class);

		novaTransacao nvTransacao = new novaTransacao();
		nvTransacao.setTimestamp(System.currentTimeMillis());
		nvTransacao.setAmount(25000.15);
		nvTransacao.setDataInsert(System.currentTimeMillis());
		when(this.repositoryNovaTransacao.save(nvTransacao)).thenReturn(HttpStatus.CREATED);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		MockHttpServletRequestBuilder request = post("/transacoes");
		request.content(new ObjectMapper().writeValueAsString(nvTransacao));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);
		mvc.perform(request).andExpect(status().isCreated());
	}

	@Test
	public void NaoSalvarTransacao() throws Exception {

		repositoryNovaTransacao = mock(novaTransacaoDAO.class);

		novaTransacao nvTransacao = new novaTransacao();
		nvTransacao.setAmount(System.currentTimeMillis());
		nvTransacao.setAmount(25000.15);
		nvTransacao.setDataInsert(System.currentTimeMillis());
		when(this.repositoryNovaTransacao.save(nvTransacao)).thenReturn(HttpStatus.NO_CONTENT);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		MockHttpServletRequestBuilder request = post("/transacoes");
		request.content(new ObjectMapper().writeValueAsString(nvTransacao));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);
		mvc.perform(request).andExpect(status().isNoContent());
	}

	@Test(expected=NullPointerException.class)
	public void GetNotFounded() throws Exception {
		ArrayList<novaTransacao> allTransaction = new ArrayList<>();
		allTransaction = this.repositoryNovaTransacao.getList();

		when(this.repositoryTodasTransacoes.resume(allTransaction)).thenReturn(null);
		mvc.perform(get("/estatisticas")
				.accept(MediaType.APPLICATION_JSON));
	}

}
