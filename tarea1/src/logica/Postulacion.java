package logica;

import java.time.LocalDateTime;

import servidor.types.DTPostulacion;

public class Postulacion {

	private String nombreOfertaLaboral;
	private String nicknamePostulante;
	private String CVreducido;
	private String motivacion;
	private LocalDateTime fechaPublic;
	private String urlVideo;
	private int resultado;
	
	// Constructores
	public Postulacion(){
		this.nombreOfertaLaboral = new String();
		this.nicknamePostulante = new String();
		this.CVreducido = new String();
		this.motivacion = new String();
		this.fechaPublic = null;
		this.urlVideo = new String();
		this.resultado = 0;
	}
	
	public Postulacion(String nombreOfertaLaboral, String nicknamePostulante, String CVReducido, String motivacion,  LocalDateTime fechaPublic, String urlVideo ) {
		this.setNombreOfertaLaboral(nombreOfertaLaboral);
		this.setNicknamePostulante(nicknamePostulante);
		this.setCVReducido(CVReducido);
		this.setMotivacion(motivacion);
		this.setFechaPublic(fechaPublic);
		this.setVideo(urlVideo);
	}
	
    // Setters
    public void setNombreOfertaLaboral(String nombreOfertaLaboral) {
    	this.nombreOfertaLaboral = nombreOfertaLaboral;
    }

	public void setNicknamePostulante(String nicknamePostulante) {
    	this.nicknamePostulante = nicknamePostulante;
    }
    
    public void setCVReducido(String CVReducido) {
        this.CVreducido = CVReducido;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public void setFechaPublic(LocalDateTime fechaPublic) {
        this.fechaPublic = fechaPublic;
    }
    
    public void setVideo(String urlVid) {
    	this.urlVideo = urlVid;
    }
    
    public void setResultado(int res) {
    	this.resultado = res;
    }
    // Getters
    public String getNombreOfertaLaboral() {
    	return nombreOfertaLaboral;
    }
    
    public String getNicknamePostulante() {
    	return nicknamePostulante;
    }
    
    public String getCVReducido() {
        return CVreducido;
    }

    public String getMotivacion() {
        return motivacion;
    }
    
    public String getUrlVideo() {
        return urlVideo;
    }

    public LocalDateTime getFechaPublic() {
        return fechaPublic;
    }
    
    public int getResultado() {
    	return resultado;
    }
    
    /**
     * Retorna los datos de la postulacion como un DTPostulacion.
     */
    public DTPostulacion toDataType() {
    	return new DTPostulacion(getNicknamePostulante(), getNombreOfertaLaboral(), getFechaPublic(), getCVReducido(), getMotivacion(), getUrlVideo(), getResultado());
    }

}
