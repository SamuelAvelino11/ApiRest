package com.appApi.mapper;

import java.util.List;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("mapperUtil")
public class MapperUtil {

	protected ModelMapper modelMapper;
	
	public MapperUtil() {
		this.modelMapper = new ModelMapper();
	}
	
	public <S,D> D ToMap(S sourcer, Class<D> destClass) {
		return this.modelMapper.map(sourcer, destClass);
	}
	
	public <S,D> List<D> ToList(List<S> sourcer, Type destClass) {
		return this.modelMapper.map(sourcer, destClass);
	}
	
}
