package Utils;
import java.util.ArrayList;

public class ListaUtils {
	public static <T> ArrayList<T> diferenca(ArrayList<T> list1, ArrayList<T> list2) {
		ArrayList<T> list = list1;
		list.removeAll(list2);
		return list;
	}
	
	public static <T> ArrayList<T> interseccao(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

	public static <T> ArrayList<T> listaAdicionar(ArrayList<T> antiga, ArrayList<T> nova) {
		return diferenca(nova, antiga);
	}

	public static <T> ArrayList<T> listaDeletar(ArrayList<T> antiga, ArrayList<T> nova) {
		return diferenca(antiga, nova);
	}
}