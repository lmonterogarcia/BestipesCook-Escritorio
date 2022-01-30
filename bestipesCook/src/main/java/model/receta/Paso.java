package model.receta;

import model.Imagen;

public class Paso {
	
	// PK
	private int iIdPaso;
	//NN
	private String sTextoPaso;
	private byte bOrdenPAso;
	private Receta receta;
	//N
	private Imagen imagen;
	
	public Paso() {
	}

	public Paso(int iIdPaso) {
		this.iIdPaso = iIdPaso;
	}
	
	

	public Paso(int iIdPaso, String sTextoPaso, byte bOrdenPAso, Receta receta, Imagen imagen) {
		super();
		this.iIdPaso = iIdPaso;
		this.sTextoPaso = sTextoPaso;
		this.bOrdenPAso = bOrdenPAso;
		this.receta = receta;
		this.imagen = imagen;
	}
	
	
	
	
	

}
