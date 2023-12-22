package View;

import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Especie;
import model.EspecieDAO;

/**
 *
 * @author Antonio Rezende
 */
public class AnimalTableModel extends GenericTableModel {

    public AnimalTableModel(List vDados){
        super(vDados, new String[]{"id", "nome", "idade", "sexo", "especie"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return animal.getId();
            case 1:
                return animal.getNome();
            case 2:
                return animal.getIdade();
            case 3:
                return animal.getSexo();
            case 4:
                Especie especie = EspecieDAO.getInstance().retrieveById(animal.getId_especie());
                if (especie != null) {
                    return especie.getNome();
                }
                return "";
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                animal.setId((int)aValue);
                break;
            case 1:
                animal.setNome((String)aValue);
                break;
            case 2:
                animal.setIdade((String)aValue);
                break;
            case 3:
                animal.setSexo((String)aValue);
                break;
            case 4:
                Especie especie = EspecieDAO.getInstance().retrieveById((int)aValue);
                if ( especie == null ) {
                    especie = EspecieDAO.getInstance().create((String)aValue);
                }
                animal.setId_especie(especie.getId());
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
        
        AnimalDAO.getInstance().update(animal);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}