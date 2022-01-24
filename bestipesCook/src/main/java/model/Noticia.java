package model;

public class Noticia {
	private Integer idNoticia;
	private String fechaCreacionNoticia;
	private String tituloNoticia;
	private String subtituloNoticia;
	private String textoNoticia;
	private Imagen oImagen;

	public Noticia() {
	}

	public Noticia(Integer idNoticia, String fechaCreacionNoticia, String tituloNoticia, String subtituloNoticia,
			String textoNoticia, Imagen oImagen) {
		this.idNoticia = idNoticia;
		this.fechaCreacionNoticia = fechaCreacionNoticia;
		this.tituloNoticia = tituloNoticia;
		this.subtituloNoticia = subtituloNoticia;
		this.textoNoticia = textoNoticia;
		this.oImagen = oImagen;
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

	public Imagen getoImagen() {
		return oImagen;
	}

	public void setoImagen(Imagen oImagen) {
		this.oImagen = oImagen;
	}

	@Override
	public String toString() {
		return "Noticia [idNoticia=" + idNoticia + ", fechaCreacionNoticia=" + fechaCreacionNoticia + ", tituloNoticia="
				+ tituloNoticia + ", subtituloNoticia=" + subtituloNoticia + ", textoNoticia=" + textoNoticia
				+ ", oImagen=" + oImagen + "]";
	}
}
