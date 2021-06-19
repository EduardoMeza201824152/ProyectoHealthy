package com.healthy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.entity.Producto;
import com.healthy.service.ProductoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/rest/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService service;

		//list
		@GetMapping
		public ResponseEntity<List<Producto>> lista(){
			log.info(">>>> lista ");
			List<Producto> lstProducto = service.listaProducto();
			return ResponseEntity.ok(lstProducto);
		}
		
		//find by id
		@GetMapping("/buscarPorId/{id}")
		public ResponseEntity<Optional<Producto>> buscar(@PathVariable("id") int id) {
			log.info(">>>> busca por id : " + id);
			Optional<Producto> optProducto = service.obtienePorId(id);
			if (optProducto.isPresent()) {
				return ResponseEntity.ok(optProducto);
			} else {
				log.info(">>>> busca por id - no existe Producto con id: " + id);
				return ResponseEntity.badRequest().build();
			}
		}
		
		//list by ingredientes
		@GetMapping("/buscarPorIngredientes/{ing}")
		public ResponseEntity<List<Producto>> listaPorIngredientes(@PathVariable("ing") String filtro){
			log.info(">>>> lista por ingredientes ");
			List<Producto> lstProducto = service.listaPorIngredientes(filtro);
			return ResponseEntity.ok(lstProducto);
		}
		
		//list by ingredientes ver.2
		@GetMapping("/buscarPorIngredientes2/{ing}")
		public ResponseEntity<List<String>> listaPorIngredientes2(@PathVariable("ing") String filtro){
			log.info(">>>> lista por ingredientes ver.2");
			List<String> lstString = service.listaPorIngredientes2(filtro);
			return ResponseEntity.ok(lstString);
		}
		
		//insert
		@PostMapping
		public ResponseEntity<Producto> registra(@RequestBody Producto obj){
			log.info(">>>> registra  " + obj.getId());
			Producto objSalida = service.insertaActualizaProducto(obj);
			if (objSalida != null) {
				return ResponseEntity.ok(objSalida);
			}else {
				log.info(">>>> registra - error al momento de registrar");
				return ResponseEntity.badRequest().build();
			}
		}
		
		//update
		@PutMapping
		public ResponseEntity<Producto> actualiza(@RequestBody Producto obj){
			log.info(">>>> actualiza  " + obj.getId());
			Optional<Producto> optProducto = service.obtienePorId(obj.getId());
			if (optProducto.isPresent()) {
				Producto objSalida = service.insertaActualizaProducto(obj);
				if (objSalida != null) {
					return ResponseEntity.ok(objSalida);
				}else {
					return ResponseEntity.badRequest().build();
				}	
			}else {
				log.info(">>>> actualiza - no existe Producto con id: " + obj.getId());
				return ResponseEntity.badRequest().build();
			}
		}
		
		//delete
		@DeleteMapping("/{id}")
		public ResponseEntity<Producto> elimina(@PathVariable("id") int id){
			log.info(">>>> elimina  " + id);
			Optional<Producto> optProducto = service.obtienePorId(id);
			if (optProducto.isPresent()) {
				service.eliminaProducto(id);
				return ResponseEntity.ok(optProducto.get());
			}else {
				log.info(">>>> elimina - no existe Producto con id: " + id);
				return ResponseEntity.badRequest().build();
			}
		}
	
}
