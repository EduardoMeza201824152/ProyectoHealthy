package com.healthy.service;

import java.util.List;
import java.util.Optional;

import com.healthy.entity.Producto;


public interface ProductoService {

	public abstract List<Producto> listaProducto();
	public abstract Optional<Producto> obtienePorId(int id);
	public abstract List<Producto> listaPorIngredientes(String ing);
	public abstract List<String> listaPorIngredientes2(String ing);
	public abstract Producto insertaActualizaProducto(Producto obj);
	public abstract void eliminaProducto(int id);
	
}
