package com.healthy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.entity.Producto;
import com.healthy.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repository;

	@Override
	public List<Producto> listaProducto() {
		return repository.findAll();
	}

	@Override
	public Optional<Producto> obtienePorId(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Producto> listaPorIngredientes(String ing) {
		return repository.listaProducto("%"+ing+"%");
	}
	
	@Override
	public List<String> listaPorIngredientes2(String ing) {
		return repository.listaProducto2("%"+ing+"%");
	}

	@Override
	public Producto insertaActualizaProducto(Producto obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaProducto(int id) {
		repository.deleteById(id);
	}
	
}
