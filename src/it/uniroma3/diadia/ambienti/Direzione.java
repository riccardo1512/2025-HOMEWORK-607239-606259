package it.uniroma3.diadia.ambienti;

public enum Direzione {

	NORD(0) {

		@Override
		public Direzione opposta() {
			return SUD;
		}
	},
	EST(90) {

		@Override
		public Direzione opposta() {
			return OVEST;
		}
	},
	SUD(180) {

		@Override
		public Direzione opposta() {
			return NORD;
		}
	},
	OVEST(270) {

		@Override
		public Direzione opposta() {
			return EST;
		}
	};

	private final int gradi;

	private Direzione(int gradi) {

		this.gradi = gradi;
	}

	public int getGradi() {

		return this.gradi;
	}
	
	/**
	 * Restituisce la Direzione (enum) a partire dalla direzione passata come una stringa
	 * @param Stringa della direzione
	 * @return Direzione direzione (tipo enum)
	 * */
	public static final Direzione getDirezione(String direzione) {
		
		Direzione d = null;

		switch(direzione) {

			case "nord":
			d = Direzione.NORD;
			break;
			
			case "est":
			d = Direzione.EST;
			break;

			case "sud":
			d = Direzione.SUD;
			break;

			case "ovest":
			d = Direzione.OVEST;
			break;
		}
		
		return d;
	}

	public abstract Direzione opposta();
}
