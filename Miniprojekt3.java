
public class Miniprojekt3 {
	
	public void zeigeSkigebiet(Station[] skigebiet) {
		// Gib das ganze Skigebiet zeilenweise in Textform aus. Durchlaufe hierfür das Array
		// skigebiet und gib für jede Station alle dort ausgehenden Liftverbindung (Array lifte) und 
		// alle dort ausgehenden Pisten (Array pisten) mit ihrem jeweiligen Ziel aus.
		//
		// KEINE BEWERTUNG DURCH JACK
		for (int i = 0; i < skigebiet.length; i ++){
			System.out.println( skigebiet[i].name);
			if (skigebiet[i].lifte!=null){
				for (int j = 0; j < skigebiet[i].lifte.length; j++){
					System.out.println(skigebiet[i].lifte[j].name + "->" + skigebiet[i].lifte[j].ziel.name);
				}
			}
			if(skigebiet[i].pisten != null){
				for (int a = 0; a < skigebiet[i].pisten.length; a++){
					System.out.println(skigebiet[i].pisten[a].name + "->" +  skigebiet[i].pisten[a].ziel.name);
				}
			}
			
		}
		 
	}
		
		
		//return; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	
	
	public void zeigeRoute(RElement r) {
		// Gib zeilenweise alle Lifte und Pisten auf der Route r in der gegebenen Reihenfolge aus.
		//
		// KEINE BEWERTUNG DURCH JACK
		System.out.println("----------2---------");
		while (r != null){
			if( r.lift != null){System.out.println(r.lift.name);}		
			else if(r.piste != null){System.out.println(r.piste.name);}
			r = r.nF;
		}
		
		//return; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public int offeneLifte(Station[] skigebiet) {
		// Zähle, wie viele derzeit offene Lifte es im Skigebiet gibt und liefere
		// diesen Wert zurück.
		int counter = 0;
		for(int i = 0; i < skigebiet.length;i++){
			if(skigebiet[i].lifte != null){
				for(int j = 0; j < skigebiet[i].lifte.length;j++){
					if(skigebiet[i].lifte[j].status == true){
						counter++;
					}
				}
				
			}
		}
		return counter;
	}
	
	public int pistenMeter(Station[] skigebiet) {
		// Liefere die insgesamt vorhandenen Pistenmeter im Skigebiet (unabhängig davon, ob
		// die Pisten gesperrt oder offen sind).
		int meter = 0 ;
		for(int i = 0; i < skigebiet.length ;i++){
			if(skigebiet[i].pisten != null){
				for(int j = 0; j < skigebiet[i].pisten.length;j++){
					meter += skigebiet[i].pisten[j].laenge;
				}
			}
		}
		
		return meter;
	}
	
	public int verbrauchtePunkte(RElement r) {
		int counter = 0 ;
		while( r != null){
			if(r.lift != null){counter += r.lift.punkte;}
			r =r.nF;
		}
		
		return counter;
	}
	
