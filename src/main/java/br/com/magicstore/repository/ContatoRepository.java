package br.com.magicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
