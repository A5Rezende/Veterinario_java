package View;

import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Tratamento;
import model.TratamentoDAO;

/**
 *
 * @author Gritti
 */
public class TratamentoTableModel extends GenericTableModel{
    
    public TratamentoTableModel(List vDados) {
        super(vDados, new String[]{"Id", "animal", "data de inicio", "data de final", "Finalizado"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                return String.class;
        }
    }
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tratamento.getId(); 
            case 1:
                Animal animal = AnimalDAO.getInstance().retrieveById(tratamento.getId_animal());
                if (animal != null) {
                    return animal.getNome();
                }
                return "";
            case 2:
                return tratamento.getData_inicio();
            case 3:
                return tratamento.getData_final();
            case 4:
                return tratamento.getFinalizado();
            default:
                return "";
        }

    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                tratamento.setId((int) aValue);
                break;
            case 1:
                tratamento.setId_animal((int) aValue);
                break;
            case 2:
                tratamento.setData_inicio((String) aValue);
                break;
            case 3:
                tratamento.setData_final((String) aValue);
                break;
            case 4:
                tratamento.setFinalizado((String) aValue);
                break;
            default:
                break;
        }

        TratamentoDAO.getInstance().update(tratamento);
    }
    
        @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}