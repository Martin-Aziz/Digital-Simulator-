public interface Baustein {
	public boolean berechneErgebnis();

	public Gatter getType();

	public int getXAusgang();

	public int getYAusgang();

	public int getXEingang();

	public int getYEingang();

	public void setGatterEingang(Baustein eingang);

	public void setGatterAusgang(Baustein ausgang);

}