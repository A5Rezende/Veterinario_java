package View;

import java.util.List;
import model.Consulta;
import model.ConsultaDAO;
import model.Animal;
import model.AnimalDAO;
import model.Tratamento;
import model.TratamentoDAO;
import model.Veterinario;
import model.VeterinarioDAO;

/**
 *
 * @author Antonio Rezende
 */
public class ConsultaTableModel extends GenericTableModel {

    public ConsultaTableModel(List vDados){
        // id_consulta, data_consulta, hora_consulta, relato_consulta, id_veterinario, terminado_consulta
        super(vDados, new String[]{"id", "tratamento", "veterinario", "animal", "sintomas", "data de conclusao"});
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
            case 5:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return consulta.getId();
            case 1:
                Tratamento tratamento = TratamentoDAO.getInstance().retrieveById(consulta.getId_tratamento());
                if (tratamento != null) {
                    return tratamento.getId();
                }
                return "";
                //return consulta.getId_tratamento();
            case 2:
                Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(consulta.getId_veterinario());
                if (veterinario != null) {
                    return veterinario.getNome();
                }
                return "";
                //return consulta.getId_veterinario();
            case 3:
                Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getId_animal());
                if (animal != null) {
                    return animal.getNome();
                }
                return "";
                //return consulta.getId_animal();
            case 4:
                return consulta.getSintomas();
            case 5:
                return consulta.getData_conclusao();
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                consulta.setId((int)aValue);
                break;
            case 1:
                consulta.setId_tratamento((int)aValue);
                break;
            case 2:
                consulta.setId_veterinario((int)aValue);
                break;
            case 3:
                consulta.setId_animal((int)aValue);
                break;
            case 4:
                consulta.setSintomas((String)aValue);    
                break;
            case 5:
                consulta.setData_conclusao((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
        
        ConsultaDAO.getInstance().update(consulta);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}