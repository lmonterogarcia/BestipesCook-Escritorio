package model;

public class Noticia {
	private Integer idNoticia;
	private String fechaCreacionNoticia;
	private String tituloNoticia;
	private String subtituloNoticia;
	private String textoNoticia;
	private Integer imagenidImagen;

	public Noticia() {
	}

	public Noticia(Integer idNoticia, String fechaCreacionNoticia, String tituloNoticia, String subtituloNoticia,
			String textoNoticia, Integer imagenidImagen) {
		this.idNoticia = idNoticia;
		this.fechaCreacionNoticia = fechaCreacionNoticia;
		this.tituloNoticia = tituloNoticia;
		this.subtituloNoticia = subtituloNoticia;
		this.textoNoticia = textoNoticia;
		this.imagenidImagen = imagenidImagen;
	}

	public Integer getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getFechaCreacionNoticia() {
		return fechaCreacionNoticia;
	}

	public void setFechaCreacionNoticia(String fechaCreacionNoticia) {
		this.fechaCreacionNoticia = fechaCreacionNoticia;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getSubtituloNoticia() {
		return subtituloNoticia;
	}

	public void setSubtituloNoticia(String subtituloNoticia) {
		this.subtituloNoticia = subtituloNoticia;
	}

	public String getTextoNoticia() {
		return textoNoticia;
	}

	public void setTextoNoticia(String textoNoticia) {
		this.textoNoticia = textoNoticia;
	}

	public Integer getImagenidImagen() {
		return imagenidImagen;
	}

	public void setImagenidImagen(Integer imagenidImagen) {
		this.imagenidImagen = imagenidImagen;
	}

	@Override
	public String toString() {
		return "Noticia [idNoticia=" + idNoticia + ", fechaCreacionNoticia=" + fechaCreacionNoticia + ", tituloNoticia="
				+ tituloNoticia + ", subtituloNoticia=" + subtituloNoticia + ", textoNoticia=" + textoNoticia
				+ ", imagenidImagen=" + imagenidImagen + "]";
	}
}
