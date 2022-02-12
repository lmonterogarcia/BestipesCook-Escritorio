package model;

import java.time.LocalDateTime;

public class Imagen {
	private Integer idImagen;
	private String fechaCreacionImagen;
	private LocalDateTime fechaCreacionImagenldt;
	private String rutaRelativaImagen;
	
	public Imagen() {
	}
	
	public Imagen(Integer idImagen, String fechaCreacionImagen, String rutaRelativaImagen) {
		this.idImagen = idImagen;
		this.fechaCreacionImagen = fechaCreacionImagen;
		this.rutaRelativaImagen = rutaRelativaImagen;
	}
	
	public Imagen(Integer idImagen, LocalDateTime fechacreacionImagenldt, String rutaRelativaImagen) {
		this.idImagen = idImagen;
		this.fechaCreacionImagenldt = fechacreacionImagenldt;
		this.rutaRelativaImagen = rutaRelativaImagen;
	}

	public Integer getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public String getFechaCreacionImagen() {
		return fechaCreacionImagen;
	}

	public void setFechaCreacionImagen(String fechaCreacionImagen) {
		this.fechaCreacionImagen = fechaCreacionImagen;
	}

	public String getRutaRelativaImagen() {
		return rutaRelativaImagen;
	}

	public void setRutaRelativaImagen(String rutaRelativaImagen) {
		this.rutaRelativaImagen = rutaRelativaImagen;
	}

	@Override
	public String toString() {
		return "Imagen [idImagen=" + idImagen + ", fechaCreacionImagen=" + fechaCreacionImagen + ", rutaRelativaImagen="
				+ rutaRelativaImagen + "]";
	}
}
