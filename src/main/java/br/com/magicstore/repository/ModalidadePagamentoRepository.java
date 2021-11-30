package br.com.magicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.ModalidadePagamento;

@Repository
public interface ModalidadePagamentoRepository extends JpaRepository<ModalidadePagamento, Long>{

	@Query("SELECT mp FROM ModalidadePagamento mp WHERE mp.nome = :credito OR mp.nome = :debito")
	List<ModalidadePagamento> findCreditoAndDebito(String credito, String debito);
	
}
