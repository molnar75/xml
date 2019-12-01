package beadando;

public class Etterem {
	
	private long id;
	private String nev;
	private String weblap;
	private String minosites;
	private String telefonszam;

	public Etterem(long id, String nev, String weblap, String minosites, String telefonszam) {
		super();
		this.id = id;
		this.nev = nev;
		this.weblap = weblap;
		this.minosites = minosites;
		this.telefonszam = telefonszam;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getWeblap() {
		return weblap;
	}

	public void setWeblap(String weblap) {
		this.weblap = weblap;
	}

	public String getMinosites() {
		return minosites;
	}

	public void setMinosites(String minosites) {
		this.minosites = minosites;
	}

	public String getTelefonszam() {
		return telefonszam;
	}

	public void setTelefonszam(String telefonszam) {
		this.telefonszam = telefonszam;
	}
}
