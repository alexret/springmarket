package com.example.demo.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ImagenRepository;
import com.example.demo.entidades.Imagen;

public class ImagenServicioImpl implements ImagenServicio {
	
	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private ProductoServicioImp productoServicioImp;

	@Override
	public int guardarImagen(Imagen img) {
		try {
			imagenRepository.save(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Imagen obtenerImagen(Long id) {
		Imagen findById = imagenRepository.findById(id).orElse(null);
		
		if (findById != null) {
			Imagen getImageDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	@Override
	public Boolean actualizarImagen(long idProducto, MultipartFile file) {
		Producto p = productoServicioImp.findById(idProducto).orElse(null);

		if (p == null)
			return false;
		try {
			byte[] image = file.getBytes();
			if (!p.getImagen().isEmpty()) {

				Set<Imagen> limg = p.getImagen();
				for (Imagen a : limg) {
					a.setImagen(image);
					imagenRepository.save(a);
					return true;
				}
				return null;

			} else {
				Imagen img = new Imagen("foto", image);
				p.addImagen(img);
				productoServicioImp.save(p);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
