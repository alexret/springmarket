package com.example.demo.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RespuestasDao;
import com.example.demo.entidades.Pregunta;
import com.example.demo.entidades.PreguntaDTO;
import com.example.demo.entidades.Respuesta;
import com.example.demo.entidades.RespuestaDTO;
import com.example.demo.entidades.User;

@Transactional
@Service
public class RespuestaServicioImp implements RespuestaServicio {
	
	@Autowired
	private RespuestasDao respuestaDao;

	@Override
	public List<Respuesta> listarRespuestas(Pregunta pregunta) {
		return respuestaDao.listarRespuestasPorPregunta(pregunta);
	}

	@Override
	public Respuesta crearRespuesta(String texto, User usuario, Pregunta idPregunta) {
		Respuesta respuesta= new Respuesta();
		respuesta.setRespuesta(texto);
		respuesta.setUsuario(usuario);
		respuesta.setIdPregunta(idPregunta);
		respuestaDao.crear(respuesta);
		return respuesta;
	}

	@Override
	public RespuestaDTO conversionDTO(Respuesta respuesta) {

		RespuestaDTO preguntaDTO = new RespuestaDTO(respuesta.getIdRespuesta(), respuesta.getRespuesta(), respuesta.getUsuario().getNombre(), respuesta.getPregunta().getIdPregunta());
		return preguntaDTO;
	}

	

}
