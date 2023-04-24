package com.appApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appApi.entity.produtos;

public interface ProdutosRepositories extends JpaRepository<produtos, Long>{

}
