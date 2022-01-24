package model;

public class Categoria {
	private int idCategoria;
	private String nombreCategoria;
	private boolean challengue;
	
	public Categoria(int idCategoria) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = "";
		this.challengue = false;
	}
	
	public Categoria(int idCategoria, String nombreCategoria) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.challengue = false;
	}
	
	public Categoria(int idCategoria, String nombreCategoria, boolean challengue) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.challengue = challengue;
	}


	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public boolean isChallengue() {
		return challengue;
	}

	public void setChallengue(boolean challengue) {
		this.challengue = challengue;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", challengue="
				+ challengue + "]";
	}
}
