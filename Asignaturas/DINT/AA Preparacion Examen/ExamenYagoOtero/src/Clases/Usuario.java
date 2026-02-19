package Clases;

public class Usuario {
	private String dni;
	private String nombre;
	private String apellidos;
	private int edad;
	private boolean isDentro;
	
	public Usuario(String dni, String nombre, String apellidos, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.isDentro=false;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}
	
	public boolean isDentro() {
		return isDentro;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void setDentro(boolean estado) {
		this.isDentro=estado;
	}

	@Override
	public String toString() {
		if(isDentro) {
			return "DENTRO - [DNI: "+dni + " - NOMBRE: "+ nombre + " - APELLIDOS:" + apellidos+ "]";
		}else {
			return "FUERA - [DNI: "+dni + " - NOMBRE: "+ nombre + " - APELLIDOS:" + apellidos+ "]";
		}
	}

}
