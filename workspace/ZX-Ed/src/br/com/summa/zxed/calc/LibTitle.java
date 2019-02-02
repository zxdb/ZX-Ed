package br.com.summa.zxed.calc;

import java.util.*;

import org.openxava.calculators.*;

import br.com.summa.sol.util.*;

@lombok.Setter
public class LibTitle implements ICalculator {
	private static final long serialVersionUID = 1L;

	private static final int MIN_LEN = 2;
	private static final int MAX_LEN = 4;
	private static final Set<String> prefixes = Sets.asHashSet(
			"3d ",
			"a ",
			"an ",
			"das ",
			"de ",
			"der ",
			"des ",
			"die ",
			"el ",
			"em ",
			"het ",
			"il ",
			"l'",
			"la ",
			"las ",
			"le ",
			"les ",
			"lo ",
			"los ",
			"o ",
			"the ",
			"una ",
			"une ");

    private String title;

	@Override
	public Object calculate() throws Exception {
		String names[] = title.split(" \\+ ");
		for (int i=0; i < names.length; i++) {
			names[i] = libraryName(names[i].trim());
		}
		return String.join(" + ", names);
	}

	public static String libraryName(String name) {
		for (int len=Integer.min(name.length()-1, MAX_LEN); len >= MIN_LEN; len--) {
			String prefix = name.substring(0, len);
			if (prefixes.contains(prefix.toLowerCase())) {
				return (name.substring(len)+", "+prefix).trim();
			}
		}
		return name;
	}
}