	public char maxFarbe(RElement r) {
		// Liefere die auf der Route r maximal vorkommende Pisten-Schwierigkeit, entsprechend
		// ihrer Farbe: b)lau = leicht, r)ot = mittel, s)chwarz = schwer.
		// Hinweis: Da 'b' im Alphabet vor 'r' kommt und 'r' vor 's', können Sie die
		// Vergleichsoperatoren <, >, = für den character-Wert "farbe" genau wie beim Vergleich
		// von Zahlen verwenden!
		char max = 'a';
		while( r != null){
			if(r.piste != null && r.piste.farbe >max)max = r.piste.farbe;
				r = r.nF;
			}
		
		return max; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public int maxHoehe(RElement r) {
		// Liefere die maximal auf der Route r erreichte Höhe ALS DIFFERENZ VOM START DER ROUTE AUS
		// GEMESSEN.
		// Achtung: Dieser Wert hängt sowohl von den gefahrenen Pisten als auch von den benutzten
		// Liften ab!
		int max = 0; 
		int diff = 0;
		while(r != null){
			if(r.piste != null) diff += r.piste.hdiff;
			else diff += r.lift.hdiff;
			if(diff > max)max =diff;
			r=r.nF;
		}
		return max;
	}
	
	public int anzahlHuetten(RElement r) {
		// Liefere die Anzahl aller auf der Route r erreichten Hütten.
		// Bitte beachten Sie:
		// *) Die Startstation kann ignoriert werden.
		// *) Wenn die Route an einer bereits zuvor erreichten Hütte erneut vorbeiführt,
		//    treten Mehrfachzählungen auf. Das ist im Kontext dieser Aufgabe ok!
		int counter = 0;
		while( r != null){
			if((r.lift != null && r.lift.ziel.huette)|| (r.piste != null && r.piste.ziel.huette)){
				counter++;
			}
			r = r.nF;
		}
		return counter; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public boolean parallelePistenVon(Station s) {
		// Prüfe, ob es von Station s AUSGEHEND mindestens zwei Pisten mit gleichem Ziel gibt.
		if(s.pisten != null){
			for(int i = 0; i <s.pisten.length;i++){
				for(int j = 0; j < s.pisten.length;j++){
					if(s.pisten[i].ziel == s.pisten[j].ziel && i != j){
						return true;
					}
					
					
				}
			}
		}
		
		 return false;// Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public boolean keinLiftAbwaertsZu(Station[] skigebiet, Station s) {
		// Liefert true, wenn kein zu Station s HINFÜHRENDER Lift abwärts geht (hdiff < 0).
		for(int i = 0 ; i <skigebiet.length;i++){
			if(skigebiet[i].lifte != null){
				for(int j = 0; j <skigebiet[i].lifte.length;j++){
					if(skigebiet[i].lifte[j].ziel == s){
						if(skigebiet[i].lifte[j].hdiff < 0 ){
							return false;
					}
				
				}
			}
		}
		}	
		
		return true; // Bitte durch Ihre Lösung ersetzen!
	}
	
	public boolean hatAnfaengerRoute(Station s) {
		// Liefert true, falls es von Station s ausgehend einen Lift gibt, an dessen Ziel
		// eine blaue Piste beginnt, die wiederum direkt zu s zurückführt ODER umgekehrt.
		if(s.lifte != null){
			for(int i = 0; i< s.lifte.length;i++){
				if(s.lifte[i].ziel != null){
					for(int j = 0; j< s.lifte[i].ziel.pisten.length;j++){
						if(s.lifte[i].ziel.pisten[j].ziel == s && s.lifte[i].ziel.pisten[j].farbe == 'b') return true;
					}
				}
			}
		}
		
		if(s.pisten != null){
			for(int i = 0; i< s.pisten.length;i++){
				if(s.pisten[i].ziel.lifte != null){
					for(int j = 0; j< s.pisten[i].ziel.lifte.length;j++){
						if(s.pisten[i].ziel.lifte[j].ziel == s && s.pisten[i].farbe == 'b') return true;
					}
				}
			}
		}
		
		return false; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public int befahrbarBis(RElement r) {
		// Liefere die Anzahl der Pistenmeter, bis zu der die Route r befahrbar (also nicht
		// gesperrt) ist.
		int meter = 0 ;
		while(r != null){
			if(r.piste != null) if(r.piste.status) meter += r.piste.laenge;
			r = r.nF;
		}
		return meter; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	public boolean sindDisjunkt(RElement r1, RElement r2) {
		// Liefere true, falls die beiden Routen r1 und r2 disjunkt sind, d. h. kein Lift sowohl
		// in r1 und r2 vorkommt und keine Piste sowohl in r1 als auch r2 vorkommt.
		while(r1 != null){
			RElement tmp =r2;
			while (tmp != null){
				if((r1.lift !=null && r1.lift ==tmp.lift) || r1.piste !=null && r1.piste == tmp.piste) return false;
				tmp = tmp.nF;
			}
			r1 = r1.nF;
		}
		return true; // Bitte ersetzen Sie diese Zeile durch Ihre Lösung!
	}
	
	
		
	public static void main(String[] args) {
		// alle Stationen
		Station sBrixen = new Station("Brixen", false);
		Station sChoralpe = new Station("Choralpe", true);
		Station sFilzboden = new Station("Filzboden", false);
		Station sOberberg = new Station("Oberberg", false);
		Station sZinsberg = new Station("Zinsberg", false);
		Station sSeidlalm = new Station("Seidlalm", true);
		
		// alle Pisten
		Piste pQuerverbindung = new Piste("Querverbindung", 'r', 800, -200, true, sFilzboden);
		Piste pBuckelpiste = new Piste("Buckelpiste", 's', 500, -200, false, sZinsberg);
		Piste pZinsbergAbfahrt = new Piste("Zinsberg-Abfahrt", 'b', 800, -300, false, sChoralpe);
		Piste pSeidlalmAbfahrt = new Piste("Seidlalm-Abfahrt", 'r', 3800, -400, true, sSeidlalm);
		Piste pTalabfahrtMittel = new Piste("Talabfahrt (mittel)", 'r', 4000, -600, true, sBrixen);
		Piste pTalabfahrtLeicht = new Piste("Talabfahrt (leicht)", 'b', 4200, -600, true, sBrixen);
		
		// alle Lifte
		Lift lGondelbahnAuf = new Lift("Gondelbahn (auf)", 30, 1800, 500, true, sChoralpe);
		Lift lGondelbahnAb = new Lift("Gondelbahn (ab)", 30, 1800, -500, true, sBrixen);
		Lift lOberbergLift = new Lift("Oberberg-Lift", 16, 1700, 700, true, sOberberg);
		Lift lZinsbergLift = new Lift("Zinsberg-Lift", 14, 400, 300, false, sZinsberg);
		
		// ausgehende Lifte und Pisten pro Station
		Lift[] alBrixen = new Lift[]{lGondelbahnAuf};
		Piste[] apBrixen = null;
		Lift[] alChoralpe = new Lift[]{lZinsbergLift, lGondelbahnAb};
		Piste[] apChoralpe = new Piste[]{pQuerverbindung};
		Lift[] alZinsberg = null;
		Piste[] apZinsberg = new Piste[]{pZinsbergAbfahrt};
		Lift[] alFilzboden = new Lift[]{lOberbergLift};
		Piste[] apFilzboden = null;
		Lift[] alOberberg = null;
		Piste[] apOberberg = new Piste[]{pBuckelpiste, pSeidlalmAbfahrt};
		Lift[] alSeidlalm = null;
		Piste[] apSeidlalm = new Piste[]{pTalabfahrtMittel, pTalabfahrtLeicht};
		
		// ausgehende Lifte und Pisten für alle Stationen
		Lift[][] ausgLifte = new Lift[][]{alBrixen, alChoralpe, alZinsberg, alFilzboden, alOberberg, alSeidlalm};
		Piste[][] ausgPisten = new Piste[][]{apBrixen, apChoralpe, apZinsberg, apFilzboden, apOberberg, apSeidlalm};
		
		// erstelle Skigebiet als Array aller Stationen und setze ausgehende Lifte und Pisten für jede Station
		Station[] skigebiet = new Station[]{sBrixen, sChoralpe, sZinsberg, sFilzboden, sOberberg, sSeidlalm};
		for (int is = 0; is < skigebiet.length; is++) {
			skigebiet[is].setzeAusgehendeLifte(ausgLifte[is]);
			skigebiet[is].setzeAusgehendePisten(ausgPisten[is]);
		}
		
		// erstelle Miniprojekt
		Miniprojekt3 m = new Miniprojekt3();
		
		// Beispielroute
		RElement r = new RElement(new Piste[]{pZinsbergAbfahrt},
				                  new Lift[]{lGondelbahnAuf, lZinsbergLift, lGondelbahnAb},
		                    	  new boolean[]{false, false, true, false});


		// An dieser Stelle können Sie unabhängig von JACK Ihre Methoden testen.
		// Anhand der Skizze auf dem Aufgabenblatt sowie den oben in der Klasse Miniprojekt3
		// erstellten Objekten für Lifte, Stationen und Pisten (ergänzen Sie bei Bedarf die
		// Skizze um Höhenangaben etc.) können Sie leicht von Hand ermitteln, welche Ergebnisse
		// Ihre Methoden für bestimmte Parameterwerte liefern müssten.
		// 
		m.zeigeSkigebiet(skigebiet);
		m.zeigeRoute(r);
		System.out.println(m.offeneLifte(skigebiet));
		System.out.println(m.pistenMeter(skigebiet));
		System.out.println(m.verbrauchtePunkte(r));
		System.out.println(m.maxFarbe(r));
		System.out.println(m.maxHoehe(r));
		System.out.println(m.anzahlHuetten(r));
		System.out.println(m.parallelePistenVon(sOberberg));
		System.out.println(m.keinLiftAbwaertsZu(skigebiet, sOberberg));
		System.out.println(m.hatAnfaengerRoute(sChoralpe));
		System.out.println(m.befahrbarBis(r));
		//System.out.println(m.sindDisjunkt(r, r1));
	}

}
