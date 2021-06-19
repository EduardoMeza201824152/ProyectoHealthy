package com.healthy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthy.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

		//no olvidar que @Query toma los nombres del bean de la clase (Producto.java)
		//esto hace que no funcione si le pones los nombres de la BD
		@Query("select a from Producto a where a.ingredientes like :param_filtro")
		public abstract List<Producto> listaProducto(@Param("param_filtro") String filtro);
		
		@Query("select CONCAT(a.id,' - ',a.nombre) from Producto a where a.ingredientes like :param_filtro")
		public abstract List<String> listaProducto2(@Param("param_filtro") String filtro);
		
		public abstract Optional<Producto> findById(int id);;
	
}
