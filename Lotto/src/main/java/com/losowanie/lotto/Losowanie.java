package com.losowanie.lotto;

import java.util.Arrays;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Losowanie 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//liczba kuponów
	private int liczba_kuponow = 10;
	
	//wyniki losowania
	private int []wyniki = new int [6];
	
	//kupony
	private int [][]kupon = new int [liczba_kuponow][6];
	
	//ilość trafień
	private int []licznik = new int [liczba_kuponow];
	
	//informacja czy kupon wygrany
	private String []napis = new String[liczba_kuponow];
	
	//wartość wygranej
	private String []wygrana = new String[liczba_kuponow];
	
	//przypisanie wartości początkowych w kostruktorze
	public Losowanie()
	{
		// w zależności ile kuponów
		for(int k=0; k<liczba_kuponow; k++) 
		{
			napis[k] = "niewygrany.";
			licznik[k] = 0;
			wygrana[k] = "0";
		}
	}
	//losowanie wyników
	public void los()
	{
		int temp=-1,flaga=0;
		Random liczba = new Random();
		//losuj liczby i wypełniaj tablice wyniki
		for(int i=0; i<wyniki.length; i++) 
		{
			flaga=0;
			temp=liczba.nextInt(50);
			//jeśli liczba się powtarza to ponowanie losuj dla danego indeksu
			for(int j=0; j<i; j++) 
			{
				if(temp==wyniki[j])
				{
					flaga=1;
					break;
				}
			}
			if(flaga==0)
			{
				wyniki[i]=temp;
			}
			else
			{
				i--;
			}
		}
		//posortuj wyniki
		Arrays.sort(wyniki);
	}
	//losowanie kuponów
	public void kupon()
	{
		int temp=-1,flaga=0;
		Random liczba = new Random();
		// w zależności ile kuponów
		for(int k=0; k<liczba_kuponow; k++) 
		{
			//losuj liczby i wypełniaj tablice kupon
			for(int i=0; i<kupon[k].length; i++) 
			{
				temp=liczba.nextInt(50);
				flaga=0;
				//jeśli liczba się powtarza to ponowanie losuj dla danego indeksu
				for(int j=0; j<i; j++) 
				{
					if(temp==kupon[k][j])
					{
						flaga=1;
						break;
					}
				}
				if(flaga==0)
				{
					kupon[k][i]=temp;
				}
				else
				{
					i--;
				}
			}
			//posortuj kupony
			Arrays.sort(kupon[k]);
		}
		
	}
	//sprawdzanie wygranej
	public void sprawdz()
	{
		int pom;
		// w zależności ile kuponów
		for(int k=0; k<liczba_kuponow; k++) 
		{
			pom=0;
			for(int i=0; i<wyniki.length; i++) 
			{
				for(int j=pom; j<kupon[k].length; j++) 
				{
					//sprawdz czy liczba w kuponie jest większa niż w wyniku losowania, jeśli tak
					// to dzięki sortowaniu wez następna liczbę z wyników
					if(wyniki[i] <= kupon[k][j])
					{
						if(wyniki[i] == kupon[k][j])
						{
							licznik[k]++;
						}
							break;
					}	
					//jeśli liczba w kuponie jest mniejsza niż w wyniku losowania
					// to dzięki sortowaniu niw wracaj do tej liczby kuponu
					pom++;
				}
			}
			// jeżeli kupon ma 3 trafienia przypisz mu wygraną i wartość nagrody
			if(licznik[k] >= 3)
			{
				napis[k] = "wygrany.";
				switch (licznik[k])
				{
					case 3:
						wygrana[k] = "10";
						break;
					case 4:
						wygrana[k] = "15000";
						break;
					case 5:
						wygrana[k] = "90000";
						break;
					case 6:
						wygrana[k] = "1000000";
						break;
				}
			}
		}
	}	
	//resetowanie dla następnego losowania
	public void reset()
	{
		// w zależności ile kuponów
		for(int k=0; k<liczba_kuponow; k++) 
		{
			licznik[k] = 0;
			napis[k] = "niewygrany.";
			wygrana[k] = "0";
		}
	}
		
	//settery i gettery
	public int[] getWyniki() 
	{
		return wyniki;
	}
	
	public void setWyniki(int [] wyniki) 
	{
		this.wyniki = wyniki;
	}
	
	public int[][] getKupon() 
	{
		return kupon;
	}
	
	public void setKupon(int [][] kupon) 
	{
		this.kupon = kupon;
	}
	
	public int[] getLicznik() 
	{
		return licznik;
	}
	
	public void setLicznik(int[] licznik) 
	{
		this.licznik = licznik;
	}
	
	public String[] getNapis() 
	{
		return napis;
	}
	
	public void setNapis(String[] napis) 
	{
		this.napis = napis;
	}
	
	public String[] getWygrana() 
	{
		return wygrana;
	}
	
	public void setWygrana(String[] wygrana) 
	{
		this.wygrana = wygrana;
	}
	
	public int getLiczba_kuponow() 
	{
		return liczba_kuponow;
	}
	
	public void setLiczba_kuponow(int liczba_kuponow) 
	{
		this.liczba_kuponow = liczba_kuponow;
	}
}
