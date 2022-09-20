package jdbc;

import java.util.List;

public interface DAO<E,K> {

	public E buscarPorClavePrimaria(K k);
	
	public boolean insertar(E e);
	
	public boolean actualizar(E e);
	
	public boolean eliminar(E e);
	
	public List<E> listar();
	
}
