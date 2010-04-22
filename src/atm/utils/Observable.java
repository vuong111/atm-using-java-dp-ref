package atm.utils;

public interface Observable {
	public void addObserver(Observer o);
	public void deleteObserver(Observer o);
	public void deleteObservers();
	public void notifyObservers();
}
