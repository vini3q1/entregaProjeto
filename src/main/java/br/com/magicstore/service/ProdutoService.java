package br.com.magicstore.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.magicstore.dto.CategoriaResponse;
import br.com.magicstore.dto.FornecedorDTO;
import br.com.magicstore.dto.ProdutoDTO;
import br.com.magicstore.dto.ProdutoRequest;
import br.com.magicstore.entity.Categoria;
import br.com.magicstore.entity.Fornecedor;
import br.com.magicstore.entity.Produto;
import br.com.magicstore.exception.DefaultException;
import br.com.magicstore.repository.CategoriaRepository;
import br.com.magicstore.repository.FornecedorRepository;
import br.com.magicstore.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	private final Path root = Paths.get("./src/main/resources/static/themes/images");

	public void cadastrar(ProdutoRequest request) throws DefaultException, IOException {

//		TODO validar campos em branco
		Produto produto = new Produto();
		produto.setNome(request.getNome());
		produto.setDescricao(request.getDescricao());

		Optional<Categoria> categoria = categoriaRepository.findById(request.getIdCategoria());
		if (categoria.isPresent()) {
			produto.setCategoria(categoria.get());
		} else {
			throw new DefaultException("Categoria não encontrada!");
		}

		produto.setQuantidade(request.getQuantidade());
		produto.setValor(BigDecimal.valueOf(request.getValor()));
		repository.save(produto);

	}

	public List<ProdutoDTO> getAllProduto() {

		List<Produto> produtos = repository.findAll();
		List<ProdutoDTO> response = new ArrayList<>();

		produtos.forEach(produto -> {
			Fornecedor fn = produto.getFornecedor();
			CategoriaResponse categoria = new CategoriaResponse(produto.getCategoria().getId(),
					produto.getCategoria().getNome());
			FornecedorDTO fornecedor = new FornecedorDTO(fn.getId(), fn.getEmpresa(), fn.getCnpj(), fn.getEmail(),
					fn.getTelefone(), fn.getNomeContato());

			response.add(new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), categoria,
					fornecedor, produto.getQuantidade(), produto.getValor().doubleValue(), produto.getImagem()));
		});

		return response;
	}

	public List<ProdutoDTO> getProdutoByCategoria(Long idCategoria) {

		List<Produto> findByCategoria = repository.findByCategoria(new Categoria(idCategoria));
		List<ProdutoDTO> response = new ArrayList<>();

		findByCategoria.forEach(produto -> {
			Fornecedor fn = produto.getFornecedor();
			CategoriaResponse categoria = new CategoriaResponse(produto.getCategoria().getId(),
					produto.getCategoria().getNome());
//			FornecedorDTO fornecedor = new FornecedorDTO(fn.getId(), fn.getEmpresa(), fn.getCnpj(), fn.getEmail(),
//					fn.getTelefone(), fn.getNomeContato());

			response.add(new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), categoria,
					null, produto.getQuantidade(), produto.getValor().doubleValue(), produto.getImagem()));
		});

		return response;
	}

	public void salvarImagem(Long idProduto, MultipartFile imagem) throws IOException {

		Optional<Produto> findById = repository.findById(idProduto);
		if(findById.isPresent()) {
			
			Files.copy(imagem.getInputStream(), this.root.resolve(imagem.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			findById.get().setImagem(imagem.getOriginalFilename());
			repository.save(findById.get());
			
		}

	}
	
	public ProdutoDTO getProdutoById(Long idProduto) {
		
		Optional<Produto> findById = repository.findById(idProduto);
		if(findById.isPresent()) {
			
			Produto produto = findById.get();
			
			CategoriaResponse categoria = new CategoriaResponse(produto.getCategoria().getId(),
					produto.getCategoria().getNome());

			return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), categoria,
					null, produto.getQuantidade(), produto.getValor().doubleValue(), produto.getImagem());
			
		}
		
		return null;
		
	}

	public void atualizarInfo(ProdutoRequest request) {
		
		Optional<Produto> findById = repository.findById(request.getId());
		if(findById.isPresent()) {
			
			Produto produto = findById.get();
			produto.setNome(request.getNome());
			produto.setDescricao(request.getDescricao());
			produto.setQuantidade(request.getQuantidade());
			produto.setValor(BigDecimal.valueOf(request.getValor()));
			repository.save(produto);
			
		}
		
	}

	public void deletarProduto(Long idProduto) {

		Optional<Produto> findById = repository.findById(idProduto);
		if(findById.isPresent()) {
			repository.delete(findById.get());
		}
	}

	public List<Produto> getProdutosIndex() {
		return repository.findRandom();
	}

}
