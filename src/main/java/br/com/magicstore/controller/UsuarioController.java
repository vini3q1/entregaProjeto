package br.com.magicstore.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.CarrinhoResponse;
import br.com.magicstore.dto.CarrinhoUsuarioRequest;
import br.com.magicstore.dto.DefaultIdRequest;
import br.com.magicstore.dto.DefaultIdStringRequest;
import br.com.magicstore.dto.EnderecoRequest;
import br.com.magicstore.dto.LoginRequest;
import br.com.magicstore.dto.UsuarioRequest;
import br.com.magicstore.dto.UsuarioResetSenhaRequest;
import br.com.magicstore.entity.UsuarioEntity;
import br.com.magicstore.service.CarrinhoService;
import br.com.magicstore.service.PedidoService;
import br.com.magicstore.service.ProdutoService;
import br.com.magicstore.service.UsuarioService;

@RestController
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/")
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("produtosIndexPg1", produtoService.getProdutosIndex());
		mv.addObject("produtosIndexPg2", produtoService.getProdutosIndex());
		mv.setViewName("home/index");
		session.setAttribute("sessionID", session.getId());

		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/cadastro_login");
		mv.addObject("usuario", new UsuarioRequest());
		mv.addObject("login", new LoginRequest());
		return mv;
	}

	@GetMapping("/minhaConta")
	public ModelAndView minhaConta(Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pedidos", pedidoService.getPedidosByIdUsuario(idUsuario));
		mv.setViewName("home/minhaConta");
		return mv;
	}

	@GetMapping("/minhaConta/pedidos")
	public ModelAndView minhaContaPedidos(Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pedidos", pedidoService.getPedidosByIdUsuario(idUsuario));
		mv.setViewName("home/minhaContaPedidos");
		return mv;
	}

	@GetMapping("/minhaConta/dadosPessoais")
	public ModelAndView minhaContaDadosPessoais(Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dadosUsuario", service.getUsuarioByIdUsuario(idUsuario).get());
		mv.addObject("usuario", new UsuarioRequest());
		mv.setViewName("home/minhaContaDadosPessoais");
		return mv;
	}

	@PutMapping("/minhaConta/dadosPessoais")
	public ModelAndView atualizarUsuario(@Validated UsuarioRequest usuario) {
		service.atualizarUsuario(usuario);
		return minhaConta(usuario.getId());
	}

	@GetMapping("/minhaConta/endereco")
	public ModelAndView minhaContaEndereco(Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("enderecoUsuario", service.findEnderecoByIdUsuario(idUsuario));
		mv.addObject("endereco", new EnderecoRequest());
		mv.setViewName("home/minhaContaEndereco");
		return mv;
	}
	
	@PutMapping("/minhaConta/endereco")
	public ModelAndView atualizarEndereco(@Validated EnderecoRequest endereco, Long idUsuario) {
		service.atualizarEndereco(endereco, idUsuario);
		return minhaContaEndereco(idUsuario);
	}

	@GetMapping("/minhaConta/cartoes")
	public ModelAndView minhaContaCartoes(Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cartoes", service.findCartoesByIdUsuario(idUsuario));
		mv.setViewName("home/minhaContaCartoes");
		return mv;
	}
	
	@DeleteMapping("/minhaConta/cartoes")
	public ModelAndView deletarCartao(@Validated Long idCartao, @Validated Long idUsuario) {
		service.deletarCartao(idCartao, idUsuario);
		return minhaContaCartoes(idUsuario);
	}

	@PostMapping(value = "/usuario")
	public ModelAndView cadastrarUsuario(@Validated UsuarioRequest usuario, BindingResult br) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new UsuarioRequest());
		mv.addObject("login", new LoginRequest());

		try {
			service.cadastrarUsuario(usuario);
			mv.setViewName("login/cadastro_login");
			mv.addObject("msg", "CADASTRO_SUCESSO");

		} catch (Exception e) {
			mv.setViewName("login/cadastro_login");
			mv.addObject("msg", "CADASTRO_ERRO");
		}

		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Validated LoginRequest login, BindingResult br, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("login", new LoginRequest());

		Optional<UsuarioEntity> usuarioLogin = service.login(login, session.getId());
		if (usuarioLogin.isPresent()) {

			List<CarrinhoResponse> carrinhoByIdSessao = carrinhoService.getCarrinhoByIdSessao(session.getId(), false,
					usuarioLogin.get().getId());
			if (!carrinhoByIdSessao.isEmpty()) {

				CarrinhoUsuarioRequest request = new CarrinhoUsuarioRequest();
				DefaultIdStringRequest sessao = new DefaultIdStringRequest();
				sessao.setId(session.getId());
				request.setSessao(sessao);

				DefaultIdRequest usuarioR = new DefaultIdRequest();
				usuarioR.setId(usuarioLogin.get().getId());
				request.setUsuario(usuarioR);
				carrinhoService.vinculaCarrinhoUsuario(request);

			}

			session.setAttribute("sessionID", session.getId());
			session.setAttribute("usuarioLogado", usuarioLogin.get());
			mv.addObject("produtosIndexPg1", produtoService.getProdutosIndex());
			mv.addObject("produtosIndexPg2", produtoService.getProdutosIndex());
			mv.setViewName("home/index");

		} else {
			mv.addObject("msg", "Usuário não encontrado. Tente novamente!");
			return login();
		}

		return mv;

	}

	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {

		session.invalidate();
		return login();

	}

	@PostMapping("/reset/senha/mail")
	public ModelAndView resetSenha(@Validated DefaultIdStringRequest emailUsuario) {
		service.resetSenhaEnvioEmail(emailUsuario);
		return login();
	}

	@PutMapping("/reset/senha/")
	public ModelAndView resetSenha(@Validated UsuarioResetSenhaRequest usuario, HttpSession session) {
		service.resetSenha(usuario);
		return home(session);
	}

	@GetMapping("/reset/senha/usuario/{email}")
	public ModelAndView getResetSenha(@PathVariable String email) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("retornoEmail", email);
		mv.addObject("resetSenha", new UsuarioResetSenhaRequest());
		mv.setViewName("login/resetSenha");
		return mv;
	}

	@GetMapping("/reset/senha")
	public ModelAndView formResetSenha() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("email", new DefaultIdStringRequest());
		mv.setViewName("login/formReset");

		return mv;
	}

}
