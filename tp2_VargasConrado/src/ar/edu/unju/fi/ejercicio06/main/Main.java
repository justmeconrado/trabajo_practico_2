package ar.edu.unju.fi.ejercicio06.main;

import ar.edu.unju.fi.ejercicio06.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio06.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio06.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		// Crear objeto felino salvaje
		FelinoSalvaje tanner = new FelinoSalvaje();
		tanner.setNombre("Tanner");
		tanner.setEdad((byte) 20);
		tanner.setPeso(186f);

		// Verificar que el objeto a convertir no es nulo
		if (Converter.isNotNul(tanner)) {
			// Definir expresión lambda para convertir felino salvaje a doméstico
			Converter<FelinoSalvaje, FelinoDomestico> converter = x -> {
				FelinoDomestico felinoDomestico = new FelinoDomestico();
				felinoDomestico.setNombre(x.getNombre());
				felinoDomestico.setEdad(x.getEdad());
				felinoDomestico.setPeso(x.getPeso());
				return felinoDomestico;
			};

			// Realizar la conversión
			FelinoDomestico felinoDomestico = converter.convert(tanner);

			// Mostrar los datos del objeto felino doméstico
			converter.mostrarObjeto(felinoDomestico);
		}
	}

}
