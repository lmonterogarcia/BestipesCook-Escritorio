package model;

public class Reto {
	private Integer idReto;
	private String fechaCreacionReto;
	private String tituloReto;
	private String subtituReto;
	private String textoReto;
	private String fechaFinalizacionReto;
	private Imagen oImagen;
	
	public Reto() {
	}
	
	public Reto(Integer idReto, String fechaCreacionReto, String tituloReto, String subtituReto, String textoReto,
			String fechaFinalizacionReto, Imagen oImagen) {
		this.idReto = idReto;
		this.fechaCreacionReto = fechaCreacionReto;
		this.tituloReto = tituloReto;
		this.subtituReto = subtituReto;
		this.textoReto = textoReto;
		this.fechaFinalizacionReto = fechaFinalizacionReto;
		this.oImagen = oImagen;
	}

	public Integer getIdReto() {
		return idReto;
	}

	public void setIdReto(Integer idReto) {
		this.idReto = idReto;
	}

	public String getFechaCreacionReto() {
		return fechaCreacionReto;
	}

	public void setFechaCreacionReto(String fechaCreacionReto) {
		this.fechaCreacionReto = fechaCreacionReto;
	}

	public String getTituloReto() {
		return tituloReto;
	}

	public void setTituloReto(String tituloReto) {
		this.tituloReto = tituloReto;
	}

	public String getSubtituReto() {
		return subtituReto;
	}

	public void setSubtituReto(String subtituReto) {
		this.subtituReto = subtituReto;
	}

	public String getTextoReto() {
		return textoReto;
	}

	public void setTextoReto(String textoReto) {
		this.textoReto = textoReto;
	}

	public String getFechaFinalizacionReto() {
		return fechaFinalizacionReto;
	}

	public void setFechaFinalizacionReto(String fechaFinalizacionReto) {
		this.fechaFinalizacionReto = fechaFinalizacionReto;
	}

	public Imagen getoImagen() {
		return oImagen;
	}

	public void setoImagen(Imagen oImagen) {
		this.oImagen = oImagen;
	}

	@Override
	public String toString() {
		return "Reto [idReto=" + idReto + ", fechaCreacionReto=" + fechaCreacionReto + ", tituloReto=" + tituloReto
				+ ", subtituReto=" + subtituReto + ", textoReto=" + textoReto + ", fechaFinalizacionReto="
				+ fechaFinalizacionReto + ", oImagen=" + oImagen + "]";
	}
}
