package View;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author Antonio Rezende
 */
public class ClienteTableModel extends GenericTableModel {

    public ClienteTableModel(List vDados){
        super(vDados, new String[]{"id", "nome", "endereco", "cep", "email", "telefone"});
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
            case 5:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getEndereco();
            case 3:
                return cliente.getCep();
            case 4:
                return cliente.getEmail();
            case 5:
                return cliente.getTelefone();
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                cliente.setId((int)aValue);
                break;
            case 1:
                cliente.setNome((String)aValue);
                break;
            case 2:
                cliente.setEndereco((String)aValue);    
                break;
            case 3:
                cliente.setCep((String)aValue);
                break;
            case 4:
                cliente.setEmail((String)aValue);
                break;
            case 5:
                cliente.setTelefone((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex fora dos limites");
        }
        
        ClienteDAO.getInstance().update(cliente);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}