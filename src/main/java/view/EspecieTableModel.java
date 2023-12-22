package View;

import java.util.List;
import model.Especie;
import model.EspecieDAO;

/**
 *
 * @author Antonio Rezende
 */
public class EspecieTableModel extends GenericTableModel {

    public EspecieTableModel(List vDados){
        super(vDados, new String[]{"id", "nome"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Especie especie = (Especie) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return especie.getId();
            case 1:
                return especie.getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Especie especie = (Especie) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                especie.setId((int)aValue);
            case 1:
                especie.setNome((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
        
        EspecieDAO.getInstance().update(especie);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}