package com.appApi.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appApi.entity.vo.produtoVO;
import com.appApi.service.ProdutosService;

@RestController
@RequestMapping(value = "/api/prod")
public class ProdutosController {

	@Autowired
	private ProdutosService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public produtoVO saveC(@RequestBody produtoVO prod){
		
		return	service.create(prod);
		
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<PagedModel<EntityModel<produtoVO>>> findAll(
			@RequestParam(value="page", defaultValue = "0")Integer page,
			@RequestParam(value="size", defaultValue = "10")Integer size,
			@RequestParam(value="direction", defaultValue = "asc")String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nome"));
		
		return ResponseEntity.ok(service.findPage(pageable));
		
	}
	
	@GetMapping
	public List<produtoVO> findAlls(){
		
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public produtoVO findById(@PathVariable(value="id") long id) {
		
		return service.findById(id);
		
	}
	
	@PutMapping
	public produtoVO update(@RequestBody produtoVO prod) {
		return service.update(prod);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}


