package com.appApi.service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.appApi.Repositories.ProdutosRepositories;
import com.appApi.controller.ProdutosController;
import com.appApi.entity.produtos;
import com.appApi.entity.vo.produtoVO;

@Service
public class ProdutosService {

	
	
	ModelMapper model = new ModelMapper();
	
	@Autowired
	ProdutosRepositories repo;
	
	@Autowired
	PagedResourcesAssembler<produtoVO> assembler;
	
	public PagedModel<EntityModel<produtoVO>> findPage(Pageable pageable){
		
		var entityPage = repo.findAll(pageable);
		
		var voPage = entityPage.map(p -> model.map(p, produtoVO.class));
		
		voPage.map(p -> {
			try {
				return p.add(linkTo(methodOn(ProdutosController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		});
		
		Link link = linkTo(methodOn(ProdutosController.class)
				.findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(voPage, link);
		
	}
	
	public List<produtoVO> findAll(){
		
		List<produtoVO> list = new ArrayList<produtoVO>();
		
		for(produtos prods : repo.findAll()) {
			list.add(model.map(prods, produtoVO.class));
		}
		
		list
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(ProdutosController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			});
		
		return  list;
		
	}
	
	public produtoVO findById(long id) {
		
		var vo = model.map(repo.findById(id), produtoVO.class);
		
		vo.add(linkTo(methodOn(ProdutosController.class).findById(id)).withSelfRel());
		
		return vo;
		
	}
	
	public produtoVO create(produtoVO prod) {
		
		if(prod == null) {
			System.out.println("Adicione todos os campos!!");
		}
		
		var entity = model.map(prod, produtos.class);
		
		var vo = model.map(repo.save(entity), produtoVO.class);
		
		vo.add(linkTo(methodOn(ProdutosController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public produtoVO update(produtoVO prod) {
		
		if(prod == null) {
			System.out.println("Adicione todos os campos!!");
		}
		
		var entity = repo.findById(prod.getKey()).orElseThrow();
		
		entity.setNome(prod.getNome());
		entity.setMarca(prod.getMarca());
		entity.setFabricacao(prod.getFabricacao());
		entity.setValidade(prod.getValidade());
		entity.setCategoria(prod.getCategoria());
		entity.setPreco(prod.getPreco());
		
		var vo = model.map(repo.save(entity), produtoVO.class);
		
		vo.add(linkTo(methodOn(ProdutosController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(long id) {
		
		var entity = repo.findById(id).orElseThrow();
		
		repo.delete(entity);
		
	}
	
	
}






